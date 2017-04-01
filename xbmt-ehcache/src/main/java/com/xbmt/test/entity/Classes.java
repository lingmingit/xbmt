package com.xbmt.test.entity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.xbmt.test.converter.SingleValueCalendarConverter;

/***
 * 测试xml 转换<p>
 * @author LingMin 
 * @date 2015-5-11<br>
 * @version 1.0<br>
 */
@XStreamAlias("class")
public class Classes {
	
	
    /*
     * 设置属性显示
     */
    @XStreamAsAttribute
    @XStreamAlias("名称")
    private String name;

    

    /*
     * 忽略
     */
    @XStreamOmitField
    private int number;

    

    @XStreamImplicit(itemFieldName = "testEntitys")
    private List<TestEntity> testEntitys;
    

    @SuppressWarnings("unused")
    @XStreamConverter(SingleValueCalendarConverter.class)
    private Calendar created = new GregorianCalendar();

 

    

    public Classes(){}

    /***
     * 构造方法
     * @param name
     * @param ts
     */
    public Classes(String name, TestEntity... ts) {
        this.name = name;
        this.testEntitys = Arrays.asList(ts);
    }

	/**
	 * 获取name<p>
	 * @return  name  name<br>
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name<p>
	 * @param  name  name<br>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取number<p>
	 * @return  number  number<br>
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * 设置number<p>
	 * @param  number  number<br>
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * 获取testEntitys<p>
	 * @return  testEntitys  testEntitys<br>
	 */
	public List<TestEntity> getTestEntitys() {
		return testEntitys;
	}

	/**
	 * 设置testEntitys<p>
	 * @param  testEntitys  testEntitys<br>
	 */
	public void setTestEntitys(List<TestEntity> testEntitys) {
		this.testEntitys = testEntitys;
	}

	/**
	 * 获取created<p>
	 * @return  created  created<br>
	 */
	public Calendar getCreated() {
		return created;
	}

	/**
	 * 设置created<p>
	 * @param  created  created<br>
	 */
	public void setCreated(Calendar created) {
		this.created = created;
	}

	

}
