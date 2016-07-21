package com.rightsallocate.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.rightsallocate.common.Page;
import com.rightsallocate.model.Organization;
import com.rightsallocate.model.SysProperties;
import com.rightsallocate.model.User;
import com.rightsallocate.service.OrganizationServiceImpl;
import com.rightsallocate.service.SysPropertiesServiceImpl;
import com.rightsallocate.service.UserServiceImpl;

public class UserAction extends ActionSupport {

	@Resource(name = "userService")
	private UserServiceImpl userService;
	@Resource(name = "sysPropertiesService")
	private SysPropertiesServiceImpl sysPropertiesService;
	@Resource(name="organizationService")
	private OrganizationServiceImpl organizationService;
	

	private String username;
	private String password;
	private String checkCode;

	public String exit() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("adminUser", null); // �洢�û���Ϣ
		session.setAttribute("myPage", null);
		session.setAttribute("sysProperties", null);
		return SUCCESS;
	}

	public String login() {
		// �����֤��
		HttpSession session = ServletActionContext.getRequest().getSession();
		String checkCode = (String) session.getAttribute("checkCode");
		if (!checkCode.equals(this.checkCode.toLowerCase())) {
			addFieldError("validateError", "��֤����������");
			return "input";
		}
		// ��֤�û���������
		User user = userService.login(username, password);
		if (user == null) {
			addFieldError("validateError", "�û��������������");
			return "input";
		}

		// ��֤ͨ���� ���û��������Ϣ��ŵ�session��
		session.setAttribute("adminUser", user); // �洢�û���Ϣ
		sysPropertiesService.init(0);
		Page myPage = sysPropertiesService.getPage();
		List myList = myPage.getList();
		
		//�洢��ҳ��Ϣ
		session.setAttribute("myPage", myPage);
		session.setAttribute("sysProperties", myList);
	
		//�洢��֯������Ϣ
		List<Organization> organizationList = organizationService.findAll();
		Organization currentOrganization = user.getOrganization();
		if(currentOrganization!=null){
			int index = -1;
			for (int i = 0; i < organizationList.size(); i++) {
				if(organizationList.get(i).getOid().equals(currentOrganization.getOid())) {
					index = i;
					break;
				}
			}
			List<Organization> organList = new ArrayList<Organization>();
			organList.add(organizationList.get(index));
			for (int i = 0; i < index; i++) {
				organList.add(organizationList.get(i));
			}
			for (int i = index; i < organizationList.size(); i++) {
				organList.add(organizationList.get(i));
			}
			session.setAttribute("organizations", organList);
			session.setAttribute("hasOrganization", true);
		}else {
			session.setAttribute("organizations", organizationList);
			session.setAttribute("hasOrganization", false);
		}
		return SUCCESS;
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public SysPropertiesServiceImpl getSysPropertiesService() {
		return sysPropertiesService;
	}

	public void setSysPropertiesService(
			SysPropertiesServiceImpl sysPropertiesService) {
		this.sysPropertiesService = sysPropertiesService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public OrganizationServiceImpl getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationServiceImpl organizationService) {
		this.organizationService = organizationService;
	}

}
