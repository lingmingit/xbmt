package com.xbmt.auth3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;


/****
 * 定义 token管理类
 * @author admin
 * 可以将该token 管理类配置到Spring Bean中
 * 将 计算签名固定值key、token失效时间等参数 通过配置文件动态注入，实现配置可修改
 *
 */
public class TokenUtils{

	
   /***固定计算签名值key***/
   public static final String MAGIC_KEY = "obfuscate";


   /***
    * 根据用户名 生成 token
    * token生成规则：
    * 用户名：失效毫秒数：计算签名值
    * @param userDetails 用户对象
    * @return 返回token值
    */
   public static String createToken(UserDetails userDetails)
   {
      /* Expires in one hour */
      long expires = System.currentTimeMillis() + 1000L * 60 * 60;

      StringBuilder tokenBuilder = new StringBuilder();
      tokenBuilder.append(userDetails.getUsername());
      tokenBuilder.append(":");
      tokenBuilder.append(expires);
      tokenBuilder.append(":");
      tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires));

      return tokenBuilder.toString();
   }


   /***
    * 计算数字签名，Hex.encode(MD5(拼接串))
    * 用户名：token失效毫秒数：用户密码：签名固定值
    * @param userDetails 用户对象
    * @param expires token 失效毫秒数
    * @return 返回计算的签名值
    */
   public static String computeSignature(UserDetails userDetails, long expires)
   {
      StringBuilder signatureBuilder = new StringBuilder();
      signatureBuilder.append(userDetails.getUsername());
      signatureBuilder.append(":");
      signatureBuilder.append(expires);
      signatureBuilder.append(":");
      signatureBuilder.append(userDetails.getPassword());
      signatureBuilder.append(":");
      signatureBuilder.append(TokenUtils.MAGIC_KEY);

      MessageDigest digest;
      try {
         digest = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
         throw new IllegalStateException("No MD5 algorithm available!");
      }

      return new String(org.springframework.security.crypto.codec.Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
   }


   /***
    * 根据token分割用户名
    * [用户名：失效毫秒数：计算的token签名]
    * 如："user:1468401003052:41f0a8ee0a79263b7f5f7ca4fe18b6b0"
    * @param authToken
    * @return
    */
   public static String getUserNameFromToken(String authToken){
      if (null == authToken) {
         return null;
      }

      String[] parts = authToken.split(":");
      return parts[0];
   }

   /***
    * 验证token是否合法
    * 1、判断token 是否过期
    * 2、判断token 对应的签名是否正确
    * [用户名：失效毫秒数：计算的token签名]
    * 如："user:1468401003052:41f0a8ee0a79263b7f5f7ca4fe18b6b0"
    * @param authToken token字符串
    * @param userDetails 用户对象
    * @return 返回是否合法 true 合法， false 不合法
    */
   public static boolean validateToken(String authToken, UserDetails userDetails){
      String[] parts = authToken.split(":");
      long expires = Long.parseLong(parts[1]);
      String signature = parts[2];

      if (expires < System.currentTimeMillis()) {
         return false;
      }
      return signature.equals(TokenUtils.computeSignature(userDetails, expires));
   }
}