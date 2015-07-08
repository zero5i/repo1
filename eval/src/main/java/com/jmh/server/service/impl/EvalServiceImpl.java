package com.jmh.server.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmh.server.commom.SampleConstant;
import com.jmh.server.commom.base.AbsBaseService;
import com.jmh.server.commom.exception.SampleServiceException;
import com.jmh.server.commom.util.DateTimeUtil;
import com.jmh.server.commom.util.WeixinUtil;
import com.jmh.server.entity.CityEntity;
import com.jmh.server.entity.EvaluateEntity;
import com.jmh.server.entity.ShopEntity;
import com.jmh.server.entity.UserEntity;
import com.jmh.server.entity.WeixinEntity;
import com.jmh.server.service.IEvalService;


@Service
@Transactional
public class EvalServiceImpl extends AbsBaseService implements IEvalService{
	
	@Override
	public List<ShopEntity> getShopListByUserId(Long userId) {
		List<ShopEntity> list = shopDao.selectShopListByUserId(userId);
		
		return list;
	}

	@Override
	public ShopEntity getShopById(Long id) {
		ShopEntity entity = shopDao.selectShopById(id);
		
		if(entity == null){
			throw new SampleServiceException("未找到相关店铺信息");
		}
		
		return entity;
	}

	@Override
	public int deleteShop(Long empId) {
		return shopDao.deleteShopById(empId);
	}

	@Override
	public int updateShop(ShopEntity entity) {
		return shopDao.updateShop(entity);
	}

	@Override
	public ShopEntity insertShop(ShopEntity entity) {
		int cnt = shopDao.insertShop(entity);
		return cnt > 0 ? entity : null;
	}

	@Override
	public UserEntity getUserByOpenId(String openId) {
		return userDao.selectUserByOpenId(openId);
	}
	
	/**
	 * 获取微信持久状态的AccessToken<p>
	 * @return <p>
	 * String
	 */
	public String getAccessToken(){
		
		WeixinEntity weixin = weixinDao.selectWeixinByKey(SampleConstant.WEIXIN_ACCESS_TOKEN);
		
		// 不可用的时候,重新取token
		if(weixin == null 
				|| weixin.getExpiredFlag().intValue() == SampleConstant.WEIXIN_EXPIRED_FLAG_1 
				|| StringUtils.isEmpty(weixin.getHoldVal())){
			
			String accessToken = WeixinUtil.getRemoteAccessToken();
			if(StringUtils.isEmpty(accessToken)){
				throw new SampleServiceException("微信access_token取得失败.");
			}
				
			WeixinEntity entity = new WeixinEntity();
			entity.setKeyName(SampleConstant.WEIXIN_ACCESS_TOKEN);
			entity.setHoldVal(accessToken);
			int ret = 0;
			if(weixin == null){
				ret = weixinDao.insertWeixin(entity);	
			}else{
				ret = weixinDao.updateWeixin(entity);
			}
			
			if(ret == 0){
				throw new SampleServiceException("微信access_token持久化处理失败.");
			}
			
			return accessToken;
		}
		
		return weixin.getHoldVal();
	}
	
	/**
	 * 获取微信持久状态的jsapi_ticket<p>
	 * @return <p>
	 * String
	 */
	public String getJsapiTicket(){
		
		WeixinEntity weixin = weixinDao.selectWeixinByKey(SampleConstant.WEIXIN_JSAPI_TICKET);
		
		// 不可用的时候,重新取token
		if(weixin == null 
				|| weixin.getExpiredFlag().intValue() == SampleConstant.WEIXIN_EXPIRED_FLAG_1  
				|| StringUtils.isEmpty(weixin.getHoldVal())){

			String accessToken = this.getAccessToken();
			
			String jsapiTicket = WeixinUtil.getRemoteJsapiTicket(accessToken);
			if(StringUtils.isEmpty(jsapiTicket)){
				throw new SampleServiceException("微信jsapi_ticket取得失败.");
			}
			
			WeixinEntity entity = new WeixinEntity();
			entity.setKeyName(SampleConstant.WEIXIN_JSAPI_TICKET);
			entity.setHoldVal(jsapiTicket);
			int ret = 0;
			if(weixin == null){
				ret = weixinDao.insertWeixin(entity);	
			}else{
				ret = weixinDao.updateWeixin(entity);
			}
			
			if(ret == 0){
				throw new SampleServiceException("微信access_token持久化处理失败.");
			}
			
			return jsapiTicket;
		}
		
		return weixin.getHoldVal();
	}

	@Override
	public List<CityEntity> getProvinceList() {
		return cityDao.selectProvinceList();
	}

	@Override
	public List<CityEntity> getChildCityList(long provinceId) {
		return cityDao.selectCityListByParentId(provinceId);
	}

	@Override
	public EvaluateEntity evalShop(ShopEntity shopEntity, EvaluateEntity evaluate) {
		
		if(shopEntity == null){
			throw new SampleServiceException("非法操作.");
		}
		
		int ret = 0;
		if(shopEntity.getId() == null || shopEntity.getId() == 0L){
			ret = shopDao.insertShop(shopEntity);
		}else{
			ret = shopDao.updateShop(shopEntity);
		}
		
		if(ret == 0){
			throw new SampleServiceException("店铺创建失败.");
		}
		
		String currYearMonth = DateTimeUtil.getDateTimeStr(DateTimeUtil.PATTERN_21);
		
		Long shopId = shopEntity.getId();
		
		EvaluateEntity evalEntity = evaluateDao.selectEvaluate(shopId, currYearMonth);
		if(evalEntity == null){
			
			evaluate.setShopId(shopId);
			evaluate.setEvaluateDate(currYearMonth);
			
			// TODO 评测值设定
			evaluate.setEvaluateValue(4321L);
			
			int evalRet = evaluateDao.insertEvaluate(evaluate);
			if(evalRet == 0){
				throw new SampleServiceException("评测数据创建失败.");
			}
			
			return evaluate;
		}
	
		evalEntity.setMonthlyEnergy(evaluate.getMonthlyEnergy());
		evalEntity.setMonthlyOtherPay(evaluate.getMonthlyOtherPay());
		evalEntity.setMonthlyPurchase(evaluate.getMonthlyPurchase());
		evalEntity.setMonthlyRent(evaluate.getMonthlyRent());
		evalEntity.setMonthlySalary(evaluate.getMonthlySalary());
		evalEntity.setMonthlySales(evaluate.getMonthlySales());
		
		// TODO 评测值设定
		evalEntity.setEvaluateValue(1234L);
		
		evaluateDao.updateEvaluate(evalEntity);
		
		return evalEntity;
		
	}
}
