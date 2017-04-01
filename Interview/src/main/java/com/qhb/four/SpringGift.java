package com.qhb.four;

import java.util.concurrent.atomic.AtomicInteger;



/** 
 * Java模拟抢红包应用
 * http://www.lai18.com/content/8811447.html
 * <p>
 * @author LingMin 
 * @date 2016-10-10<br>
 * @version 1.0<br>
 */
public class SpringGift {

	private String role;
	private AtomicInteger gift;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AtomicInteger getGift() {
		return gift;
	}

	public void setGift(AtomicInteger gift) {
		this.gift = gift;
	}

	public int getRemainCount() {
		return this.gift.get();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "role="+role+" \t gift="+gift.toString();
	}

}
