package com.xbmt.test.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import com.xbmt.test.entity.Classes;
import com.xbmt.test.entity.ListBean;
import com.xbmt.test.entity.TestEntity;


/***
 * Java对象和XML字符串的相互转换<p>
 * @author LingMin 
 * @date 2015-5-11<br>
 * @version 1.0<br>
 */
public class XStreamTest {

	XStream xstream = null;
	/**
	 * 构造方法
	 */
    public XStreamTest(){
    	xstream = new XStream();
    }
    
    
    /***
     * 转换单个 实体 为xml<p>
     *  <p>
     * void
     */
    public void writeBean2XML(){
    	TestEntity bean = new TestEntity();
	   	 bean.setId("id0001");
	   	 bean.setNumber("N0001");
	   	 bean.setName("名称1");
		ObjectOutputStream  out = null;
		ObjectInputStream in = null;
		//
		String result = xstream.toXML(bean);
		System.out.println("result="+result);
		
       //类重命名
       //xstream.alias("account", TestEntity.class);
       //xstream.alias("生日", Birthday.class);
       //xstream.aliasField("生日", Student.class, "birthday");
       //xstream.aliasField("生日", Birthday.class, "birthday");
		
       //属性重命名
       xstream.aliasField("名称", TestEntity.class, "name");
       //包重命名
       xstream.aliasPackage("com.test", "com.eas.test.entity");
       result = xstream.toXML(bean);
		System.out.println("result[重命名后的XML]="+result);
    }
    /****
     *  转换 list 为xml<p>
     *  <p>
     * void
     */
    public void writeList2XML() {
    	
        ListBean listBean = new ListBean();
        listBean.setName("this is a List Collection");
       

        //list.add(listBean);//引用listBean，父元素
    	
    	List<TestEntity> list = new ArrayList<TestEntity>();
    	TestEntity bean = new TestEntity();
	   	 bean.setId("id0001");
	   	 bean.setNumber("N0001");
	   	 bean.setName("名称1");
	   	 list.add(bean);
	   	 
	   	bean = new TestEntity();
	   	 bean.setId("id0002");
	   	 bean.setNumber("N0002");
	   	 bean.setName("名称2");
	   	 list.add(bean);
	     list.add(bean);//引用bean
	     
	     listBean.setList(list);
	     
	     //将ListBean中的集合设置空元素，即不显示集合元素标签
	     xstream.addImplicitCollection(ListBean.class, "list");

	     //设置reference模型
	     //xstream.setMode(XStream.NO_REFERENCES);//不引用
	     xstream.setMode(XStream.ID_REFERENCES);//id引用
	     //xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);//绝对路径引用

	      //将name设置为父类（Student）的元素的属性
	      xstream.useAttributeFor(TestEntity.class, "name");
	      //xstream.useAttributeFor(Birthday.class, "birthday");

	        //修改属性的name
	        xstream.aliasAttribute("姓名", "name");
	      //  xstream.aliasField("生日", Birthday.class, "birthday");

	      
	   	 
	   	String result = xstream.toXML(listBean);
		System.out.println("result="+result);
    }
    
    
    /***
     * <p>
     *  <p>
     * void
     */
    public void writeList2XML4Annotation(){
    	
    	TestEntity bean = new TestEntity();
	   	 bean.setId("id0001");
	   	 bean.setNumber("N0001");
	   	 bean.setName("名称1");
	   	 
	   	TestEntity bean1 = new TestEntity();
	   	 bean1.setId("id0002");
	   	 bean1.setNumber("N0002");
	   	 bean1.setName("名称2");
	   	 
        Classes c = new Classes("一班", bean , bean1);
        c.setNumber(2);
        
        //对指定的类使用Annotation
       // xstream.processAnnotations(Classes.class);
        //启用Annotation
        //xstream.autodetectAnnotations(true);
        xstream.alias("testEntity", TestEntity.class);
        xstream.omitField(Classes.class, "created");
        xstream.omitField(com.xbmt.framework.entity.base.Core.class, "logger");
        xstream.omitField(org.apache.log4j.Logger.class, "repository");
        //
        xstream.ignoreUnknownElements("logger");
       // xstream.
	   	String result = xstream.toXML(bean1);
		System.out.println("result="+result);
    }
    
	/**            
	 *  <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		XStreamTest xstreamTest = new XStreamTest();
		//xstreamTest.writeBean2XML();
		//xstreamTest.writeList2XML();
		
		xstreamTest.writeList2XML4Annotation();
	}

}
