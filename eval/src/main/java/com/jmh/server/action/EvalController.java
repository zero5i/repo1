package com.jmh.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jmh.server.entity.CityEntity;
import com.jmh.server.entity.EvaluateEntity;
import com.jmh.server.entity.ShopEntity;
import com.jmh.server.entity.UserEntity;
import com.jmh.server.service.IEvalService;
import com.opensymphony.xwork2.ActionContext;


public class EvalController {
	
	private static Logger logger = Logger.getLogger(EvalController.class);  
	private static final String SESSION_USER = "session.user";
	
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
	 * 店铺列表
	 */
	private List<ShopEntity> shopList = new ArrayList<ShopEntity>(); 
	
	public String index() {
		
		String accessToken = evalService.getAccessToken();
		logger.debug(accessToken);
		String jsapiTicket = evalService.getJsapiTicket();
		logger.debug(jsapiTicket);
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
		
		UserEntity user = evalService.getUserByOpenId(openId);
		
		if(user == null){
			return ""; 
		}*/

		// TODO 获取用户在系统中的ID（非openid）
		UserEntity user = new UserEntity();
		user.setId(1L);
		
		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
        session.put(SESSION_USER, user);
		
		shopList = evalService.getShopListByUserId(user.getId());
		
		//model.addAttribute("shopList", shopList);
		return "success";
	}
	
	/**
	 * <p>店铺详细信息检索
	 * @return <p>
	 * String
	 */
	public String detail(){
		
		if(shopId > 0){
			this.shopEntity = evalService.getShopById(shopId);
			
			this.cityList = evalService.getChildCityList(this.shopEntity.getProvinceId());
		}else{
			this.shopEntity = new ShopEntity();
		}
		
		return "success";
	}
	
	/**
	 * <p>创建，修改,评测店铺
	 * @return <p>
	 * String
	 */
	public String evalShop(){			

		ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> session = actionContext.getSession();
        UserEntity user = (UserEntity)session.get(SESSION_USER);
        if(user == null){
        	this.evaluateEntity = null;
        	return "success";
        }
		
        this.shopEntity.setUserId(user.getId());
        
		this.evaluateEntity = evalService.evalShop(this.shopEntity, this.evaluateEntity);
		
		return "success";
	}
	
	/**
	 * <p>取得店铺信息AJAX
	 * @return <p>
	 * String
	 */
	public String findShop(){
		this.shopEntity = evalService.getShopById(shopId);
		
		if(shopEntity.getProvinceId() != null && shopEntity.getProvinceId() > 0){
			// 初始化所有省下的列表
			this.cityList = evalService.getChildCityList(shopEntity.getProvinceId());
		}
		return "success";
	}
	
	/**
	 * <p>取得城市列表AJAX
	 * @return <p>
	 * String
	 */
	public String findCityList(){
		this.cityList = evalService.getChildCityList(provinceId);
		return "success";
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
}
