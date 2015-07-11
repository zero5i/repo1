package com.jmh.server.commom.enmu;

public enum EvalStatusType {
	
	扩张型("扩张型", "恭喜您，可以考虑开分店啦！"),
	健康型("健康型", "保持住，在努力一下就可以开分店啦！"),
	警惕型("警惕型", "要提高警惕啦，店铺问题较多，快快抓紧调整吧！"),
	危机型("危机型", "危险呢！再不努力就有关店危险了！");

	// 成员变量
	private String label;
	private String msg;

	// 构造方法
	private EvalStatusType(String label, String msg) {
		this.label = label;
		this.msg = msg;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * <p>获取 msg</p>
	 * @return  msg  msg<br>
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * <p>设置 msg</p>
	 * @param  msg  msg<br>
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
