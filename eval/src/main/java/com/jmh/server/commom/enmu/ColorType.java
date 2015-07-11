package com.jmh.server.commom.enmu;

public enum ColorType {

	红("红", "red"),
	黄("黄", "yellow"),
	绿("绿", "green");

	// 成员变量
	private String label;
	
	// value
	private String value;

	// 构造方法
	private ColorType(String label, String value) {
		this.label = label;
		this.value = value;
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
}
