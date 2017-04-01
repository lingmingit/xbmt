package com.xbmt.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;

/***
 * 
 * @author admin
 *
 */
public class DigestUtil {
	
	/** 日志书写对象**/
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DigestUtil.class);
	
	
	static String userName = "user";//登录用户名
	
	static String password = "e10adc3949ba59abbe56e057f20f883e";//登录加密密码
	
	static String realmName = "Contacts Realm via Digest Authentication";//服务器端配置的 固定字符串
	
	static String key = "acegi";//服务器配置 固定 key
	
	static int time = 120; //秒钟   服务器配置过期时间
	
	static String noncecount = "00000001";
	
	static String cnonce = "xx";//cnonce是客户端产生的一个GUID，一般是32字节，而且是16个字节字符串的16进制形式表示，所以其中的内容是0~9和a~f之间的那些字符。
	
	static String qop = "auth";//qop是认证的(校验)方式，这个比较重要，对后面md5的加密过程有影响，值就按照上面的那样写就行了
	
	static String method = "POST";
	
	static String uri = "/xbmt-api/api/j_spring_security_check";
	
    public static void main(String[] args) {
       // System.out.println(encodePasswordInA1Format("user","Contacts Realm via Digest Authentication","123456"));
    	
    	
    	String nonce = DigestUtil.getNonceStr();//根据 时间 生成 对应 Nonce
    	DigestUtil.getResponse(nonce);//服务端效验
    	
    	//logger.info("123456");
    }
    
    /***
     * response的值就很重要了，是根据以上信息，再加上密码通过一定的顺序计算出的一个md5码，固定为16字节的16进制表示形式。
     * 服务器在收到所有这些信息后，也通过相同的方式计算出这个值，而密码则是保存在服务器端，
     * 即服务器要通过用户名去找到对应的密码，然后和计算出md5值，再和客户端传过来的response值对比，
     * 如果一样，则认证通过，否则通不过。
     *  总的计算公式是：md5=HA1:HD:HA2
    	HA1[MD5加密]=username:realm:password
    	HD[原始字符串]=nonce:noncecount:cnonce:qop
    	HA2[MD5加密]=method:uri
     * @return
     */
    public static String getResponse(String  nonce){
    	String HA1 = DigestUtil.encodePasswordInA1Format(userName, realmName, password);
    	String HD = nonce+":"+noncecount+":"+cnonce+":"+qop;
    	String HA2 = md5Hex(method + ":" + uri);
    	String temp = md5Hex(HA1+":"+HD+":"+HA2);
    	logger.info("HA1="+HA1);
    	logger.info("HD="+HD);
    	logger.info("HA2"+HA2);
    	logger.info("Response="+temp);//Response=505a7b89ff95fd5578f9869ee10cf4f9
    	return temp;
    }
    
    
    /***
     * 根据 时间 生成 对应 Nonce
     * nonce的值也是一个字符串，如果不严格，可以随机生成一个就行，注意它是个GUID，即唯一的、不重复的。
     * 如果严格，则需要包含时间信息、客户端IP信息和其它信息，因为认证过程的时间很短，
     * 所以如果服务器收到认证信息后发现这个时间和服务器的时间相去甚远，那说明不正常，
     * 直接拒绝，以防止攻击，还有客户端IP，如果这个IP一直这样攻击，则可以在一定时间内发现是该IP的连接则直接断掉。
     * 这些严格的做法主要是为了防止攻击。在rfc2617上有狭路为详细的描述。
     * 我这里没有考虑这些，只是使用了个简单的字符串
     * @return
     */
    public static String getNonceStr(){
    	//base64位 加密 解密  base64(expirationTime + ":" + md5Hex(expirationTime + ":" + key2))
    	String expirationTime = String.valueOf(time * 1000);// 10秒钟
    	String temp = expirationTime + ":" + md5Hex(expirationTime + ":" + key);
    	logger.info("temp="+temp);
    	String nonce = DigestUtil.encodeAuthorizationKey(temp);
    	logger.info("nonce="+nonce);
    	DigestUtil.decodeAuthorizationKey(nonce);
    	return nonce;
    }
    
    
    
                                     
    public static String encodePasswordInA1Format(String username, String realm, String password) {
        String a1 = username + ":" + realm + ":" + password;
        return md5Hex(a1);
    }
    
    /**
     * MD5加密
     * @param data
     * @return
     */
    public static String md5Hex(String data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(data.getBytes())));
    }
    
    
    /***
     * base64位 加密
     * @param username
     * @param password
     * @return
     */
    public static String encodeAuthorizationKey(final String username, final String password) {
        final String authorizationString = username + ":" + password;
        return encodeAuthorizationKey(authorizationString);
    }
    
    
    /***
     * base64位 加密
     * @param username
     * @param password
     * @return
     */
    public static String encodeAuthorizationKey(String temp) {
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(temp.getBytes(Charset.forName("US-ASCII"))));
    }

    /***
     * base64位 解密
     * @param username
     * @param password
     * @return
     */
    public static void decodeAuthorizationKey(final String basicAuthValue) {
        if (basicAuthValue == null) {
        	return;
        }
        final byte[] decodeBytes = org.apache.commons.codec.binary.Base64.decodeBase64(basicAuthValue.substring(basicAuthValue.indexOf(' ') + 1));
        String decoded = null;
        try {
            decoded = new String(decodeBytes, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
        	e.printStackTrace();
        	return;
        }
        logger.info("decoded="+decoded);
        final int indexOfDelimiter = decoded.indexOf(':');
       // final String username = decoded.substring(0, indexOfDelimiter);
       // final String password = decoded.substring(indexOfDelimiter + 1);
      //  logger.info("username="+username+" \t password="+password);
    }
}