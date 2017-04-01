package com.concurrents.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/***
 * blog.csdn.net/jackfrued/article/details/44499227
 * 刚才忘记了一件事情，Java 5中还引入了Callable接口、Future接口和FutureTask接口，通过他们也可以构建并发应用程序，代码如下所示。
 * @author LingMin 
 * @date 2016-11-16<br>
 * @version 1.0<br>
 */
public class Test07 {

	 private static final int POOL_SIZE = 10;
	 
	 
	    static class CalcThread implements Callable<Double> {
	        private List<Double> dataList = new ArrayList<>();
	 
	        public CalcThread() {
	            for(int i = 0; i < 10000; ++i) {
	                dataList.add(Math.random());
	            }
	        }
	 
	        @Override
	        public Double call() throws Exception {
	            double total = 0;
	            for(Double d : dataList) {
	                total += d;
	            }
	            return total / dataList.size();
	        }
	 
	    }
	 
	    public static void main(String[] args) {
	        List<Future<Double>> fList = new ArrayList<>();
	        ExecutorService es = Executors.newFixedThreadPool(POOL_SIZE);
	        for(int i = 0; i < POOL_SIZE; ++i) {
	            fList.add(es.submit(new CalcThread()));
	        }
	 
	        for(Future<Double> f : fList) {
	            try {
	                System.out.println(f.get());
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	 
	        es.shutdown();
	    }
}
