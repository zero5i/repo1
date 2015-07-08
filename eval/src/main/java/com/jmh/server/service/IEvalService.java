package com.jmh.server.service;

import java.util.List;

import com.jmh.server.entity.CityEntity;
import com.jmh.server.entity.EvaluateEntity;
import com.jmh.server.entity.ShopEntity;
import com.jmh.server.entity.UserEntity;

public interface IEvalService {
	
	/**
	 * <p>取得店铺</p>
	 * @param shopId 
	 * @param userId
	 * @return
	 * ShopEntity
	 */
	public ShopEntity getShopById(Long shopId);
	
	/**
	 * <p>通过openId获取用户
	 * @param openId
	 * @return <p>
	 * UserEntity
	 */
	public UserEntity getUserByOpenId(String openId);
	
	/**
	 * <p>取得店铺列表</p>
	 * @param 
	 * @return 
	 * List<ShopEntity>
	 */
	public List<ShopEntity> getShopListByUserId(Long userId);

	/**
	 * <p>修改雨店铺信息</p>
	 * @param shop
	 * @return 修改数目
	 * int
	 */
	public int updateShop(ShopEntity shop);
	
	/**
	 * <p>创建店铺</p>
	 * @param shop
	 * @return
	 * ShopEntity
	 */
	public ShopEntity insertShop(ShopEntity shop);
	
	/**
	 * <p>删除店铺</p>
	 * @param id
	 * @return 
	 * int
	 */
	public int deleteShop(Long id);
	
	/**
	 * <p>持久状态的AccessToken取得
	 * @return <p>
	 * String
	 */
	public String getAccessToken();
	
	/**
	 * 持久状态的JsapiTicket取得<p>
	 * @return <p>
	 * String
	 */
	public String getJsapiTicket();
	
	/**
	 * <p> 取得所有省
	 * @return <p>
	 * List<CityEntity>
	 */
	public List<CityEntity> getProvinceList();

	/**
	 * <p>取得市，区城市列表
	 * @param provinceId
	 * @return <p>
	 * ShopEntity
	 */
	public List<CityEntity> getChildCityList(long provinceId);

	/**
	 * <p>评测店铺
	 * @param shopEntity
	 * @param evaluateEntity
	 * @return <p>
	 * EvaluateEntity
	 */
	public EvaluateEntity evalShop(ShopEntity shopEntity,	EvaluateEntity evaluateEntity);
}
