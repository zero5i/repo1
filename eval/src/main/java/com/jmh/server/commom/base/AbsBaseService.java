package com.jmh.server.commom.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.jmh.server.dao.ICityDao;
import com.jmh.server.dao.IEvaluateDao;
import com.jmh.server.dao.IShopDao;
import com.jmh.server.dao.IUserDao;
import com.jmh.server.dao.IWeixinDao;



public abstract class AbsBaseService {

	@Autowired
	protected IWeixinDao weixinDao;

	@Autowired
	protected IShopDao shopDao;

	@Autowired
	protected IUserDao userDao;

	@Autowired
	protected IEvaluateDao evaluateDao;
	
	@Autowired
	protected ICityDao cityDao;
	
	
}
