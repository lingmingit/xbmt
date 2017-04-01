package com.base;


/***
 * 在JAVA中，不用API中的函数，如何将String类型的转换成int类型数据<p>
 * 实现思路  通过 ASCII值(将char转换为int类型 得到) 对照表实现
 * 1、首先判断字符串第一位char是否为负号，如果如果为负号 index = 1，否则index = 0 
 * 2、循环将字符串分割为 每一个char ，将char转换为int类型，再将结果减去 48 或  (int)('0')   value = s.charAt(index) - 48;
 * 3、再将转换结果进行累加 每一位，同时 *10的倍数     result * 10 + value
 * 4、再转换负数，如果是负数用 0 - result即可
 * @author LingMin 
 * @date 2016-7-19<br>
 * @version 1.0<br>
 */
public class ConvertStringToInt {

	
	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		String s = "-1232";
		int number = parseInt2(s);
		System.out.println("number="+number);
		String s1 = "1232";
		number = parseInt(s1);
		System.out.println("number="+number);
	}
	
	
	
	/***
	 * 支持 负数
	 * 将String 类型 转换 int 类型  自定义转换
	 * 在JAVA中，不用API中的函数，如何将String类型的转换成int类型数据<p>
	 * @param s 字符串
	 * @return int 返回类型<p>
	 * 
	 */
	public static int parseInt2(String s) {
		int result = 0;
		int value;
		int index = 0;
		if(s.charAt(0) == '-') {
			index = 1;
		}
		for(; index < s.length() ; index++) {
			value = s.charAt(index) - 48;
			if(value < 0 || value > 9) {
				throw new NumberFormatException();
			} else {
				result = result * 10 + value;
			}
		}
		if(s.charAt(0) == '-') {
			result = 0 - result;
		}
		//System.out.println(result);
        return result;
    }
	
	
	/***
	 * 不支持负数
	 * 将String 类型 转换 int 类型  自定义转换
	 * 在JAVA中，不用API中的函数，如何将String类型的转换成int类型数据<p>
	 * @param s 字符串
	 * @return int 返回类型<p>
	 * 
	 */
	public static int parseInt(String s) {
        if (s == null || s.equals("")){
            throw new IllegalArgumentException("参数不能为null或空串！");
        }
        int result = 0;
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            
            if (c >= '0' && c <= '9') {
                result = result * 10 + (int) (c - '0');
              System.out.println("c="+c+" \t result="+result);
            } else {
                throw new IllegalArgumentException("s中只能包含数字");
            }
        }
        return result;
    }
}
