package com.jmh.server.bean;

import java.util.ArrayList;
import java.util.List;


public class EvalValidateBean {
	
	/**
	 * 验证
	 */
	private int idx;
	
	/**
	 * 验证名称：餐位数，毛利率等等
	 */
	private String labelName;
	
	/**
	 * 颜色标记 red, green, yellow
	 */
	private String color;
	
	/**
	 * 正常范围
	 */
	private String normalRange;
	
	/**
	 * 评测得分
	 */
	private String scope;
	
	/**
	 * 状态名称:正常，不合理等等
	 */
	private String statusLabel;
	
	/**
	 * 评测结果提示列表
	 */
	private List<String> evalTips = new ArrayList<String>();/*
	
	*//**
	 * 消息列表
	 *//*
	private List<String> msgList = new ArrayList<String>();*/

	/**
	 * <p>获取 验证</p>
	 * @return  idx  验证<br>
	 */
	public int getIdx() {
		return idx;
	}

	/**
	 * <p>设置 验证</p>
	 * @param  idx  验证<br>
	 */
	public void setIdx(int idx) {
		this.idx = idx;
	}

	/**
	 * <p>获取 验证名称：餐位数，毛利率等等</p>
	 * @return  labelName  验证名称：餐位数，毛利率等等<br>
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * <p>设置 验证名称：餐位数，毛利率等等</p>
	 * @param  labelName  验证名称：餐位数，毛利率等等<br>
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	/**
	 * <p>获取 颜色标记redgreenyellow</p>
	 * @return  color  颜色标记redgreenyellow<br>
	 */
	public String getColor() {
		return color;
	}

	/**
	 * <p>设置 颜色标记redgreenyellow</p>
	 * @param  color  颜色标记redgreenyellow<br>
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * <p>获取 正常范围</p>
	 * @return  normalRange  正常范围<br>
	 */
	public String getNormalRange() {
		return normalRange;
	}

	/**
	 * <p>设置 正常范围</p>
	 * @param  normalRange  正常范围<br>
	 */
	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}

	/**
	 * <p>获取 评测得分</p>
	 * @return  scope  评测得分<br>
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * <p>设置 评测得分</p>
	 * @param  scope  评测得分<br>
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * <p>获取 评测结果提示</p>
	 * @return  evalTips  评测结果提示<br>
	 */
	public List<String> getEvalTips() {
		return evalTips;
	}

	/**
	 * <p>设置 评测结果提示</p>
	 * @param  evalTips  评测结果提示<br>
	 */
	public void setEvalTips(List<String> evalTips) {
		this.evalTips = evalTips;
	}

	/**
	 * <p>获取 状态名称:正常，不合理等等</p>
	 * @return  statusLabel  状态名称:正常，不合理等等<br>
	 */
	public String getStatusLabel() {
		return statusLabel;
	}

	/**
	 * <p>设置 状态名称:正常，不合理等等</p>
	 * @param  statusLabel  状态名称:正常，不合理等等<br>
	 */
	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}
}
