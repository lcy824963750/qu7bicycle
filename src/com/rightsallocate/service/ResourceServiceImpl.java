package com.rightsallocate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rightsallocate.dao.AbstractBaseDao;
import com.rightsallocate.model.Resource;

@Service
@Transactional
public class ResourceServiceImpl extends AbstractBaseDao<Resource, String> implements ResourceService {

}
