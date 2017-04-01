package com.bitys;

/**
 * 进制的转换（10进制转16进制为例）传统
 * 这种转换就像数数，一共有多少个，我来用十六进制的方法再数一遍，逢16进1，数完为止。
 * 这种方法的弊端就是效率低，而且不能转换负数。
 * @author admin
 *
 */
public class turn10_16 {

	public static void main(String[] args){
	    int n=200; //n就是代转换的数字
	    boolean out_turn=false; //输出时用，去掉输出时候高位上的‘0’
	    int[] s=new int[20]; //将转换后的十六进制数存放在s［］数组中
	 
	    while(n>0){
	            int i=0;  
	            s[i]++;
	            while(s[i]>15){ //逢16进一，并且检查下一位
	                            //是不是16，如果是，再进一
	                s[i]=0;  
	                i++;
	                s[i]++;
	            }
	            n--;  //数完一个之后n－－，知道数完n个数
	        }
	 
	    for(int i=19;i>=0;i--){
	        if(out_turn == false){   //这个if是为了去掉最高位上的0，
	                                     // 其中out_turn作为开关；
	        if(s[i]==0)
	        continue;
	        else{
	            out_turn=true;
	            i++;
	        }
	        }
	        else{  //输出转换之后的结果，10输出A，类推
	        if(s[i]<10)
	            System.out.print(s[i]);
	        else
	            System.out.print((char)('A'+(s[i]-10)));
	        }
	    }
	    System.out.println();
	    }
	

}
