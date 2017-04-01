package com.concurrents.cwal;

import java.util.List;


/***
 * 可以通过下面两段代码的运行状况来验证一下CopyOnWriteArrayList是不是线程安全的容器。
 * @author LingMin 
 * @date 2016-11-16<br>
 * @version 1.0<br>
 */
public class AddThread  implements Runnable {
    private List<Double> list;
 
    public AddThread(List<Double> list) {
        this.list = list;
    }
 
    @Override
    public void run() {
        for(int i = 0; i < 10000; ++i) {
            list.add(Math.random());
        }
    }
}
