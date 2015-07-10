package com.jmh.server.service.impl;

import java.math.BigDecimal;
import java.security.MessageDigest;
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
		
		UserEntity userEntity = userDao.selectUserByOpenId(openId);
		
		if(userEntity == null){
			return null;
		}
		
		String s = userEntity.getId() + DateTimeUtil.getCurrentTime();
		
		String loginToken = this.encryptLoginToken(s);
		
		userEntity.setLoginToken(loginToken);
		
		int ret = userDao.updateUser(userEntity);
		
		return ret == 0 ? null : userEntity;
	}
	
	private  String encryptLoginToken(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
			throw new SampleServiceException("店铺创建/更新处理失败.");
		}
		
		String currYearMonth = DateTimeUtil.getDateTimeStr(DateTimeUtil.PATTERN_21);
		
		BigDecimal val = this.getEvalValue(evaluate);
				
		Long shopId = shopEntity.getId();
		
		EvaluateEntity evalEntity = evaluateDao.selectEvaluate(shopId, currYearMonth);
		if(evalEntity == null){
			
			evaluate.setShopId(shopId);
			evaluate.setEvaluateDate(currYearMonth);

			evaluate.setEvaluateValue(val);
			
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
		evalEntity.setEvaluateValue(val);
		
		evaluateDao.updateEvaluate(evalEntity);
		
		return evalEntity;
		
	}
	
	private BigDecimal getEvalValue(EvaluateEntity evaluate){

		// 月销售额
		BigDecimal monthlySales = evaluate.getMonthlySales();
		
		// 月采购额
		BigDecimal monthlyPurchase = evaluate.getMonthlyPurchase();
		
		// 每月工资
		BigDecimal monthlySalary = evaluate.getMonthlySalary();
		
		// 每月租金
		BigDecimal monthlyRent = evaluate.getMonthlyRent();
		
		// 每月能耗
		BigDecimal monthlyEnergy = evaluate.getMonthlyEnergy();
		
		// 其他开销
		BigDecimal monthlyOtherPay = evaluate.getMonthlyOtherPay();
		
		// 团购收入
		BigDecimal monthlyGroupBuy = evaluate.getMonthlyGroupBuy();
		
		BigDecimal val = monthlySales.subtract(monthlyPurchase).subtract(monthlySalary).subtract(monthlyRent).subtract(monthlyEnergy).subtract(monthlyOtherPay);
		
		return val;
	}

	@Override
	public UserEntity getUserByLoginToken(String loginToken) {
		return userDao.selectUserByLoginToken(loginToken);
	}
}
