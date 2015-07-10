package com.jmh.server.commom.enmu;

public enum EvalValidateType {
	
	餐位数("餐位数", 1),
	毛利率("毛利率", 2),
	菜品数量("菜品数量", 3),
	团购售卖("团购售卖", 4),
	人力成本("人力成本",5),
	采购("采购", 6),
	租金成本("租金成本", 7),
	能耗成本("能耗成本", 8);

	// 成员变量
	private String label;
	
	// value
	private int value;

	// 构造方法
	private EvalValidateType(String label, int value) {
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
