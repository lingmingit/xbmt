package com.sft;



/** 
   将数字转换为中文可读数字<p>
   实现思路：
   1、定义中文数字数组[零 - 九]、中文单位(5位)[个 - 万，其中个为'']、中文数字单位(4位以上)[万、万、亿、万亿]
   2、首先将数字字符串按4位进行循环分割出来，分割长度按取整数大，同时计算截取起始位置、结算位置()
   	  2.1、按字符串顺序截取算法								     120303
   	  2.2、当循环第一个 并且 模数不等于0，起始位置0，结束位置为模数      12
   	  2.3、否则 本次的结束位置 作为 下次的起始位置
   3、将分割截取好的每一个数字字符串进行字符循环分割，转换为可读中文
   	    注意两个以上0(如果前面一个为0，则后面不需要再增加0)和最后一个数字为0的时候(截取)，并且0不需要单位
   	  将字符串中的每个char转换具体int类型值，匹配中文数字数组
   4、再进行分割字符串转换后的拼接包括单位
              根据循环索引匹配大中文单位数组，int maxIndex = len; zhNumUnits2[maxIndex - index - 1]，
              最大索引小于4位分割串的长度len，按从大到小匹配数组(因4位分割串是按大到小)，最后一位不需要单位
      
 * @author LingMin 
 * @date 2016-10-19<br>
 * @version 1.0<br>
 */
public class NumToZhString {

	//中文数字数组定义
	private String[] zhNumStrs = {"零" , "一" , "二" , "三" , "四" ,"五", "六", "七" , "八" , "九" };
	//中文数字单位数组定义  个、十、百、千、万
	private String[] zhNumUnits = {"" , "十" , "百" , "千" , "万" };//,"十万", "百万", "千万" , "亿" , "十亿" 
	
	//中文数字单位数组定义 
	private String[] zhNumUnits2 = { "万" , "万" , "亿" , "万亿" };//万万亿
	
	//分割位数定义，每4位分割 万
	private int splitNum = 4;
	
	
	/***
	 * 将数字字符串转换为中文可读数字
	 * @param numStr 数字字符串
	 * @return 返回中文可读数字
	 */
	public String getZhNumString2(String numStr){
		if(numStr == null){
			System.out.println("输入字符串为null");
			return null;
		}
		numStr = numStr.trim();
		if("".equals(numStr)){
			System.out.println("输入字符串为空");
			return null;
		}
		if(!numStr.matches("\\d+")){
			System.out.println("输入字符串非数字");
			return null;//非数字 抛出异常
		}
		if(numStr.length() > 16){//超出最大长度
			System.out.println("输入数字长度超出16位最大限制");
			return null;
		}
		//取整数大，首先对位数进行分割，看有几个万位
		int len = (int)Math.ceil(((double)numStr.length() / this.splitNum));
		System.out.println("len="+len);
		StringBuffer zhNumStr = new StringBuffer();
		int lastEndIndex = 0;
		for(int i = 0  ; i < len ; i++){
			/***第一种方法：逆向截取算法  120303
			//1、6 - (0 * 4) = 6     0303
			//2、6 - (1 * 4) = 2     12
			//计算截取结束位置  
			int eIndex = numStr.length() - (i * this.splitNum);
			int bIndex = eIndex - this.splitNum;
			if(bIndex < 0){//起始位置小于0  就默认为0
				bIndex = 0;
			}****/
			/**第二种方法：顺序截取算法		 						     120303      
			 * 1、当第一位 并且 模数不等于0，起始位置0，结束位置为模数     		  12          
			   2、否则 本次的结束位置 作为 下次的起始位置				      0303
			 *   *******/
			int bIndex = 0;
			int eIndex = 0;
			int mod = numStr.length() % this.splitNum;
			//第一次
			if(i == 0 && mod != 0){
				eIndex = mod;
				lastEndIndex = eIndex;
			}else{
				//本次的结束位置 作为 下次的起始位置
				bIndex = lastEndIndex;
				eIndex = bIndex + this.splitNum;
				lastEndIndex = eIndex;
			}
			
			String temp = numStr.substring(bIndex, eIndex);
			String zhNumTemp = this.getZhNumString(temp);
			//System.out.println(zhNumTemp);
			
			zhNumStr.append(zhNumTemp);
			if(i < len - 1){//zhNumUnits2 = { "万" , "万" , "亿" , "万亿" };
				zhNumStr.append(this.getSplitZhUnit(len, i));
			}
			//zhNumStr.append("  "); //增加空格分隔符，有利于阅读
		}
		return zhNumStr.toString();
	}
	
	
	/***
	 * 将4位数字转换为中文可读数字
	 * @param numStr 数字字符串
	 * @return 返回中文可读数字
	 */
	public String getZhNumString(String numStr){
		if(numStr == null){
			return null;
		}
		numStr = numStr.trim();
		if("".equals(numStr)){
			return null;
		}
		if(!numStr.matches("\\d+")){
			return null;//非数字 抛出异常
		}
		
		StringBuffer zhNumStr = new StringBuffer();
		//
		for(int i = 0 ; i < numStr.length() ; i++){
			char charTemp = numStr.charAt(i);
			int index = Integer.parseInt(String.valueOf(charTemp));
			String zhNumTemp = this.getZhNumStr(index);
			if(zhNumStr.length() != 0){
				//如果前一位数字为零 并且 当前位数字为零 ，则不再追加零
				char perZhNumStr = zhNumStr.charAt(zhNumStr.length() - 1);
				if(zhNumStrs[0].equals(String.valueOf(perZhNumStr))){
					if(!zhNumStrs[0].equals(zhNumTemp)){
						zhNumStr.append(zhNumTemp);
					}
				}else{
					zhNumStr.append(zhNumTemp);
				}
			}else{
				zhNumStr.append(zhNumTemp);
			}
			//如果是0 不需要单位
			if(charTemp != '0'){
				int unitIndex = numStr.length() - i - 1;
				zhNumStr.append(this.getZhUnit(unitIndex));
			}
		}
		
		//如果最后一位是零，必须截取
		String lastChar = String.valueOf(zhNumStr.charAt(zhNumStr.length() - 1));
		if(zhNumStrs[0].equals(lastChar)){
			return zhNumStr.substring(0, zhNumStr.length() - 1);
		}
		return zhNumStr.toString();
	}
	
