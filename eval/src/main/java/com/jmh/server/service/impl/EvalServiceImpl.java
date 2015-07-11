package com.jmh.server.service.impl;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmh.server.bean.EvalChartBean;
import com.jmh.server.bean.EvalPageBean;
import com.jmh.server.bean.EvalValidateBean;
import com.jmh.server.commom.EvalConstant;
import com.jmh.server.commom.base.AbsBaseService;
import com.jmh.server.commom.enmu.ColorType;
import com.jmh.server.commom.enmu.EvalStatusType;
import com.jmh.server.commom.enmu.EvalValidateType;
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
		
		for(ShopEntity shop : list){
			EvaluateEntity evalEntity = evaluateDao.selectLatestEvaluateByShopId(shop.getId());
			if( evalEntity != null ){
				
				// 设定最近一次的盈利结果
				shop.setEvaluateValue(evalEntity.getEvaluateValue());
			}
		}
		
		return list;
	}

	@Override
	public ShopEntity getShopById(Long id) {
		ShopEntity shop = shopDao.selectShopById(id);
		
		if(shop == null){
			throw new SampleServiceException("未找到相关店铺信息");
		}
		
		EvaluateEntity evalEntity = evaluateDao.selectLatestEvaluateByShopId(shop.getId());
		if( evalEntity != null ){
			
			// 设定最近一次的盈利结果
			shop.setEvaluateValue(evalEntity.getEvaluateValue());
		}
		
		return shop;
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
		
		WeixinEntity weixin = weixinDao.selectWeixinByKey(EvalConstant.WEIXIN_ACCESS_TOKEN);
		
		// 不可用的时候,重新取token
		if(weixin == null 
				|| weixin.getExpiredFlag().intValue() == EvalConstant.WEIXIN_EXPIRED_FLAG_1 
				|| StringUtils.isEmpty(weixin.getHoldVal())){
			
			String accessToken = WeixinUtil.getRemoteAccessToken();
			if(StringUtils.isEmpty(accessToken)){
				throw new SampleServiceException("微信access_token取得失败.");
			}
				
			WeixinEntity entity = new WeixinEntity();
			entity.setKeyName(EvalConstant.WEIXIN_ACCESS_TOKEN);
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
		
		WeixinEntity weixin = weixinDao.selectWeixinByKey(EvalConstant.WEIXIN_JSAPI_TICKET);
		
		// 不可用的时候,重新取token
		if(weixin == null 
				|| weixin.getExpiredFlag().intValue() == EvalConstant.WEIXIN_EXPIRED_FLAG_1  
				|| StringUtils.isEmpty(weixin.getHoldVal())){

			String accessToken = this.getAccessToken();
			
			String jsapiTicket = WeixinUtil.getRemoteJsapiTicket(accessToken);
			if(StringUtils.isEmpty(jsapiTicket)){
				throw new SampleServiceException("微信jsapi_ticket取得失败.");
			}
			
			WeixinEntity entity = new WeixinEntity();
			entity.setKeyName(EvalConstant.WEIXIN_JSAPI_TICKET);
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
	public UserEntity getUserByLoginToken(String loginToken) {
		if(StringUtils.isEmpty(loginToken)){
			return null;
		}
		
		return userDao.selectUserByLoginToken(loginToken);
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
	public EvalPageBean evalShop(ShopEntity shopEntity, EvaluateEntity evaluate) {
		
		EvalPageBean retBean = new EvalPageBean();
		
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
		
		BigDecimal evaluateValue = this.getEvalValue(evaluate);
		retBean.setEvaluateValue(evaluateValue);
		
		Long shopId = shopEntity.getId();
		
		EvaluateEntity evalEntity = evaluateDao.selectEvaluate(shopId, currYearMonth);
		if(evalEntity == null){
			
			evaluate.setShopId(shopId);
			evaluate.setEvaluateDate(currYearMonth);

			evaluate.setEvaluateValue(evaluateValue);
			
			this.parseEvalValidateBean(shopEntity, evaluate, retBean);
			
			int evalRet = evaluateDao.insertEvaluate(evaluate);
			if(evalRet == 0){
				throw new SampleServiceException("评测数据创建失败.");
			}
			
			return retBean;
		}
	
		evalEntity.setMonthlyEnergy(evaluate.getMonthlyEnergy());
		evalEntity.setMonthlyOtherPay(evaluate.getMonthlyOtherPay());
		evalEntity.setMonthlyPurchase(evaluate.getMonthlyPurchase());
		evalEntity.setMonthlyRent(evaluate.getMonthlyRent());
		evalEntity.setMonthlySalary(evaluate.getMonthlySalary());
		evalEntity.setMonthlySales(evaluate.getMonthlySales());

		evalEntity.setEvaluateValue(evaluateValue);
		
		this.parseEvalValidateBean(shopEntity, evalEntity, retBean);
		
		evaluateDao.updateEvaluate(evalEntity);
		
		return retBean;
	}
	
	/**
	 * <p>项目诊断结果设定
	 * @param retBean <p>
	 * void
	 */
	private void parseEvalValidateBean(ShopEntity shopEntity, EvaluateEntity evalEntity, EvalPageBean retBean){
		
		List<EvalValidateBean> validateBeanList = new ArrayList<EvalValidateBean>();
		
		// 绿数
		int greeCnt = 0;
		
		// 黄数
		int yellowCnt = 0;
		
		// 红数
		int redCnt = 0;
		
		float evaluateValue = evalEntity.getEvaluateValue().floatValue();
		
		// 前厅面积
		BigDecimal spaceSize = shopEntity.getSpaceSize();
		
		// 餐位总数
		Integer posCount = shopEntity.getPosCount();
		
		// 菜品数量 // TODO 
		int foodCnt = 0;
		
		// 月销售额
		BigDecimal monthlySales = evalEntity.getMonthlySales();
				
		// 月采购额
		BigDecimal monthlyPurchase = evalEntity.getMonthlyPurchase();
		
		// 每月工资
		BigDecimal monthlySalary = evalEntity.getMonthlySalary();
		
		// 每月租金
		BigDecimal monthlyRent = evalEntity.getMonthlyRent();
		 
		// 每月能耗
		BigDecimal monthlyEnergy = evalEntity.getMonthlyEnergy();
		
		// 其他开销
		BigDecimal monthlyOtherPay = evalEntity.getMonthlyOtherPay();
		monthlyOtherPay = monthlyOtherPay == null ? BigDecimal.ZERO : monthlyOtherPay;
		
		//  团购收入 
		BigDecimal monthlyGroupBuy = evalEntity.getMonthlyGroupBuy();
		monthlyGroupBuy = monthlyGroupBuy == null ? BigDecimal.ZERO : monthlyGroupBuy;
		
		// 餐位数 评测值 = 前厅面积 / 餐位总数
		EvalValidateBean bean1 = new EvalValidateBean();
		float val1 = spaceSize.divide(BigDecimal.valueOf(posCount), 1, BigDecimal.ROUND_HALF_UP).floatValue();
		if(val1 > 1.2 && val1 < 1.4){
			bean1.setColor(ColorType.绿.getValue());
			bean1.setStatusLabel(ColorType.绿.getTips());
			greeCnt++;
		}else if((val1 >= 1.0 && val1 <= 1.2) || (val1 >= 1.4 && val1 <= 1.5)){
			bean1.setColor(ColorType.黄.getValue());
			bean1.setStatusLabel(ColorType.黄.getTips());
			yellowCnt++;
		}else if(val1 < 1.0 || val1 > 1.5){
			bean1.setColor(ColorType.红.getValue());
			bean1.setStatusLabel(ColorType.红.getTips());
			redCnt++;
		}
		bean1.setIdx(EvalValidateType.餐位数.getValue());
		bean1.setLabelName(EvalValidateType.餐位数.getLabel());
		bean1.setNormalRange("1.2~1.4");
		bean1.setScope(String.valueOf(val1));
		validateBeanList.add(bean1);
		
		// 毛利率 评测值 = （1 - 月采购额 / 月销售额）* 100%
		EvalValidateBean bean2 = new EvalValidateBean();
		float val2Tmp = monthlyPurchase.divide(monthlySales, 2, BigDecimal.ROUND_HALF_UP).floatValue();
		int val2 = (int)((1 - val2Tmp) * 100);
		if(val2 >= 63 && val2 <= 70){
			bean2.setColor(ColorType.绿.getValue());
			bean2.setStatusLabel(ColorType.绿.getTips());
			greeCnt++;
		}else if(val2 >= 58 && val2 < 63){
			bean2.setColor(ColorType.黄.getValue());
			bean2.setStatusLabel(ColorType.黄.getTips());
			yellowCnt++;
		}else if(val2 < 58 || val2 > 70){
			bean2.setColor(ColorType.红.getValue());
			bean2.setStatusLabel(ColorType.红.getTips());
			redCnt++;
		}
		bean2.setIdx(EvalValidateType.毛利率.getValue());
		bean2.setLabelName(EvalValidateType.毛利率.getLabel());
		bean2.setNormalRange("63%~70%");
		bean2.setScope(String.valueOf(val2) + "%");
		validateBeanList.add(bean2);
		
		// 菜品数量
		EvalValidateBean bean3 = new EvalValidateBean();		
		// 前厅面积小于100平方米
		if(spaceSize.intValue() <= 100){
			if(foodCnt >= 80 && foodCnt <= 100){
				bean3.setColor(ColorType.绿.getValue());
				bean3.setStatusLabel(ColorType.绿.getTips());
				greeCnt++;
			}else if((foodCnt >= 60 && foodCnt < 80) || (foodCnt > 100 && foodCnt <= 120)){
				bean3.setColor(ColorType.黄.getValue());
				bean3.setStatusLabel(ColorType.黄.getTips());
				yellowCnt++;
			}else if(foodCnt < 80 || foodCnt > 120){
				bean3.setColor(ColorType.红.getValue());
				bean3.setStatusLabel(ColorType.红.getTips());
				redCnt++;
			}
			bean3.setNormalRange("80~100");
		}else if(spaceSize.intValue() > 100 && spaceSize.intValue() < 150){
			if(foodCnt >= 100 && foodCnt <= 120){
				bean3.setColor(ColorType.绿.getValue());
				bean3.setStatusLabel(ColorType.绿.getTips());
				greeCnt++;
			}else if((foodCnt >= 80 && foodCnt < 100) || (foodCnt > 120 && foodCnt <= 140)){
				bean3.setColor(ColorType.黄.getValue());
				bean3.setStatusLabel(ColorType.黄.getTips());
				yellowCnt++;
			}else if(foodCnt < 100 || foodCnt > 140){
				bean3.setColor(ColorType.红.getValue());
				bean3.setStatusLabel(ColorType.红.getTips());
				redCnt++;
			}
			bean3.setNormalRange("100~120");
		}else if(spaceSize.intValue() >= 150){
			if(foodCnt >= 120 && foodCnt <= 140){
				bean3.setColor(ColorType.绿.getValue());
				bean3.setStatusLabel(ColorType.绿.getTips());
				greeCnt++;
			}else if((foodCnt >= 100 && foodCnt < 120) || (foodCnt > 140 && foodCnt <= 160)){
				bean3.setColor(ColorType.黄.getValue());
				bean3.setStatusLabel(ColorType.黄.getTips());
				yellowCnt++;
			}else if(foodCnt < 100 || foodCnt > 160){
				bean3.setColor(ColorType.红.getValue());
				bean3.setStatusLabel(ColorType.红.getTips());
				redCnt++;
			}
			bean3.setNormalRange("120~140");
		}
		bean3.setScope(String.valueOf(foodCnt));
		bean3.setIdx(EvalValidateType.菜品数量.getValue());
		bean3.setLabelName(EvalValidateType.菜品数量.getLabel());
		validateBeanList.add(bean3);
		
		// 团购售卖 月团购收入 / 月销售额
		EvalValidateBean bean4 = new EvalValidateBean();
		int val4 = (int)(monthlyGroupBuy.divide(monthlySales, 2, BigDecimal.ROUND_HALF_UP).floatValue() * 100);
		if(val4 >= 7 && val4 <= 15){
			bean4.setColor(ColorType.绿.getValue());
			bean4.setStatusLabel(ColorType.绿.getTips());
			greeCnt++;
		}else if((val4 >= 0 && val4 < 7) || (val4 > 15 && val4 <= 25)){
			bean4.setColor(ColorType.黄.getValue());
			bean4.setStatusLabel(ColorType.黄.getTips());
			yellowCnt++;
		}else if(val4 < 0 || val4 > 25){
			bean4.setColor(ColorType.红.getValue());
			bean4.setStatusLabel(ColorType.红.getTips());
			redCnt++;
		}
		bean4.setIdx(EvalValidateType.团购售卖.getValue());
		bean4.setLabelName(EvalValidateType.团购售卖.getLabel());
		bean4.setNormalRange("7%~15%");
		bean4.setScope(String.valueOf(val4) + "%");
		validateBeanList.add(bean4);
		
		// 人力成本 每月工资 / 月销售额
		EvalValidateBean bean5 = new EvalValidateBean();
		int val5 = (int)(monthlySalary.divide(monthlySales, 2, BigDecimal.ROUND_HALF_UP).floatValue() * 100);
		if(val5 >= 19 && val5 <= 24){
			bean5.setColor(ColorType.绿.getValue());
			bean5.setStatusLabel(ColorType.绿.getTips());
			greeCnt++;
		}else if((val5 > 24 && val5 <= 30)){
			bean5.setColor(ColorType.黄.getValue());
			bean5.setStatusLabel(ColorType.黄.getTips());
			yellowCnt++;
		}else if(val5 < 19 || val5 > 30){
			bean5.setColor(ColorType.红.getValue());
			bean5.setStatusLabel(ColorType.红.getTips());
			redCnt++;
		}
		bean5.setIdx(EvalValidateType.人力成本.getValue());
		bean5.setLabelName(EvalValidateType.人力成本.getLabel());
		bean5.setNormalRange("19%~24%");
		bean5.setScope(String.valueOf(val5) + "%");
		validateBeanList.add(bean5);
		
		// 采购  月采购额 / 月销售额
		EvalValidateBean bean6 = new EvalValidateBean();
		int val6 = (int)(monthlyPurchase.divide(monthlySales, 2, BigDecimal.ROUND_HALF_UP).floatValue() * 100);
		if(val6 >= 30 && val6 <= 37){
			bean6.setColor(ColorType.绿.getValue());
			bean6.setStatusLabel(ColorType.绿.getTips());
			greeCnt++;
		}else if((val6 > 37 && val6 <= 42)){
			bean6.setColor(ColorType.黄.getValue());
			bean6.setStatusLabel(ColorType.黄.getTips());
			yellowCnt++;
		}else if(val6 < 30 || val6 > 42){
			bean6.setColor(ColorType.红.getValue());
			bean6.setStatusLabel(ColorType.红.getTips());
			redCnt++;
		}
		bean6.setIdx(EvalValidateType.采购.getValue());
		bean6.setLabelName(EvalValidateType.采购.getLabel());
		bean6.setNormalRange("30%~37%");
		bean6.setScope(String.valueOf(val6) + "%");
		validateBeanList.add(bean6);
		
		// 租金成本 每月租金 / 月销售额
		EvalValidateBean bean7 = new EvalValidateBean();
		int val7 = (int)(monthlyRent.divide(monthlySales, 2, BigDecimal.ROUND_HALF_UP).floatValue() * 100);
		if(val7 >= 15 && val7 <= 20){
			bean7.setColor(ColorType.绿.getValue());
			bean7.setStatusLabel(ColorType.绿.getTips());
			greeCnt++;
		}else if((val7 > 20 && val7 <= 25)){
			bean7.setColor(ColorType.黄.getValue());
			bean7.setStatusLabel(ColorType.黄.getTips());
			yellowCnt++;
		}else if(val7 < 15 || val7 > 25){
			bean7.setColor(ColorType.红.getValue());
			bean7.setStatusLabel(ColorType.红.getTips());
			redCnt++;
		}
		bean7.setIdx(EvalValidateType.租金成本.getValue());
		bean7.setLabelName(EvalValidateType.租金成本.getLabel());
		bean7.setNormalRange("15%~20%");
		bean7.setScope(String.valueOf(val7) + "%");
		validateBeanList.add(bean7);
		
		// 能耗成本 每月租金 / 月销售额
		EvalValidateBean bean8 = new EvalValidateBean();
		int val8 = (int)(monthlyEnergy.divide(monthlySales, 2, BigDecimal.ROUND_HALF_UP).floatValue() * 100);
		if(val8 >= 2 && val8 <= 4){
			bean8.setColor(ColorType.绿.getValue());
			bean8.setStatusLabel(ColorType.绿.getTips());
			greeCnt++;
		}else if((val8 > 4 && val8 <= 6)){
			bean8.setColor(ColorType.黄.getValue());
			bean8.setStatusLabel(ColorType.黄.getTips());
			yellowCnt++;
		}else if(val8 < 2 || val8 > 6){
			bean8.setColor(ColorType.红.getValue());
			bean8.setStatusLabel(ColorType.红.getTips());
			redCnt++;
		}
		bean8.setIdx(EvalValidateType.能耗成本.getValue());
		bean8.setLabelName(EvalValidateType.能耗成本.getLabel());
		bean8.setNormalRange("2%~4%");
		bean8.setScope(String.valueOf(val8) + "%");
		validateBeanList.add(bean8);
		
		retBean.setValidateBeanList(validateBeanList);
		
		// 状况类型设定
		
		// 扩张型 绿 >= 5项，且盈利
		if(evaluateValue > 0 && greeCnt >= 5){
			retBean.setEvalTypeCode(EvalStatusType.扩张型.getCode());
			retBean.setEvalType(EvalStatusType.扩张型.getLabel());
			retBean.setEvalTypeMsg(EvalStatusType.扩张型.getMsg());
		}
		
		// 健康型 绿+黄 >= 6项，且盈利
		if(evaluateValue > 0 && (greeCnt + yellowCnt) >= 6){
			retBean.setEvalTypeCode(EvalStatusType.健康型.getCode());
			retBean.setEvalType(EvalStatusType.健康型.getLabel());
			retBean.setEvalTypeMsg(EvalStatusType.健康型.getMsg());
		}		

		// 警惕型 3 <= 绿+黄 <= 5
		if((greeCnt + yellowCnt) >= 3 && (greeCnt + yellowCnt) <= 5){
			retBean.setEvalTypeCode(EvalStatusType.警惕型.getCode());
			retBean.setEvalType(EvalStatusType.警惕型.getLabel());
			retBean.setEvalTypeMsg(EvalStatusType.警惕型.getMsg());
		}
		
		// 危机型 0 <= 绿+黄 <= 2
		if((greeCnt + yellowCnt) >= 0 && (greeCnt + yellowCnt) <= 2){
			retBean.setEvalTypeCode(EvalStatusType.危机型.getCode());
			retBean.setEvalType(EvalStatusType.危机型.getLabel());
			retBean.setEvalTypeMsg(EvalStatusType.危机型.getMsg());
		}
		
		// 评测结果类型Code设定
		evalEntity.setStatusTypeCode(retBean.getEvalTypeCode());
		
		// 评测趋势图转换
		List<EvalChartBean> chartBeanList = new ArrayList<EvalChartBean>();
		
		List<EvaluateEntity> evaluateList = evaluateDao.selectEvaluateListByShopId(shopEntity.getId());
		for(EvaluateEntity e : evaluateList){
			EvalChartBean chart = new EvalChartBean();
			
			chart.setEvaluateValue(e.getEvaluateValue());
			chart.setEvaluateDate(e.getEvaluateDate());
			chart.setStatusTypeCode(e.getStatusTypeCode());
			chart.setStatusTypeLabel(EvalStatusType.getLabel(e.getStatusTypeCode()));
			chartBeanList.add(chart);
		}
		
		// 评测趋势图设定
		retBean.setChartBeanList(chartBeanList);
	}
	
	/**
	 * <p>取得评测总分
	 * @param evaluate
	 * @return <p>
	 * BigDecimal
	 */
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
		monthlyOtherPay = monthlyOtherPay == null ? BigDecimal.ZERO : monthlyOtherPay;
		
		// 团购收入
		BigDecimal monthlyGroupBuy = evaluate.getMonthlyGroupBuy();
		
		BigDecimal val = monthlySales.subtract(monthlyPurchase).subtract(monthlySalary).subtract(monthlyRent).subtract(monthlyEnergy).subtract(monthlyOtherPay);
		
		return val;
	}

	@Override
	public EvalPageBean getEvalList(long shopId) {
		
		EvalPageBean retBean = new EvalPageBean();
		
		ShopEntity shopEntity = shopDao.selectShopById(shopId);
		EvaluateEntity evalEntity = evaluateDao.selectLatestEvaluateByShopId(shopId);
		
		this.parseEvalValidateBean(shopEntity, evalEntity, retBean);
		
		/*
		// 评测趋势图转换
		List<EvalChartBean> chartBeanList = new ArrayList<EvalChartBean>();
		
		List<EvaluateEntity> evaluateList = evaluateDao.selectEvaluateListByShopId(shopId);
		for(EvaluateEntity e : evaluateList){
			EvalChartBean chart = new EvalChartBean();
			
			chart.setEvaluateValue(e.getEvaluateValue());
			chart.setEvaluateDate(e.getEvaluateDate());
			chart.setStatusTypeCode(e.getStatusTypeCode());
			chart.setStatusTypeLabel(EvalStatusType.getLabel(e.getStatusTypeCode()));
			chartBeanList.add(chart);
		}
		
		// 评测趋势图设定
		retBean.setChartBeanList(chartBeanList);*/
		
		return retBean;
	}

	/**
	 * 取得评测结果提示详细
	 */
	@Override
	public List<String> getEvalValidateBean(int idx) {
		
		List<String> evalTips = EvalValidateType.getTips(idx);
		
		return evalTips;
	}

}
