package com.base;



/***
 * | 或 || 的使用<p>
 * |  按位或  它们都是位运算符    【按位或 同时执行两个条件】
 * || 逻辑或 也叫 短路或    【逻辑或（短路或） 只要一个条件满足  第二个条件就不执行】
 * switch 没有break 后续的case 继续执行
 * @author LingMin 
 * @date 2016-7-21<br>
 * @version 1.0<br>
 */
public class CutOr {
	
	
	private static int j = 0;
	
	/***
	 * case 没有break 后续case 都会执行<p>
	 * @param k
	 * @return <p>
	 * boolean
	 */
	private static boolean methodB(int k){
		
		j += k;//j = 0 + 4 = 4
		switch(j){
			case (4):
				j += 1; //j = 4 + 1 = 5
			case (8):
				j += 2; //j = 5 + 2 = 7
			case (12):
				j += 3; //j = 7 + 3 = 10
		
		}
		return true;
	}
	
	/***
	 * | 或 || 短路或 和非短路或的使用 <p>
	 * @param i <p>
	 * void
	 */
	public static void methodA(int i){
		boolean b;
		//按位或  同时执行两个条件
		b = i < 10 | methodB(4);
		//逻辑或（短路或） 只要一个条件满足  第二个条件就不执行
		b = i < 10 || methodB(8);
	}
	
	
	/***
	 * | 或 || 短路或 和非短路或的使用 <p>
	 * @param i <p>
	 * void
	 */
	public static void methodC(){
		int x, y = 10;
		//逻辑或（短路或） 只要一个条件满足  第二个条件就不执行
		if((x = 0) == 0 || (y = 20) == 20){
			System.out.println("y="+y);// 结果 10
		}
		//按位或  同时执行两个条件
		int a , b = 10;
		if((a = 0) == 0 | (b = 20) == 20){
			System.out.println("b="+b); // 结果20
		}
	}
	

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		methodA(0);
		System.out.println(j);//所以 最终结果为10
		
		methodC();
	}

}
