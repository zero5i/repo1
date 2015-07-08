package com.jmh.server.commom.enmu;

public enum ShopType {

	中餐("中餐", 1),
	西餐("西餐", 2),
	火锅("火锅", 3),
	咖啡_面包("咖啡／面包", 4),
	中式快餐("中式快餐",5),
	西式快餐("西式快餐", 6),
	简餐("简餐", 7),
	奶茶铺("奶茶铺", 8);

	// 成员变量
	private String label;
	
	// value
	private int value;

	// 构造方法
	private ShopType(String label, int value) {
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
	public int getValue() {
		return value;
	}

	/**
	 * <p>设置 value</p>
	 * @param  value  value<br>
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
