package com.jmh.server.entity;

import java.io.Serializable;

public class CityEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 城市名称
	 */
	private String cityName;
	
	/**
	 * 夫节点ID
	 */
	private Long parentId;

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
	 * <p>获取 城市名称</p>
	 * @return  cityName  城市名称<br>
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * <p>设置 城市名称</p>
	 * @param  cityName  城市名称<br>
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * <p>获取 夫节点ID</p>
	 * @return  parentId  夫节点ID<br>
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * <p>设置 夫节点ID</p>
	 * @param  parentId  夫节点ID<br>
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
