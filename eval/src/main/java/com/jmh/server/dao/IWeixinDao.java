package com.jmh.server.dao;

import com.jmh.server.entity.WeixinEntity;

/** <p>
 * @author JiangMuhua 
 * @date 2015年6月28日<br>
 * @version 1.0<br>
 */
public interface IWeixinDao {
	/**
	 * 通过ID查询<p>
	 * @return <p>
	 * WeixinEntity
	 */
	public WeixinEntity selectWeixinByKey(String key);
	
	/**
	 * <p>添加微信持久值
	 * @param entity
	 * @return <p>
	 * WeixinEntity
	 */
	public int insertWeixin(WeixinEntity entity);
	
	/**
	 * 更新微信持久值<p>
	 * @param id
	 * @return <p>
	 * WeixinEntity
	 */
	public int updateWeixin(WeixinEntity entity);

}
