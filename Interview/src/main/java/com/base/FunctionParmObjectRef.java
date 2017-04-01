/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.base;

/** 
 * 
 * Java到底是传引用还是传值?
 * http://www.importnew.com/3559.html
 * Java确实使用对象的引用来做计算的，所有的对象变量都是引用。
 * 但是，Java在向方法传递参数时传的不是引用，是值。
 * <p>
 * @author LingMin 
 * @date 2016-9-10<br>
 * @version 1.0<br>
 */
public class FunctionParmObjectRef {

	
	class Point{
		int x;
		int y;
		/**
		 * 
		 * @param x
		 * @param y
		 */
		public Point(int x , int y){
			this.x = x;
			this.y = y;
		}
	}
	/***
	 * Java复制并传递了“引用”的值，而不是对象。因此，方法中对对象的计算是会起作用的，因为引用指向了原来的对象。
	 * 但是因为方法中对象的引用是“副本”，所以对象交换就没起作用。如图2所示，交换动作只对方法中的引用副本起作用了，不影响方法外的引用。
	 * 所以不好意思，方法被调用后，改变不了方法外的对象的引用。如果要对方法外的对象引用做交换，我们应该交换原始的引用，而不是它的副本。<p>
	 * @param arg1
	 * @param arg2 <p>
	 * void
	 */
	public void tricky(Point arg1, Point arg2){
	    arg1.x = 100;
	    arg1.y = 100;
	    Point temp = arg1;
	    arg1 = arg2;
	    arg2 = temp;
	}
	 
	
	
	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String [] args){
		FunctionParmObjectRef test = new FunctionParmObjectRef();
		//实例化内部类，通过外部类对象.new ,如下：
		Point pnt1 = test.new Point(0,0);
	    Point pnt2 = test.new Point(0,0);
	    System.out.println("X: " + pnt1.x + " Y: " +pnt1.y);
	    System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
	    System.out.println(" ");
	    test.tricky(pnt1,pnt2);
	    System.out.println("X: " + pnt1.x + " Y:" + pnt1.y);
	    System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
	}

}
