package com.base;

/***
 * 测试逻辑表达式 <p>
 * 1、三元表达式运算
 * 2、先++还是后++， 运算符在前面就是 先++再比较或运算，如果再后面 先比较或运算后再++
 * 
 * @author LingMin 
 * @date 2016-8-16<br>
 * @version 1.0<br>
 */
public class ExpressionTest {

	/**             
	 * <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a =1 , b = 2 , c = 3;
		boolean u = false , v = true;
		boolean flag = (a >= 1 && b <= 12 ? v :u);// true
		System.out.println("flag="+flag);
				//!(true | true && true)
		flag = !((a >= --b | b++ <= c--) && b == c);// false
		System.out.println("flag="+flag);
		
		int i = 3;
		//             3 + 5     -  6 * 7
		int num = ((i++) + (++i) - ((++i) * (++i)));// -34
		System.out.println(num);
	}

}
