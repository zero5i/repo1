package com.jmh.server.entity;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 编号
	 */
	private String openId;
	
	/**初始化Flag*/
	private Integer initFlag;
	
	/**记录创建时间*/
	private Date createDate;
	
	/**记录更新时间*/
	private Date updateDate;

	/**
	 * 获取 主键<p>
	 * @return  id  主键<br>
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 主键<p>
	 * @param  id  主键<br>
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>获取 编号</p>
	 * @return  openId  编号<br>
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * <p>设置 编号</p>
	 * @param  openId  编号<br>
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * <p>获取 初始化Flag</p>
	 * @return  initFlag  初始化Flag<br>
	 */
	public Integer getInitFlag() {
		return initFlag;
	}

	/**
	 * <p>设置 初始化Flag</p>
	 * @param  initFlag  初始化Flag<br>
	 */
	public void setInitFlag(Integer initFlag) {
		this.initFlag = initFlag;
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
}
