package com.jmh.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jmh.server.entity.CityEntity;

/** <p>
 * @author JiangMuhua 
 * @date 2015年6月28日<br>
 * @version 1.0<br>
 */
public interface ICityDao {
	
	/**
	 * 检索出所有省<p>
	 * @return <p>
	 * List<CityEntity>
	 */
	public List<CityEntity> selectProvinceList();

	/**
	 * 通过ID查询<p>
	 * @param id
	 * @return <p>
	 * CityEntity
	 */
	public List<CityEntity> selectCityListByParentId(@Param("parentId") Long parentId);

}
