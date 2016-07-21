package com.rightsallocate.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rightsallocate.common.SecurityCode;
import com.rightsallocate.common.SecurityImage;

public class SecurityCodeImageAction extends ActionSupport {

	// ͼƬ��
	private ByteArrayInputStream imageStream;
	private String timestamp;// �õ�ʱ���
	
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
		// ��ȡĬ���ѶȺͳ��ȵ���֤��
		String securityCode = SecurityCode.getSecurityCode();
		imageStream = SecurityImage.getImageAsInputStream(securityCode);
		// ����session��
		Map session = ActionContext.getContext().getSession();
		session.put("checkCode", securityCode);
		return SUCCESS;
	}
}
