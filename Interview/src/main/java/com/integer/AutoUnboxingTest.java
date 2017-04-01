package com.integer;




/** 
 * int和Integer有什么区别
 * <p>
 * @author LingMin 
 * @date 2016-9-15<br>
 * @version 1.0<br>
 */
public class AutoUnboxingTest {

	
	 public static void main(String[] args) {
	        Integer a = new Integer(3);
	        Integer b = 3;                  // 将3自动装箱成Integer类型
	        int c = 3;
	        Integer d = 3; 
	        System.out.println(a == b);     // false 两个引用没有引用同一对象
	        System.out.println(a == c);     // true a自动拆箱成int类型再和c比较
	        System.out.println(b == c); //true   b自动拆箱成int类型再和c比较
	        System.out.println(b == d); //true   b 和 d 都用Integer 缓存 所以为true
	        
	        //简单的说，如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象，
	        //所以上面的面试题中f1==f2的结果是true，而f3==f4的结果是false。
	        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
	        
	        System.out.println(f1 == f2);//true   f1 和 f2 都用Integer 缓存 所以为true
	        System.out.println(f3 == f4);//false   因为Integer的 默认缓存大小 为 -128 and 127，150超出范围，所以没有缓存
	        
	        System.out.println(Math.round(-11.6));
	        
	        System.out.println(3 << 5);
	        
	        System.out.println("the result is :" + cal());  
	    }
	 
	 
	 /***
	  * 
	  * @return
	  */
	 public static int cal()  
	    {  
	        int sum = 0;  
	        OUTER: for(int i = 0;i < 10;i++)  
	        {  
	            for(int j = 1;j <= 10;j++)  
	            {  
	                sum += j;  
	                if(j == 5)  {
	                	//1 + 2 + 3+ 4 + 5
	                	System.out.println("break;");
	                    break OUTER;  
	                }
	            }  
	        }  
	        return sum;  
	    }  
}
