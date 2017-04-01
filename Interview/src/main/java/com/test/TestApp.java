package com.test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import sun.misc.BASE64Decoder;

public class TestApp {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileInputStream inUsercert;
		try {
			inUsercert = new FileInputStream("D:/西部红果煤炭交易中心(盘江)/xbmt网站/tomcat_HTTPS_SSL配置/startSSL/xbmt_keystore.jks");
			 int len = inUsercert.available();
		      byte[] Usercert = new byte[len];
		      inUsercert.read(Usercert);
		      inUsercert.close();
		      System.out.println(">>>>>>Cert export oK!"+Usercert);
		      
		      String strCert = new String(Usercert);
		      byte[] bcert = new BASE64Decoder().decodeBuffer(strCert );
		     CertificateFactory cf = CertificateFactory.getInstance("X.509");
		     X509Certificate cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(bcert));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}
	
	
	public void test(String str){
		
		List<String> strList = new ArrayList<String>();
		List list = null;
		List<Integer> intList = new ArrayList<Integer>();
		list = strList;
		intList = list;
	}
	
	public int test(int a){
		return 0;
	}

}
