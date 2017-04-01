package com.xbmt.framework.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.xbmt.common.CommonUtils;
import com.xbmt.common.web.SpringContextUtil;

/**
 * 以SERVLET方式生成图片验证码<p>
 * @author LingMin 
 * @date 2015-08-13<br>
 * @version 1.0<br>
 */
public class JcaptchaAuthCodeServlet extends HttpServlet {
	/** 系统生成版本编号 **/
	private static final long serialVersionUID = -8155706769579451268L;
	/** 图片验证码生成器 **/
	private ImageCaptchaService captchaLogicBean;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JcaptchaAuthCodeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		captchaLogicBean = (ImageCaptchaService) SpringContextUtil.getInstance().getBean(ImageCaptchaService.class);
		// 合法性判断
		if (CommonUtils.isNotEmptyObject(captchaLogicBean)) {
			byte[] captchaChallengeAsJpeg = null;
			java.io.ByteArrayOutputStream jpegOutput = new java.io.ByteArrayOutputStream();
			try {
				java.awt.image.BufferedImage bufferImage = captchaLogicBean.getImageChallengeForID(request.getSession(false).getId(), request.getLocale());
				javax.imageio.ImageIO.write(bufferImage, "jpeg", jpegOutput);
			} catch (IllegalArgumentException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			} catch (CaptchaServiceException e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
			captchaChallengeAsJpeg = jpegOutput.toByteArray();
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/" + "jpeg");
			ServletOutputStream responseOutputStream = response.getOutputStream();
			responseOutputStream.write(captchaChallengeAsJpeg);
			responseOutputStream.flush();
			responseOutputStream.close();
		}
	}

}
