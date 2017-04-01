package com.xbmt.auth2;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.codec.Base64;


/***
 * http 摘要认证 token管理
 * @author admin
 *
 */
public class MyTokenUtils {

	
	/** 日志书写对象**/
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyTokenUtils.class);
	//摘要分隔符
	public static String SplitChar = ":";
	//认证方式
	public static String Qop = "auth";
	
	//响应认证头名称  获取token
	public static String RespAuthHeaderKey = "WWW-Authenticate";
		//请求认证头 名称  请求资源			     
	public static String ReqAuthHeaderKey = "Authorization";
	
	/***
	 * 将摘要认证信息添加到 响应头中
	 * @param response 响应对象
	 * @param realmName 固定名称
	 * @param key 计算签名固定key
	 * @param nonceValiditySeconds token失效时间  单位秒
	 * @param qop 认证方式
	 */
	public static void addResponseHeader(HttpServletResponse response , String realmName , String key , int nonceValiditySeconds , String qop){
        response.addHeader(RespAuthHeaderKey, MyTokenUtils.getAuthenticateHeader(realmName, MyTokenUtils.getNonceValueBase64(key , nonceValiditySeconds)));
	}
	
	/***
	 * 拼接 摘要认证信息
	 * @param realmName 固定名称
	 * @param key 计算签名固定key
	 * @param nonceValiditySeconds token失效时间  单位秒
	 * @param qop 认证方式
	 */
	public static String getAuthenticateHeader(String realmName , String nonceValueBase64){
		return MyTokenUtils.getAuthenticateHeader(realmName , Qop , nonceValueBase64 );
	}
	/***
	 * 拼接 摘要认证信息
	 * @param realmName 固定名称
	 * @param key 计算签名固定key
	 * @param nonceValiditySeconds token失效时间  单位秒
	 * @param qop 认证方式
	 */
	public static String getAuthenticateHeader(String realmName  , String qop , String nonceValueBase64){
        StringBuffer temp = new StringBuffer();
        temp.append("Digest realm=\"").append(realmName).append("\",").append("qop=\"").append(qop).append("\",").append("nonce=\"").append(nonceValueBase64).append("\"");
       // String authenticateHeader = "Digest realm=\"" + realmName + "\", " + "qop=\"auth\", nonce=\""+ nonceValueBase64 + "\"";
        if (logger.isDebugEnabled()) {
            logger.debug("WWW-Authenticate header sent to user agent: " + temp.toString());
        }
		return temp.toString();
	}
	
	
	/***
	 * 计算  nonce base64加密 签名值
	 * nonce的值也是一个字符串，如果不严格，可以随机生成一个就行，注意它是个GUID，即唯一的、不重复的。
	 * 如果严格，则需要包含时间信息、客户端IP信息和其它信息，因为认证过程的时间很短，所以如果服务器收到认证信息后发现这个时间和服务器的时间相去甚远，
	 * 那说明不正常，直接拒绝，以防止攻击，还有客户端IP，如果这个IP一直这样攻击，则可以在一定时间内发现是该IP的连接则直接断掉。
	 * 这些严格的做法主要是为了防止攻击。在rfc2617上有狭路为详细的描述。我这里没有考虑这些，只是使用了个简单的字符串
	 * 目前 nonce=   Base64.encode（失效毫秒数：MD5(失效毫秒数：计算签名固定key)）
	 * @param key 计算签名固定key
	 * @param nonceValiditySeconds token失效时间  单位秒
	 * @return
	 */
	public static String getNonceValueBase64(String key , int nonceValiditySeconds ){
		long expiryTime = System.currentTimeMillis() + (nonceValiditySeconds * 1000);
		StringBuffer temp = new StringBuffer();
		temp.append(expiryTime).append(SplitChar).append(key);//失效毫秒数：计算签名固定key
        String signatureValue = MyDigestAuthUtils.md5Hex(temp.toString());
        temp.setLength(0);//清空
        temp.append(expiryTime).append(SplitChar).append(signatureValue);//nonce=   失效毫秒数：MD5(失效毫秒数：计算签名固定key)
        return new String(Base64.encode(temp.toString().getBytes()));//再进行 Base64 加密
	}
}
