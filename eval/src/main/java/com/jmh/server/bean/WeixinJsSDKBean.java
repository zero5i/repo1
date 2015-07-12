package com.jmh.server.bean;

/** <p>
 * @author JiangMuhua @date 2015年7月12日<br>
 * @version 1.0<br>
 */
public class WeixinJsSDKBean {
	
	/**公众号的唯一标识 */
	private String appId;
	
	/**生成签名的时间戳*/
	private String timestamp;
	
	/**生成签名的随机串*/
	private String nonceStr;
	
	/**签名*/
	private String signature;

	/**
	 * <p>获取 公众号的唯一标识</p>
	 * @return  appId  公众号的唯一标识<br>
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * <p>设置 公众号的唯一标识</p>
	 * @param  appId  公众号的唯一标识<br>
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * <p>获取 生成签名的时间戳</p>
	 * @return  timestamp  生成签名的时间戳<br>
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * <p>设置 生成签名的时间戳</p>
	 * @param  timestamp  生成签名的时间戳<br>
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * <p>获取 生成签名的随机串</p>
	 * @return  nonceStr  生成签名的随机串<br>
	 */
	public String getNonceStr() {
		return nonceStr;
	}

	/**
	 * <p>设置 生成签名的随机串</p>
	 * @param  nonceStr  生成签名的随机串<br>
	 */
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	/**
	 * <p>获取 签名</p>
	 * @return  signature  签名<br>
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * <p>设置 签名</p>
	 * @param  signature  签名<br>
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
