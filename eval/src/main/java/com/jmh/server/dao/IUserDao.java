package com.jmh.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jmh.server.entity.UserEntity;

/** <p>取得所有性别</p>
 * @author JiangMuhua @date 2015年3月25日<br>
 * @version 1.0<br>
 */
public interface IUserDao {
	
	/**
	 * <p>取得所有用户
	 * @return <p>
	 * List<UserEntity>
	 */
	public List<UserEntity> selectUserList();

	/**
	 * <p>通过用户Id查询用户
	 * @param id
	 * @return <p>
	 * UserEntity
	 */
	public UserEntity selectUserById(@Param("id") Long id);
	
	/**
	 * <p>通过微信OpenId取得用户信息</p>
	 * @param openId
	 * @return 
	 * UserEntity
	 */
	public UserEntity selectUserByOpenId(@Param("openId") String openId);
	
	/**
	 * <p>通过Id修改用户初始化状态信息
	 * @param openId
	 * @return <p>
	 * int
	 */
	public int updateUserFlag(@Param("id") String id, @Param("initFlag") int initFlag);
	
	/**
	 * <p>添加用户
	 * @return <p>
	 * int
	 */
	public int insertUser(UserEntity user);

}
