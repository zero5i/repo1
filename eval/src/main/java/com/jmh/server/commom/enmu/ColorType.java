package com.jmh.server.commom.enmu;

public enum ColorType {

	红("红", "error", "不合理"),
	黄("黄", "warn", "中等"),
	绿("绿", "normal", "正常");

	// 成员变量
	private String label;
	
	// value
	private String value;
	
	// tips
	private String tips;
	
	// 构造方法
	private ColorType(String label, String value, String tips) {
		this.label = label;
		this.value = value;
		this.tips = tips;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * <p>获取 value</p>
	 * @return  value  value<br>
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <p>设置 value</p>
	 * @param  value  value<br>
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * <p>获取 tips</p>
	 * @return  tips  tips<br>
	 */
	public String getTips() {
		return tips;
	}

	/**
	 * <p>设置 tips</p>
	 * @param  tips  tips<br>
	 */
	public void setTips(String tips) {
		this.tips = tips;
	}
}
