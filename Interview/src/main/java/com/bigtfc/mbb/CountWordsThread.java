package com.bigtfc.mbb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/***
 * 用java多线程统计超大数据文件(1TB以上)中出现次数最多的人名 <p>
 * @author LingMin 
 * @date 2016-8-2<br>
 * @version 1.0<br>
 */
public class CountWordsThread  implements Runnable{
	
    private FileChannel fileChannel = null;
    private FileLock lock = null;
    private MappedByteBuffer mbBuf = null;
    private Map<String, Integer> hashMap = null;
     
    public CountWordsThread(File file, long start, long end) {
        try {
            // 得到当前文件的通道
            fileChannel = new RandomAccessFile(file, "rw").getChannel();
            // 锁定当前文件的部分
            lock = fileChannel.lock(start, end, false);
            // 对当前文件片段建立内存映射，如果文件过大需要切割成多个片段
            // 2016-08-03 此处map方法第三个参数为映射size大小，而不是结束位置，否则 就会出现源文件大小增加 （如果您请求一个超出文件大小的映射，文件会被增大以匹配映射的大小。）
            mbBuf = fileChannel.map(FileChannel.MapMode.READ_ONLY, start, end);
            // 创建HashMap实例存放处理结果
            hashMap = new HashMap<String,Integer>();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        String str = Charset.forName("UTF-8").decode(mbBuf).toString();
        StringTokenizer token = new StringTokenizer(str);
        String word = null;        
        while(token.hasMoreTokens()) {
            // 将处理结果放到一个HashMap中
            word = token.nextToken().toString().trim();
            if(null != hashMap.get(word)) {
                hashMap.put(word, hashMap.get(word) + 1);
            } else {
                hashMap.put(word, 1);
            }
        }
        try {
            // 释放文件锁
            lock.release();
            mbBuf.clear();
            this.clean(mbBuf);
            // 关闭文件通道
            fileChannel.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return;
    }
     
    //获取当前线程的执行结果
    public Map<String, Integer> getResultMap() {
        return hashMap;
    }
    
    
    /***
     * MappedByteBuffer的确快，但也存在一些问题，主要就是内存占用和文件关闭等不确定问题。
     * 被MappedByteBuffer打开的文件只有在垃圾收集时才会被关闭，而这个点是不确定的。
     * 在javadoc里是这么说的：A mapped byte buffer and the file mapping that it represents remain valid until the buffer itself  is garbage-collected.<p>
     * @param buffer
     * @throws Exception <p>
     * void
     */
	public static void clean(final Object buffer) throws Exception {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod(
							"cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
							.invoke(buffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});

	}
}
