package com.jmh.server.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	
	/**用户表id(外键)*/
	private Long userId;
	
	/**店铺名称*/
	private String shopName;
	
	/**评测次数*/
	private Integer evalCount;
	
	/**城市编号 省*/
	private Integer provinceId;
	
	/**城市编号*/
	private Integer cityId;
	
	/**店铺类型值*/
	private String typeCode;
	
	/**餐位数*/
	private Integer posCount;
	
	/**人均消费*/
	private Integer perPay;
	
	/**前厅面积*/
	private BigDecimal spaceSize;
	
	/**菜品数量*/
	private Integer foodCount;
	
	/**记录创建时间*/
	private Date createDate;
	
	/**记录更新时间*/
	private Date updateDate;

	/**最新评测得分*/
	private BigDecimal evaluateValue;
	
	/**
	 * <p>获取 主键</p>
	 * @return  id  主键<br>
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>设置 主键</p>
	 * @param  id  主键<br>
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>获取 用户表id(外键)</p>
	 * @return  userId  用户表id(外键)<br>
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * <p>设置 用户表id(外键)</p>
	 * @param  userId  用户表id(外键)<br>
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * <p>获取 店铺名称</p>
	 * @return  shopName  店铺名称<br>
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * <p>设置 店铺名称</p>
	 * @param  shopName  店铺名称<br>
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * <p>获取 评测次数</p>
	 * @return  evalCount  评测次数<br>
	 */
	public Integer getEvalCount() {
		return evalCount;
	}

	/**
	 * <p>设置 评测次数</p>
	 * @param  evalCount  评测次数<br>
	 */
	public void setEvalCount(Integer evalCount) {
		this.evalCount = evalCount;
	}

	/**
	 * <p>获取 城市编号</p>
	 * @return  cityId  城市编号<br>
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * <p>设置 城市编号</p>
	 * @param  cityId  城市编号<br>
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * <p>获取 店铺类型值</p>
	 * @return  typeCode  店铺类型值<br>
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * <p>设置 店铺类型值</p>
	 * @param  typeCode  店铺类型值<br>
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * <p>获取 餐位数</p>
	 * @return  posCount  餐位数<br>
	 */
	public Integer getPosCount() {
		return posCount;
	}

	/**
	 * <p>设置 餐位数</p>
	 * @param  posCount  餐位数<br>
	 */
	public void setPosCount(Integer posCount) {
		this.posCount = posCount;
	}

	/**
	 * <p>获取 人均消费</p>
	 * @return  perPay  人均消费<br>
	 */
	public Integer getPerPay() {
		return perPay;
	}

	/**
	 * <p>设置 人均消费</p>
	 * @param  perPay  人均消费<br>
	 */
	public void setPerPay(Integer perPay) {
		this.perPay = perPay;
	}

	/**
	 * <p>获取 前厅面积</p>
	 * @return  spaceSize  前厅面积<br>
	 */
	public BigDecimal getSpaceSize() {
		return spaceSize;
	}

	/**
	 * <p>设置 前厅面积</p>
	 * @param  spaceSize  前厅面积<br>
	 */
	public void setSpaceSize(BigDecimal spaceSize) {
		this.spaceSize = spaceSize;
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
	 * <p>获取 城市编号省</p>
	 * @return  provinceId  城市编号省<br>
	 */
	public Integer getProvinceId() {
		return provinceId;
	}

	/**
	 * <p>设置 城市编号省</p>
	 * @param  provinceId  城市编号省<br>
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * <p>获取 最新评测得分</p>
	 * @return  evaluateValue  最新评测得分<br>
	 */
	public BigDecimal getEvaluateValue() {
		return evaluateValue;
	}

	/**
	 * <p>设置 最新评测得分</p>
	 * @param  evaluateValue  最新评测得分<br>
	 */
	public void setEvaluateValue(BigDecimal evaluateValue) {
		this.evaluateValue = evaluateValue;
	}

	/**
	 * @return the foodCount
	 */
	public Integer getFoodCount() {
		return foodCount;
	}

	/**
	 * @param foodCount the foodCount to set
	 */
	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

}
