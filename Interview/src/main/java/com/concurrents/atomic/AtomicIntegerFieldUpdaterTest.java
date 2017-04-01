/**
 * 
 */
package com.concurrents.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 * 
 * AtomicIntegerFieldUpdater类的主要作用是让普通变量也享受原子操作。

    就比如原本有一个变量是int型，并且很多地方都应用了这个变量，但是在某个场景下，
   想让int型变成AtomicInteger，但是如果直接改类型，就要改其他地方的应用。
  AtomicIntegerFieldUpdater就是为了解决这样的问题产生的。
 * 
 * Updater只能修改它可见范围内的变量。因为Updater使用反射得到这个变量。如果变量不可见，就会出错。比如如果某变量申明为private，就是不可行的。
为了确保变量被正确的读取，它必须是volatile类型的。如果我们原有代码中未申明这个类型，那么简单得申明一下就行，这不会引起什么问题。

由于CAS操作会通过对象实例中的偏移量直接进行赋值，因此，它不支持static字段（Unsafe.objectFieldOffset()不支持静态变量）。
 * @author LingMin 
 * @date 2016-9-21<br>
 * @version 1.0<br>
 */
public class AtomicIntegerFieldUpdaterTest {

	public static class V{
        int id;
        volatile int score;
        public int getScore()
        {
            return score;
        }
        public void setScore(int score)
        {
            this.score = score;
        }
 
    }
    public final static AtomicIntegerFieldUpdater<V> vv = AtomicIntegerFieldUpdater.newUpdater(V.class, "score");
 
    public static AtomicInteger allscore = new AtomicInteger(0);
 
    public static void main(String[] args) throws InterruptedException
    {
        final V stu = new V();
        Thread[] t = new Thread[100];
        for (int i = 0; i < 100; i++)
        {
            t[i] = new Thread() {
                @Override
                public void run()
                {
                    if(Math.random()>0.4)
                    {
                        vv.incrementAndGet(stu);
                        allscore.incrementAndGet();
                    }
                }
            };
            t[i].start();
        }
        for (int i = 0; i < 100; i++)
        {
            t[i].join();
        }
        System.out.println("score="+stu.getScore());
        System.out.println("allscore="+allscore);
        System.out.println("vv.="+vv.get(stu));
    }

}
