package com.jmh.server.commom.enmu;

import java.util.Arrays;
import java.util.List;

public enum EvalValidateType {
	
	餐位数("餐位数", 1, new String[]{"是否可以调整目前桌椅排列?", "是否可以调整公共空间利用?", "是否可以调整桌椅规格大小?"}),
	毛利率("毛利率", 2, new String[]{"是不是采购价格问题？", "是否员工验货环境出了问提?", "是不是库存管理的问题？", "是不是菜品定价问题？", "是不是营业额的问题？"}),
	菜品数量("菜品数量", 3, new String[]{"是否可以查看行业龙头的菜单组成？", "是否可以请教业内人士？"}),
	团购售卖("团购售卖", 4, new String[]{"营销是否太单一了，只靠团购？","是否团购让利力度过度", "是否团购品类数量问题？"}),
	人力成本("人力成本",5, new String[]{"是否人员太多了？", "是否可以合并岗位？", "是否可以部分钟点工？", "排班是否不合理？"}),
	采购("采购", 6, new String[]{"采购是否价格太高？", "是否一直没有询价了？", "是否有人为的暗箱操作等？"}),
	租金成本("租金成本", 7, new String[]{}),
	能耗成本("能耗成本", 8, new String[]{"是否没有制定各节能制度？", "是否存在服务员监督不力问题？", "是否有其它泄露短路问题？"});

	// 成员变量
	private String label;
	
	// value
	private int value;
	
	private String[] tips;

	// 普通方法
	public static List<String> getTips(int value) {
		for (EvalValidateType c : EvalValidateType.values()) {
			if ( c.getValue() == value ) {
				return Arrays.asList(c.tips);
			}
		}
		return null;
	}
	
	// 构造方法
	private EvalValidateType(String label, int value, String[] tips) {
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
