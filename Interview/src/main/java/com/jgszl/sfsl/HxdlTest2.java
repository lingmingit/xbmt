package com.jgszl.sfsl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/** 
 * 10w定时任务，如何高效触发超时
 * 这个环形队列法是一个通用的方法，Set和Map中可以是任何task，本文的uid是一个最简单的举例。<p>
 * @author LingMin 
 * @date 2017年3月16日<br>
 * @version 1.0<br>
 */
public class HxdlTest2 {
	
	
	//环形队列长度
	private int length = 10;
	
	//环形队列当前元素索引
	private volatile AtomicInteger currentIndex = new AtomicInteger(0);
	
	//前一个元素索引位置
	private volatile int previousIndex = -1;
	//1）环形队列数组,30s超时，就创建一个index从0到30的环形队列（本质是个数组）
	private volatile CusQueue[] cusQueueList = new CusQueue[10];

	//3）同时还有一个Map<uid, index>，记录uid落在环上的哪个slot里
	private volatile Map<String , Integer> maps = new ConcurrentHashMap<>();
	
	
	 //时钟对象
	private TimerTask timerTask = null;
	
	/***
	 * 私有构造方法
	 */
	public HxdlTest2(){
		System.out.println("HxdlTest2");
		this.initArray();
		System.out.println("cusQueueList.len="+cusQueueList.length);
	}
	/***
	 * 初始化环形队列数组<p>
	 *  <p>
	 * void
	 */
	private void initArray(){
		cusQueueList = new CusQueue[this.length];
		for(int i = 0 ; i < this.length ;i++){
			cusQueueList[i] = new CusQueue(i);
		}
	}
	
	/***
	 * 启动计时器<p>
	 *  <p>
	 * void
	 */
	public void startTimer(){
		 Timer timer = new Timer();
		   
	    this.timerTask = new TimerTask() {
	      public void run() {
	    	  startProces();
	      }
	    };
	    //timer.schedule(this.timerTask, 50L, 200L);
	    //第一个参数 线程对象， 第二个参数 第一次运行run 方法的间隔时间， 第三个问题下次调用的间隔时间
	    timer.schedule(this.timerTask, 50L, 1000L);
	    /**
	     * ---------------schedule的意思（时间表、进度表）
			timer.schedule(new MyTask(event.getServletContext()), 0, 60*60*1000);
			
			第一个参数"new MyTask(event.getServletContext())":
			是 TimerTask 类，在包：import java.util.TimerTask .使用者要继承该类，并实现 public void run() 方法，因为 TimerTask 类实现了 Runnable 接口。
			
			第二个参数"0"的意思是:(0就表示无延迟)
			当你调用该方法后，该方法必然会调用 TimerTask 类 TimerTask 类 中的 run() 方法，这个参数就是这两者之间的差值，转换成汉语的意思就是说，用户调用 schedule() 方法后，要等待这么长的时间才可以第一次执行 run() 方法。
			
			第三个参数"60*60*1000"的意思就是:
			(单位是毫秒60*60*1000为一小时)
			(单位是毫秒3*60*1000为三分钟)
			第一次调用之后，从第二次开始每隔多长的时间调用一次 run() 方法。

	     */
	}
	
