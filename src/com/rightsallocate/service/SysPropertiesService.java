package com.rightsallocate.service;

import com.rightsallocate.common.Page;
import com.rightsallocate.dao.BaseDao;
import com.rightsallocate.model.SysProperties;

public interface SysPropertiesService extends BaseDao<SysProperties, String> {
	
	/**
	 * ��ҳ��ʼ��
	 * @param pno
	 */
	public void init(int pno);
	
	
	/**
	 * ��ҳʵ��
	 * @return
	 */
	public Page getPage();

}
