package com.jmh.server.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EvalPageBean {
	
	/**
	 * 评测得分
	 */
	private BigDecimal evaluateValue;
	
	/**
	 * 扩张型.健康型..警惕型.危机型.危险呢
	 */
	private String evalType;
	
	/**
	 * 评测结果类型Code
	 */
	private int evalTypeCode;
	
	/**
	 * 评测类型提醒消息
	 */
	private String evalTypeMsg;
	
	/**
	 * 项目诊断结果列表
	 */
	private List<EvalValidateBean> validateBeanList = new ArrayList<EvalValidateBean>();
	
	/**
	 * 评测走势图列表
	 */
	private List<EvalChartBean> chartBeanList = new ArrayList<EvalChartBean>();

	/**
	 * <p>获取 评测得分</p>
	 * @return  evaluateValue  评测得分<br>
	 */
	public BigDecimal getEvaluateValue() {
		return evaluateValue;
	}

	/**
	 * <p>设置 评测得分</p>
	 * @param  evaluateValue  评测得分<br>
	 */
	public void setEvaluateValue(BigDecimal evaluateValue) {
		this.evaluateValue = evaluateValue;
	}

	/**
	 * <p>获取 扩张型.健康型..警惕型.危机型.危险呢</p>
	 * @return  evalType  扩张型.健康型..警惕型.危机型.危险呢<br>
	 */
	public String getEvalType() {
		return evalType;
	}

	/**
	 * <p>设置 扩张型.健康型..警惕型.危机型.危险呢</p>
	 * @param  evalType  扩张型.健康型..警惕型.危机型.危险呢<br>
	 */
	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}

	/**
	 * <p>获取 评测类型提醒消息</p>
	 * @return  evalTypeMsg  评测类型提醒消息<br>
	 */
	public String getEvalTypeMsg() {
		return evalTypeMsg;
	}

	/**
	 * <p>设置 评测类型提醒消息</p>
	 * @param  evalTypeMsg  评测类型提醒消息<br>
	 */
	public void setEvalTypeMsg(String evalTypeMsg) {
		this.evalTypeMsg = evalTypeMsg;
	}

	/**
	 * <p>获取 项目诊断结果列表</p>
	 * @return  validateBeanList  项目诊断结果列表<br>
	 */
	public List<EvalValidateBean> getValidateBeanList() {
		return validateBeanList;
	}

	/**
	 * <p>设置 项目诊断结果列表</p>
	 * @param  validateBeanList  项目诊断结果列表<br>
	 */
	public void setValidateBeanList(List<EvalValidateBean> validateBeanList) {
		this.validateBeanList = validateBeanList;
	}

	/**
	 * <p>获取 评测结果类型Code</p>
	 * @return  evalTypeCode  评测结果类型Code<br>
	 */
	public int getEvalTypeCode() {
		return evalTypeCode;
	}

	/**
	 * <p>设置 评测结果类型Code</p>
	 * @param  evalTypeCode  评测结果类型Code<br>
	 */
	public void setEvalTypeCode(int evalTypeCode) {
		this.evalTypeCode = evalTypeCode;
	}

	/**
	 * <p>获取 评测走势图列表</p>
	 * @return  chartBeanList  评测走势图列表<br>
	 */
	public List<EvalChartBean> getChartBeanList() {
		return chartBeanList;
	}

	/**
	 * <p>设置 评测走势图列表</p>
	 * @param  chartBeanList  评测走势图列表<br>
	 */
	public void setChartBeanList(List<EvalChartBean> chartBeanList) {
		this.chartBeanList = chartBeanList;
	}
	
	
}
