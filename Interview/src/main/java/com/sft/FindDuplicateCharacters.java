package com.sft;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/***
 * 找出字符串中重复字符的Java程序<p>
 * https://community.oracle.com/docs/DOC-887464
 *   解决这个问题的标准方法是从字符串获取字符数组，对它进行遍历再用字符和字符数构建一个Map。然后遍历这个Map并打印出出现多于一次的字符。所以，你实际上需要两个循环来完成这项工作，第一个循环用来构建map而第二个循环用来打印字符和字符数。如果你看看下面的例子，只有一个静态方法称为printDuplicateCharacters()，可以完成以上两个任务。

     首先我们通过调用toCharArray()来获取字符串的字符数组。下一步我们使用HashMap来存储字符和字符数。我们使用containsKey()方法来检验key看字符是否已经存在，如果存在我们通过调用get()方法从HashMap获取老的计数并加1后存回。
 * 
 * @author LingMin 
 * @date 2017-2-23<br>
 * @version 1.0<br>
 */
public class FindDuplicateCharacters {

	 public static void main(String args[]) {

	       printDuplicateCharacters("Programming");

	       printDuplicateCharacters("Combination");

	       printDuplicateCharacters("Java");

	    }

	 

	   /*

	  * Find all duplicate characters in a String and print each of them.

	  */

	   public static void printDuplicateCharacters(String word) {

	        char[] characters = word.toCharArray();

	 

	        // build HashMap with character and number of times they appear in String

	       Map<Character, Integer> charMap = new HashMap<Character, Integer>();

	        for (Character ch : characters) {

	             if (charMap.containsKey(ch)) {

	                 charMap.put(ch, charMap.get(ch) + 1);

	             } else {

	       charMap.put(ch, 1);

	       }

	   }

	 

	   // Iterate through HashMap to print all duplicate characters of String

	  Set<Map.Entry<Character, Integer>> entrySet = charMap.entrySet();

	  System.out.printf("List of duplicate characters in String '%s' %n", word);

	   for (Map.Entry<Character, Integer> entry : entrySet) {

	        if (entry.getValue() > 1) {

	       System.out.printf("%s : %d %n", entry.getKey(), entry.getValue());

	       }

	     }

	  }

	 
}
