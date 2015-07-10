package com.jmh.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.jmh.server.entity.EvaluateEntity;

/** <p>
 * @author DuanQingwei @date 2015年3月25日<br>
 * @version 1.0<br>
 */
public interface IEvaluateDao {
	
	public EvaluateEntity selectEvaluate(@Param("shopId") Long shopId, @Param("evaluateDate") String evaluateDate);
	
	/**
	 * 通过店铺ID检索出所有测评<p>
	 * @return <p>
	 * List<EvaluateEntity>
	 */
	public List<EvaluateEntity> selectEvaluateListByShopId(@Param("shopId") Long shopId);

	/**
	 * 通过ID查询<p>
	 * @param id
	 * @return <p>
	 * EvaluateEntity
	 */
	public EvaluateEntity selectEvaluateById(@Param("id") Long id);
	
	/**
	 * 删除测评<p>
	 * @param id
	 * @return <p>
	 * int
	 */
	@Delete("delete from t_evaluate where id = #{id}")
	public int deleteEvaluate(@Param("id") Long id);
	
	/**
	 * 修改测评<p>
	 * @param entity
	 * @return <p>
	 * EvaluateEntity
	 */
	public int updateEvaluate(EvaluateEntity entity);

	/**
	 * 创建测评<p>
	 * @param entity
	 * @return <p>
	 * EvaluateEntity
	 */
	public int insertEvaluate(EvaluateEntity entity);

}
