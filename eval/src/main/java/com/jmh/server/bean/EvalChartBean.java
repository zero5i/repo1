package com.jmh.server.bean;

import java.math.BigDecimal;

public class EvalChartBean {
	
	/**
	 * 测评值
	 */
	private BigDecimal evaluateValue;
	
	/**
	 * 测评日期(yyyyMMdd)
	 */
	private String evaluateDate;
	
	/**
	 * 评测结果状态值
	 */
	private Integer statusTypeCode;

	/**
	 * 评测结果状态
	 */
	private String statusTypeLabel;
	
	/**
	 * <p>获取 测评日期(yyyyMMdd)</p>
	 * @return  evaluateDate  测评日期(yyyyMMdd)<br>
	 */
	public String getEvaluateDate() {
		return evaluateDate;
	}

	/**
	 * <p>设置 测评日期(yyyyMMdd)</p>
	 * @param  evaluateDate  测评日期(yyyyMMdd)<br>
	 */
	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	/**
	 * <p>获取 评测结果状态值</p>
	 * @return  statusTypeCode  评测结果状态值<br>
	 */
	public Integer getStatusTypeCode() {
		return statusTypeCode;
	}

	/**
	 * <p>设置 评测结果状态值</p>
	 * @param  statusTypeCode  评测结果状态值<br>
	 */
	public void setStatusTypeCode(Integer statusTypeCode) {
		this.statusTypeCode = statusTypeCode;
	}

	/**
	 * <p>获取 测评值</p>
	 * @return  evaluateValue  测评值<br>
	 */
	public BigDecimal getEvaluateValue() {
		return evaluateValue;
	}

	/**
	 * <p>设置 测评值</p>
	 * @param  evaluateValue  测评值<br>
	 */
	public void setEvaluateValue(BigDecimal evaluateValue) {
		this.evaluateValue = evaluateValue;
	}

	/**
	 * <p>获取 评测结果状态</p>
	 * @return  statusTypeLabel  评测结果状态<br>
	 */
	public String getStatusTypeLabel() {
		return statusTypeLabel;
	}

	/**
	 * <p>设置 评测结果状态</p>
	 * @param  statusTypeLabel  评测结果状态<br>
	 */
	public void setStatusTypeLabel(String statusTypeLabel) {
		this.statusTypeLabel = statusTypeLabel;
	}
}
