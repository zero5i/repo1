package com.jmh.server.entity;

import java.io.Serializable;
import java.util.Date;

public class WeixinEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID 微信中要持久的名称
	 */
	private String keyName;
	
	/**
	 * 保存的微信相关值
	 */
	private String holdVal;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 是否过期
	 */
	private Integer expiredFlag;

	/**
	 * <p>获取 主键ID微信中要持久的名称</p>
	 * @return  keyName  主键ID微信中要持久的名称<br>
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * <p>设置 主键ID微信中要持久的名称</p>
	 * @param  keyName  主键ID微信中要持久的名称<br>
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * <p>获取 保存的微信相关值</p>
	 * @return  holdVal  保存的微信相关值<br>
	 */
	public String getHoldVal() {
		return holdVal;
	}

	/**
	 * <p>设置 保存的微信相关值</p>
	 * @param  holdVal  保存的微信相关值<br>
	 */
	public void setHoldVal(String holdVal) {
		this.holdVal = holdVal;
	}

	/**
	 * <p>获取 创建时间</p>
	 * @return  createDate  创建时间<br>
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * <p>设置 创建时间</p>
	 * @param  createDate  创建时间<br>
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * <p>获取 是否过期</p>
	 * @return  expiredFlag  是否过期<br>
	 */
	public Integer getExpiredFlag() {
		return expiredFlag;
	}

	/**
	 * <p>设置 是否过期</p>
	 * @param  expiredFlag  是否过期<br>
	 */
	public void setExpiredFlag(Integer expiredFlag) {
		this.expiredFlag = expiredFlag;
	}

}
