package com.qu7bicycle.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qu7bicycle.common.SecurityCode;
import com.qu7bicycle.common.SecurityImage;

public class SecurityCodeImageAction extends ActionSupport {

	// 图片流
	private ByteArrayInputStream imageStream;
	private String timestamp;// 得到时间戳
	
	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;

	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String execute() throws Exception {
		// 获取默认难度和长度的验证码
		String securityCode = SecurityCode.getSecurityCode();
		imageStream = SecurityImage.getImageAsInputStream(securityCode);
		// 放入session中
		Map session = ActionContext.getContext().getSession();
		session.put("checkCode", securityCode);
		return SUCCESS;
	}
}
