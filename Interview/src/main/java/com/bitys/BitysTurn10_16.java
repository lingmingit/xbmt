package com.bitys;


/**
 * 进制的转换（10进制转16进制为例）位运算
 * @author admin
 *
 */
public class BitysTurn10_16 {

	 public static void main(String[] args){
	        int n=200; //n就是代转换的数字
	        boolean out_turn=false; //输出时用，去掉输出时候高位上的‘0’
	        int[] s=new int[20]; //将转换后的十六进制数存放在s［］数组中
	 
	    for(int i=0;i<=8;i++){ //int型占用了8个byte位置，每个byte即一个16进制，
	                               //每次保留一个byte并且转换成16进制，至少要8次（可以优化）
	            int temp= n & 15;  //与0000-0000 0000-0000 0000-0000 0000-1111进
	                               // 行&运算，只保留最后4个位置即“个位”上的数
	            s[i]=temp; //将这个数赋给个位
	            n=n>>>4; //无符号右移4个位置，再保留出十位上的数
	        }
	 
	        for(int i=19;i>=0;i--){
	            if(out_turn == false){   //这个if是为了去掉最高位上的0，其中out_turn作为
	                                     //开关；
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
