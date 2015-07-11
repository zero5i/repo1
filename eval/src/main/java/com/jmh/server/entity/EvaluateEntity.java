package com.jmh.server.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EvaluateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 店铺ID
	 */
	private Long shopId;
	
	/**
	 * 测评日期(yyyyMMdd)
	 */
	private String evaluateDate;
	
	/**
	 * 测评值
	 */
	private BigDecimal evaluateValue;
	
	/**
	 * 评测结果状态值
	 */
	private Integer statusTypeCode;

	/**月销售额*/
	private BigDecimal monthlySales;
	
	/**月采购额*/
	private BigDecimal monthlyPurchase;
	
	/**每月工资*/
	private BigDecimal monthlySalary;
	
	/**每月租金*/
	private BigDecimal monthlyRent;
	
	/**每月能耗*/
	private BigDecimal monthlyEnergy;
	
	/**其他开销*/
	private BigDecimal monthlyOtherPay;
	
	/** 团购收入 */
	private BigDecimal monthlyGroupBuy;
	
	/**记录创建时间*/
	private Date createDate;
	
	/**记录更新时间*/
	private Date updateDate;
	
	/**
	 * 获取 主键ID<p>
	 * @return  id  主键ID<br>
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 主键ID<p>
	 * @param  id  主键ID<br>
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	 * <p>获取 月销售额</p>
	 * @return  monthlySales  月销售额<br>
	 */
	public BigDecimal getMonthlySales() {
		return monthlySales;
	}

	/**
	 * <p>设置 月销售额</p>
	 * @param  monthlySales  月销售额<br>
	 */
	public void setMonthlySales(BigDecimal monthlySales) {
		this.monthlySales = monthlySales;
	}

	/**
	 * <p>获取 月采购额</p>
	 * @return  monthlyPurchase  月采购额<br>
	 */
	public BigDecimal getMonthlyPurchase() {
		return monthlyPurchase;
	}

	/**
	 * <p>设置 月采购额</p>
	 * @param  monthlyPurchase  月采购额<br>
	 */
	public void setMonthlyPurchase(BigDecimal monthlyPurchase) {
		this.monthlyPurchase = monthlyPurchase;
	}

	/**
	 * <p>获取 每月工资</p>
	 * @return  monthlySalary  每月工资<br>
	 */
	public BigDecimal getMonthlySalary() {
		return monthlySalary;
	}

	/**
	 * <p>设置 每月工资</p>
	 * @param  monthlySalary  每月工资<br>
	 */
	public void setMonthlySalary(BigDecimal monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	/**
	 * <p>获取 每月租金</p>
	 * @return  monthlyRent  每月租金<br>
	 */
	public BigDecimal getMonthlyRent() {
		return monthlyRent;
	}

	/**
	 * <p>设置 每月租金</p>
	 * @param  monthlyRent  每月租金<br>
	 */
	public void setMonthlyRent(BigDecimal monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	/**
	 * <p>获取 每月能耗</p>
	 * @return  monthlyEnergy  每月能耗<br>
	 */
	public BigDecimal getMonthlyEnergy() {
		return monthlyEnergy;
	}

	/**
	 * <p>设置 每月能耗</p>
	 * @param  monthlyEnergy  每月能耗<br>
	 */
	public void setMonthlyEnergy(BigDecimal monthlyEnergy) {
		this.monthlyEnergy = monthlyEnergy;
	}

	/**
	 * <p>获取 其他开销</p>
	 * @return  monthlyOtherPay  其他开销<br>
	 */
	public BigDecimal getMonthlyOtherPay() {
		return monthlyOtherPay;
	}

	/**
	 * <p>设置 其他开销</p>
	 * @param  monthlyOtherPay  其他开销<br>
	 */
	public void setMonthlyOtherPay(BigDecimal monthlyOtherPay) {
		this.monthlyOtherPay = monthlyOtherPay;
	}

	/**
	 * <p>获取 记录创建时间</p>
	 * @return  createDate  记录创建时间<br>
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * <p>设置 记录创建时间</p>
	 * @param  createDate  记录创建时间<br>
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * <p>获取 记录更新时间</p>
	 * @return  updateDate  记录更新时间<br>
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * <p>设置 记录更新时间</p>
	 * @param  updateDate  记录更新时间<br>
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * <p>获取 店铺ID</p>
	 * @return  shopId  店铺ID<br>
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * <p>设置 店铺ID</p>
	 * @param  shopId  店铺ID<br>
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * <p>获取 团购收入</p>
	 * @return  monthlyGroupBuy  团购收入<br>
	 */
	public BigDecimal getMonthlyGroupBuy() {
		return monthlyGroupBuy;
	}

	/**
	 * <p>设置 团购收入</p>
	 * @param  monthlyGroupBuy  团购收入<br>
	 */
	public void setMonthlyGroupBuy(BigDecimal monthlyGroupBuy) {
		this.monthlyGroupBuy = monthlyGroupBuy;
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
}
