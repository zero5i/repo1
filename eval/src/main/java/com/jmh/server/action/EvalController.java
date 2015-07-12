package com.jmh.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jmh.server.bean.EvalPageBean;
import com.jmh.server.bean.WeixinJsSDKBean;
import com.jmh.server.entity.CityEntity;
import com.jmh.server.entity.EvaluateEntity;
import com.jmh.server.entity.ShopEntity;
import com.jmh.server.entity.UserEntity;
import com.jmh.server.service.IEvalService;


public class EvalController {
	
	private static Logger logger = Logger.getLogger(EvalController.class);  
	
	private static final String SUCCESS = "success";
	private static final String INPUT = "input";
	
	@Autowired
	private IEvalService evalService;

	/**
	 * 店铺Id
	 */
	private long shopId = 0L;
	
	/**
	 * 省Id
	 */
	private long provinceId = 0L;
	
	/**
	 * 登陆Token
	 */
	private String loginToken = null;
	
	/**
	 * 区，市列表
	 */
	private List<CityEntity> cityList = new ArrayList<CityEntity>();
	
	/**
	 *  店铺信息
	 */
	private ShopEntity shopEntity = new ShopEntity();
	
	/**
	 * 店铺月开支
	 */
	private EvaluateEntity evaluateEntity = new EvaluateEntity();
	
	/**
	 * 评测结果Bean
	 */
	private EvalPageBean evalPageBean;
	
	/**
	 * 店铺列表
	 */
	private List<ShopEntity> shopList = new ArrayList<ShopEntity>(); 
	
	/**
	 * 评测记录idx
	 */
	private int validateTypeValue;
	
	/**
	 * 评测结果提示列表
	 */
	private List<String> evalTipsList;
	
	/**
	 * 微信相关参数信息
	 */
	private WeixinJsSDKBean weixinJsSDKBean;
	
	public String index() {
		
		//String code = request.getParameter("code");
		/*
		if(code == null){
			return null;
		}
		
		// 微信App.id
		String weixinAppSecret = CustomizedPropertyConfigurer.getContextProperty("weixin.app.secret");
		
		// 微信App.id
		String weixinAppId = CustomizedPropertyConfigurer.getContextProperty("weixin.app.id");
		
		String openId = WeixinUtil.getOpenId(weixinAppId, weixinAppSecret, code);
		*/
		
		// TODO DUMMY
		String openId = "oZf8yty0yxkzviCVi0sVutI95iB8";
		
		UserEntity user = evalService.getUserByOpenId(openId);
		
		if(user == null){
			return INPUT; 
		}
		
		this.loginToken = user.getLoginToken();
		
		//this.shopList = evalService.getShopListByUserId(user.getId());
		return SUCCESS;
	}
	
	/**
	 * <p>页面首页跳转目标
	 * @return <p>
	 * String
	 */
	public String f_index(){
		UserEntity user = (UserEntity)evalService.getUserByLoginToken(this.loginToken);
        if(user == null){
        	return INPUT;
        }
        HttpServletRequest request =  ServletActionContext.getRequest();
        this.weixinJsSDKBean = evalService.getWeixinJsSDKInfo(request.getRequestURL().toString());
        
        this.shopList = evalService.getShopListByUserId(user.getId());
        
		return SUCCESS;
	}
	
	/**
	 * <p>店铺详细信息检索
	 * @return <p>
	 * String
	 */
	public String detail(){
		
		UserEntity user = (UserEntity)evalService.getUserByLoginToken(this.loginToken);
        if(user == null){
        	this.shopEntity = null;
        	this.cityList = null;
        	return SUCCESS;
        }
        
		if(shopId > 0){
			this.shopEntity = evalService.getShopById(shopId);
			
			this.cityList = evalService.getChildCityList(this.shopEntity.getProvinceId());
		}else{
			this.shopEntity = new ShopEntity();
		}
		
		return SUCCESS;
	}
	
	/**
	 * <p>创建，修改,评测店铺</p>
	 * @return 
	 * String
	 */
	public String evalShop(){			
		
        UserEntity user = (UserEntity)evalService.getUserByLoginToken(this.loginToken);
        if(user == null){
        	this.evalPageBean = null;
        	return SUCCESS;
        }
		
        this.shopEntity.setUserId(user.getId());
        
        // 取得评测结果
		this.evalPageBean = evalService.evalShop(this.shopEntity, this.evaluateEntity);
		
		return SUCCESS;
	}
	
	/**
	 * <p>取得店铺信息AJAX</p>
	 * @return 
	 * String
	 */
	public String findShop(){
		this.shopEntity = evalService.getShopById(shopId);
		
		if(shopEntity.getProvinceId() != null && shopEntity.getProvinceId() > 0){
			// 初始化所有省下的列表
			this.cityList = evalService.getChildCityList(shopEntity.getProvinceId());
		}
		return SUCCESS;
	}
	
	/**
	 * <p>通过店铺ID取得该店铺所有评测记录</p>
	 * @return 
	 * String
	 */
	public String findEvalList(){
		UserEntity user = (UserEntity)evalService.getUserByLoginToken(this.loginToken);
        if(user == null){
        	this.evalPageBean = null;
        	return SUCCESS;
        }
        
		this.evalPageBean = evalService.getEvalList(this.shopId);
		
		return SUCCESS;
	}
	
