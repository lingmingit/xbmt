package com.concurrents.zxjjc;

import java.util.concurrent.Semaphore;



/***
 * blog.csdn.net/jackfrued/article/details/44499227
 * 哲学家进餐

1965年，荷兰计算机科学家图灵奖得主Edsger Wybe Dijkstra提出并解决了一个他称之为哲学家进餐的同步问题。
这个问题可以简单地描述如下：五个哲学家围坐在一张圆桌周围，每个哲学家面前都有一盘通心粉。
由于通心粉很滑，所以需要两把叉子才能夹住。相邻两个盘子之间放有一把叉子如下图所示。
哲学家的生活中有两种交替活动时段：即吃饭和思考。当一个哲学家觉得饿了时，
他就试图分两次去取其左边和右边的叉子，每次拿一把，但不分次序。如果成功地得到了两把叉子，
就开始吃饭，吃完后放下叉子继续思考。
把上面问题中的哲学家换成线程，把叉子换成竞争的临界资源，上面的问题就是线程竞争资源的问题。
如果没有经过精心的设计，系统就会出现死锁、活锁、吞吐量下降等问题。
 * 
 * @author LingMin 
 * @date 2016-11-16<br>
 * @version 1.0<br>
 */
public class Test04 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  String[] names = { "骆昊", "王大锤", "张三丰", "杨过", "李莫愁" };   // 5位哲学家的名字
//	      ExecutorService es = Executors.newFixedThreadPool(AppContext.NUM_OF_PHILO); // 创建固定大小的线程池
//	      for(int i = 0, len = names.length; i < len; ++i) {
//	          es.execute(new Philosopher(i, names[i]));   // 启动线程
//	      }
//	      es.shutdown();
		  
        for(int i = 0, len = names.length; i < len; ++i) {
            new Thread(new Philosopher(i, names[i])).start();
        }

	}

}


/**
 * 存放线程共享信号量的上下问
 * @author 骆昊
 *
 */
class AppContext {
    public static final int NUM_OF_FORKS = 5;   // 叉子数量(资源)
    public static final int NUM_OF_PHILO = 5;   // 哲学家数量(线程)
 
    public static Semaphore[] forks;    // 叉子的信号量
    public static Semaphore counter;    // 哲学家的信号量
 
    static {
        forks = new Semaphore[NUM_OF_FORKS];
 
        for (int i = 0, len = forks.length; i < len; ++i) {
            forks[i] = new Semaphore(1);    // 每个叉子的信号量为1
        }
 
        counter = new Semaphore(NUM_OF_PHILO - 1);  // 如果有N个哲学家，最多只允许N-1人同时取叉子
    }
 
    /**
     * 取得叉子
     * @param index 第几个哲学家
     * @param leftFirst 是否先取得左边的叉子
     * @throws InterruptedException
     */
    public static void putOnFork(int index, boolean leftFirst) throws InterruptedException {
        if(leftFirst) {
            forks[index].acquire();
            forks[(index + 1) % NUM_OF_PHILO].acquire();
        }
        else {
            forks[(index + 1) % NUM_OF_PHILO].acquire();
            forks[index].acquire();
        }
    }
 
    /**
     * 放回叉子
     * @param index 第几个哲学家
     * @param leftFirst 是否先放回左边的叉子
     * @throws InterruptedException
     */
    public static void putDownFork(int index, boolean leftFirst) throws InterruptedException {
        if(leftFirst) {
            forks[index].release();
            forks[(index + 1) % NUM_OF_PHILO].release();
        }
        else {
            forks[(index + 1) % NUM_OF_PHILO].release();
            forks[index].release();
        }
    }
}
 
/**
 * 哲学家
 * @author 骆昊
 *
 */
class Philosopher implements Runnable {
    private int index;      // 编号
    private String name;    // 名字
 
    public Philosopher(int index, String name) {
        this.index = index;
        this.name = name;
    }
 
    @Override
    public void run() {
        while(true) {
            try {
                AppContext.counter.acquire();
                boolean leftFirst = index % 2 == 0;
                AppContext.putOnFork(index, leftFirst);
                System.out.println(name + "正在吃意大利面（通心粉）...");   // 取到两个叉子就可以进食
                AppContext.putDownFork(index, leftFirst);
                AppContext.counter.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
