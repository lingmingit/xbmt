package com.serializables;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/** 
 * 测试序列化 <p>
 * @author LingMin 
 * @date 2016-10-17<br>
 * @version 1.0<br>
 */
public class Circle extends Shape  implements java.io.Serializable{

	private float radius = 1.2f;
	
    transient int color = 11;
	
	public static String type = "Circle";
	
	@Override
	public String toString() {
		return "radius="+radius+" \t color="+color+" \t type="+type;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circle subc = new Circle();
		FileInputStream in = null;
		FileOutputStream out = null;
		ObjectInputStream oin = null;
		ObjectOutputStream oout = null;
		try {
			System.out.println(subc);
			out = new FileOutputStream("Test1.txt");// 子类序列化
			oout = new ObjectOutputStream(out);
			oout.writeObject(subc);
			oout.close();
			oout = null;

			in = new FileInputStream("Test1.txt");
			oin = new ObjectInputStream(in);
			Circle subc2 = (Circle) oin.readObject();// 子类反序列化
			System.out.println(subc2);
			oin.close();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

	}

}