	/**
	 * <p>取得城市列表AJAX
	 * @return <p>
	 * String
	 */
	public String findCityList(){
		this.cityList = evalService.getChildCityList(this.provinceId);
		return SUCCESS;
	}
	
	/**
	 * <p>取得评测结果提示详细
	 * @return <p>
	 * String
	 */
	public String findEvalValidate(){
		this.evalTipsList = evalService.getEvalValidateBean(this.validateTypeValue);
		return SUCCESS;
	}
	
	/**
	 * <p>获取 shopList</p>
	 * @return  shopList  shopList<br>
	 */
	public List<ShopEntity> getShopList() {
		return shopList;
	}

	/**
	 * <p>设置 shopList</p>
	 * @param  shopList  shopList<br>
	 */
	public void setShopList(List<ShopEntity> shopList) {
		this.shopList = shopList;
	}

	/**
	 * <p>获取 店铺信息</p>
	 * @return  shopEntity  店铺信息<br>
	 */
	public ShopEntity getShopEntity() {
		return shopEntity;
	}

	/**
	 * <p>设置 店铺信息</p>
	 * @param  shopEntity  店铺信息<br>
	 */
	public void setShopEntity(ShopEntity shopEntity) {
		this.shopEntity = shopEntity;
	}

	/**
	 * <p>获取 shopId</p>
	 * @return  shopId  shopId<br>
	 */
	public long getShopId() {
		return shopId;
	}

	/**
	 * <p>设置 shopId</p>
	 * @param  shopId  shopId<br>
	 */
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	/**
	 * <p>获取 省Id</p>
	 * @return  provinceId  省Id<br>
	 */
	public long getProvinceId() {
		return provinceId;
	}

	/**
	 * <p>设置 省Id</p>
	 * @param  provinceId  省Id<br>
	 */
	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * <p>获取 区，市列表</p>
	 * @return  cityList  区，市列表<br>
	 */
	public List<CityEntity> getCityList() {
		return cityList;
	}

	/**
	 * <p>设置 区，市列表</p>
	 * @param  cityList  区，市列表<br>
	 */
	public void setCityList(List<CityEntity> cityList) {
		this.cityList = cityList;
	}

	/**
	 * <p>获取 店铺月开支</p>
	 * @return  evaluateEntity  店铺月开支<br>
	 */
	public EvaluateEntity getEvaluateEntity() {
		return evaluateEntity;
	}

	/**
	 * <p>设置 店铺月开支</p>
	 * @param  evaluateEntity  店铺月开支<br>
	 */
	public void setEvaluateEntity(EvaluateEntity evaluateEntity) {
		this.evaluateEntity = evaluateEntity;
	}

	/**
	 * <p>获取 登陆Token</p>
	 * @return  loginToken  登陆Token<br>
	 */
	public String getLoginToken() {
		return loginToken;
	}

	/**
	 * <p>设置 登陆Token</p>
	 * @param  loginToken  登陆Token<br>
	 */
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	/**
	 * <p>获取 evalService</p>
	 * @return  evalService  evalService<br>
	 */
	public IEvalService getEvalService() {
		return evalService;
	}

	/**
	 * <p>设置 evalService</p>
	 * @param  evalService  evalService<br>
	 */
	public void setEvalService(IEvalService evalService) {
		this.evalService = evalService;
	}

	/**
	 * <p>获取 评测结果Bean</p>
	 * @return  evalPageBean  评测结果Bean<br>
	 */
	public EvalPageBean getEvalPageBean() {
		return evalPageBean;
	}

	/**
	 * <p>设置 评测结果Bean</p>
	 * @param  evalPageBean  评测结果Bean<br>
	 */
	public void setEvalPageBean(EvalPageBean evalPageBean) {
		this.evalPageBean = evalPageBean;
	}

	/**
	 * <p>获取 评测记录idx</p>
	 * @return  validateTypeValue  评测记录idx<br>
	 */
	public int getValidateTypeValue() {
		return validateTypeValue;
	}

	/**
	 * <p>设置 评测记录idx</p>
	 * @param  validateTypeValue  评测记录idx<br>
	 */
	public void setValidateTypeValue(int validateTypeValue) {
		this.validateTypeValue = validateTypeValue;
	}

	/**
	 * <p>获取 评测结果提示列表</p>
	 * @return  evalTipsList  评测结果提示列表<br>
	 */
	public List<String> getEvalTipsList() {
		return evalTipsList;
	}

	/**
	 * <p>设置 评测结果提示列表</p>
	 * @param  evalTipsList  评测结果提示列表<br>
	 */
	public void setEvalTipsList(List<String> evalTipsList) {
		this.evalTipsList = evalTipsList;
	}

	/**
	 * <p>获取 微信相关参数信息</p>
	 * @return  weixinJsSDKBean  微信相关参数信息<br>
	 */
	public WeixinJsSDKBean getWeixinJsSDKBean() {
		return weixinJsSDKBean;
	}

	/**
	 * <p>设置 微信相关参数信息</p>
	 * @param  weixinJsSDKBean  微信相关参数信息<br>
	 */
	public void setWeixinJsSDKBean(WeixinJsSDKBean weixinJsSDKBean) {
		this.weixinJsSDKBean = weixinJsSDKBean;
	}
}
