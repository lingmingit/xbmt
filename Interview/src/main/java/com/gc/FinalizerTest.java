package com.gc;
/***
 * 测试
 * 2、可达性分析法
通过一系列称为 “GC Roots” 的对象作为起点，从这些节点开始向下搜索，搜索路径称为 “引用链”，以下对象可作为GC Roots：
<p>
 * @author LingMin 
 * @date 2017-3-10<br>
 * @version 1.0<br>
 */
public class FinalizerTest {
	
	public static FinalizerTest object;
    public void isAlive() {
        System.out.println("I'm alive");
    }
 
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("method finalize is running");
        object = this;
    }

	/**             <p>
	 * @param args <p>
	 * void
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		
		 object = new FinalizerTest();
		 
	        // 第一次执行，finalize方法会自救
	        object = null;
	        System.gc();
	 
	        Thread.sleep(500);
	        if (object != null) {
	            object.isAlive();
	        } else {
	            System.out.println("I'm dead");
	        }
	 
	        // 第二次执行，finalize方法已经执行过
	        object = null;
	        System.gc();
	 
	        Thread.sleep(500);
	        if (object != null) {
	            object.isAlive();
	        } else {
	            System.out.println("I'm dead");
	        }

	}

}
