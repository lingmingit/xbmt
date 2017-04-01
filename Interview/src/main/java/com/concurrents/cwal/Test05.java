package com.concurrents.cwal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 * 可以通过下面两段代码的运行状况来验证一下CopyOnWriteArrayList是不是线程安全的容器。
 * @author LingMin 
 * @date 2016-11-16<br>
 * @version 1.0<br>
 */
public class Test05 {

	private static final int THREAD_POOL_SIZE = 2;
	 
    public static void main(String[] args) {
//        List<Double> list = new ArrayList<>();//代码会在运行时产生ArrayIndexOutOfBoundsException
    	List<Double> list = new CopyOnWriteArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        es.execute(new AddThread(list));
        es.execute(new AddThread(list));
        es.shutdown();
        System.out.println("compant");
    }
}