	/***
	 * 增加用户id 到map中<p>
	 * @param uid <p>
	 * void synchronized
	 */
	public void putUid(String uid){
		
		/***
		 * 当有某用户uid有请求包到达时：
		 1）从Map结构中，查找出这个uid存储在哪一个slot里
		 2）从这个slot的Set结构中，删除这个uid
		 3）将uid重新加入到新的slot中，具体是哪一个slot呢 => Current Index指针所指向的上一个slot，因为这个slot，会被timer在30s之后扫描到
	          （4）更新Map，这个uid对应slot的index值
	  */
		System.out.println("thread.name="+Thread.currentThread().getName()+"\t putUid.this.currentIndex="+this.currentIndex.get());
		//首先判断 当前uid是否存在
		if(this.maps.containsKey(uid)){
			int index = maps.get(uid);
			System.out.println("this.cusQueueList["+index+"]="+this.cusQueueList[index].getTaskSets());
			if("thread2".equals(Thread.currentThread().getName())){
				System.out.println("2222");
			}else{
				System.out.println("1111");
			}
			this.cusQueueList[index].getTaskSets().remove(uid);
			//将uid重新加入到新的slot中
			this.maps.put(uid, this.currentIndex.get());
		}else{
			//设置用户id  所对应 环形队列索引位置
			this.maps.put(uid, this.currentIndex.get());
			CusQueue temp =  this.cusQueueList[this.currentIndex.get()];
			Set<String> taskSets =  temp.getTaskSets();
			if(taskSets == null){
				taskSets = new HashSet<>();
			}
			taskSets.add(uid);
			temp.setTaskSets(taskSets);
		}
		this.previousIndex = this.currentIndex.get();
	}
	/***
	 * 启动处理<p>
	 *  <p>
	 * void
	 */
	private  void startProces(){
		
		System.out.println("startProces.currentIndex="+currentIndex.get()+" \t this.previousIndex="+previousIndex);
		//排除当前刚刚增加的元素
		if(this.previousIndex != this.currentIndex.get()){
			CusQueue temp =  this.cusQueueList[this.currentIndex.get()];
			//显示超时的用户列表
			this.displayTaskSets(currentIndex.get() , temp.getTaskSets());
		}else{
			this.previousIndex = -1;
		}
		//
		this.currentIndex.getAndIncrement();
		if(this.currentIndex.get() >= this.length){
			this.currentIndex.set(0);
		}
	}
	
	/***
	 * 打印map结构<p>
	 *  <p>
	 * void
	 */
	public void displayMap(Map<String , Integer> maps){
		java.util.Iterator<String> iterator = maps.keySet().iterator();
		while(iterator.hasNext()){
			String uid = iterator.next();
			Integer index = this.maps.get(uid);
			System.out.println("uid="+uid+" \t index="+index);
		}
	}
	
	/***
	 * 显示超时的用户列表<p>
	 * @param index
	 * @param taskSets <p>
	 * void
	 */
	private  void displayTaskSets(int index , Set<String> taskSets){
		if(taskSets == null || taskSets.isEmpty()){
			System.out.println("currentIndex="+currentIndex.get()+" \t taskSets is Empty");
			return;
		}
		System.out.print("currentIndex="+currentIndex.get()+" \t taskSets is "+taskSets.size());
		java.util.Iterator<String> iterator = taskSets.iterator();
		while(iterator.hasNext()){
			String uid = iterator.next();
			iterator.remove();//删除元素
			System.out.print("\t "+uid+" \t");
		}
		System.out.println();
	}
	
	
	
	
	
	

	/**
	 * 获取previousIndex<p>
	 * @return  previousIndex  previousIndex<br>
	 */
	public int getPreviousIndex() {
		return previousIndex;
	}
	/**
	 * 设置previousIndex<p>
	 * @param  previousIndex  previousIndex<br>
	 */
	public void setPreviousIndex(int previousIndex) {
		this.previousIndex = previousIndex;
	}
	/**
	 * 获取cusQueueList<p>
	 * @return  cusQueueList  cusQueueList<br>
	 */
	public CusQueue[] getCusQueueList() {
		return cusQueueList;
	}
	/**
	 * 设置cusQueueList<p>
	 * @param  cusQueueList  cusQueueList<br>
	 */
	public void setCusQueueList(CusQueue[] cusQueueList) {
		this.cusQueueList = cusQueueList;
	}
	/**
	 * 获取maps<p>
	 * @return  maps  maps<br>
	 */
	public Map<String, Integer> getMaps() {
		return maps;
	}
	/**
	 * 设置maps<p>
	 * @param  maps  maps<br>
	 */
	public void setMaps(Map<String, Integer> maps) {
		this.maps = maps;
	}
	/***
	 * 测试方法<p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		
	}

}