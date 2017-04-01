package com.xbmt.auth2;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.xbmt.utils.HandleResultUtils;


/***
 * 重写 摘要认证切点 类，用于返回json格式
 * @author admin
 *
 */
public class MyDigestAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean, Ordered {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(MyDigestAuthenticationEntryPoint.class);

    //~ Instance fields ================================================================================================

    private String key;
    private String realmName;
    private int nonceValiditySeconds = 300;
    private int order = Integer.MAX_VALUE; // ~ default

    //~ Methods ========================================================================================================

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void afterPropertiesSet() throws Exception {
        if ((realmName == null) || "".equals(realmName)) {
            throw new IllegalArgumentException("realmName must be specified");
        }

        if ((key == null) || "".equals(key)) {
            throw new IllegalArgumentException("key must be specified");
        }
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

      //计算 签名 
      	String nonceValueBase64 = MyTokenUtils.getNonceValueBase64(key, nonceValiditySeconds);
      	String authenticateHeader = MyTokenUtils.getAuthenticateHeader(realmName ,  nonceValueBase64);
        
        if (authException instanceof NonceExpiredException) {
            authenticateHeader = authenticateHeader + ", stale=\"true\"";
        }

        if (logger.isDebugEnabled()) {
            logger.debug("WWW-Authenticate header sent to user agent: " + authenticateHeader);
        }
        //Access-Control-Expose-Headers: X-Foo, .
        httpResponse.addHeader(MyTokenUtils.RespAuthHeaderKey, authenticateHeader);
        /*httpResponse.setHeader("Access-Control-Expose-Headers", "WWW-Authenticate");//开发客户端 读取头的权限  测试不起作用
         */
        /***2016-07-15开发调试时，可以通过向客户端浏览器 返回http 401错误，这样浏览器就会弹出窗口提示登录，登录成功后 就可以访问接口
         * 如果部署正式环境后，需要返回json 格式 code 401错误码，这样由客户端自行控制 前台逻辑
         */
        //httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        
        //直接 返回 401 未认证错误，要求前台必须登录后才 才可以进行访问
  	    HandleResultUtils.sendResponseHandleResult(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage() ,new AuthenticateParamVo(MyTokenUtils.RespAuthHeaderKey , MyTokenUtils.ReqAuthHeaderKey , realmName , key , nonceValiditySeconds , MyTokenUtils.SplitChar , MyTokenUtils.Qop , nonceValueBase64 , authenticateHeader));
    }

    public String getKey() {
        return key;
    }

    public int getNonceValiditySeconds() {
        return nonceValiditySeconds;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setNonceValiditySeconds(int nonceValiditySeconds) {
        this.nonceValiditySeconds = nonceValiditySeconds;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }
}