	/***
	 * 根据索引获取 分割位 中文单位
	 * @param len 分割位数
	 * @param index 索引
	 * @return
	 */
	private String getSplitZhUnit(int len , int index){
		//zhNumUnits2 = { "万" , "万" , "亿" , "万亿" };
		int maxIndex = len;// zhNumUnits2.length;
		return zhNumUnits2[maxIndex - index - 1];
	}
	
	/***
	 * 根据索引获取中文数字
	 * @param index 索引
	 * @return 返回中文数字
	 */
	private String getZhNumStr(int index){
		return this.zhNumStrs[index];
	}

	

	/***
	 * 根据索引获取中文数字单位
	 * @param index 索引
	 * @return 返回中文数字单位
	 */
	private String getZhUnit(int index){
		return this.zhNumUnits[index];
	}
	
	
	
	/***
	 * 将数字字符串 按4为进行分割处理 用于显示增加可读性
	 * @param numStr 数字字符串
	 * @return 返回分割后的字符串
	 */
	public String getNum4SplitString(String numStr){
		if(numStr == null){
			return null;
		}
		numStr = numStr.trim();
		if("".equals(numStr)){
			return null;
		}
		if(!numStr.matches("\\d+")){
			return null;//非数字 抛出异常
		}
		//取整数大，首先对位数进行分割，看有几个万位
		int len = (int)Math.ceil(((double)numStr.length() / this.splitNum));
		//System.out.println("len="+len);
		StringBuffer zhNumStr = new StringBuffer();
		int lastEndIndex = 0;
		for(int i = 0  ; i < len ; i++){
			/**第二种方法：顺序截取算法		 						     120303      
			 * 1、当第一位 并且 模数不等于0，起始位置0，结束位置为模数     		  12          
			   2、否则 本次的结束位置 作为 下次的起始位置				      0303
			 *   *******/
			int bIndex = 0;
			int eIndex = 0;
			int mod = numStr.length() % this.splitNum;
			//第一次
			if(i == 0 && mod != 0){
				eIndex = mod;
				lastEndIndex = eIndex;
			}else{
				//本次的结束位置 作为 下次的起始位置
				bIndex = lastEndIndex;
				eIndex = bIndex + this.splitNum;
				lastEndIndex = eIndex;
			}
			
			String temp = numStr.substring(bIndex, eIndex);
			zhNumStr.append(temp).append(" ");
			
		}
		return zhNumStr.toString();
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		double length = 6;
		double len = length / 5;
		System.out.println(len);
		double len2 = Math.ceil(len);
		
		System.out.println(len2);
		
		System.out.println(3%2);
	}

}
