package com.jmh.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.jmh.server.entity.ShopEntity;

/**
 * 店铺Dao<p>
 * @author JiangMuhua @date 2015年3月25日<br>
 * @version 1.0<br>
 */
public interface IShopDao {
	
	/**
	 * 查询名下店铺列表<p>
	 * @return <p>
	 * List<ShopEntity>
	 */
	public List<ShopEntity> selectShopListByUserId(@Param("userId") Long userId);

	/**
	 * 通过店铺编号查询店铺<p>
	 * @param Id
	 * @return <p>
	 * ShopEntity
	 */
	public ShopEntity selectShopById(@Param("id") Long id);
	
	/**
	 * 删除店铺<p>
	 * @param empId
	 * @return <p>
	 * ShopEntity
	 */
	@Delete("delete from t_shop where id = #{id}")
	public int deleteShopById(@Param("id") Long id);
	
	/**
	 * 修改店铺<p>
	 * @param entity
	 * @return <p>
	 * ShopEntity
	 */
	public int updateShop(ShopEntity entity);
	
	/**
	 * 创建店铺<p>
	 * @param entity
	 * @return <p>
	 * ShopEntity
	 */
	public int insertShop(ShopEntity entity);
	
}
