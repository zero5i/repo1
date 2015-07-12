package com.jmh.server.commom.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/** <p>
 * @author JiangMuhua @date 2015年6月28日<br>
 * @version 1.0<br>
 */
public class WeixinUtil {
	
	/**
	 * <p>取得公共号的appid
	 * @return <p>
	 * String
	 */
	public static String getAppId(){
		return CustomizedPropertyConfigurer.getContextProperty("weixin.app.id");
	}
	
	public static String getSecretId(){
		return CustomizedPropertyConfigurer.getContextProperty("weixin.app.secret");
	}
	
	/**
	 * 取得签名signature
	 * 
	 * @param timestamp
	 * @param nonceStr
	 * @param url
	 * @return
	 */
	public static String GetSignature(String timestamp, String nonceStr, String url, String ticket) {
		String signature = null;
		String string1 = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
		// 对string1进行sha1签名，得到signature
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("utf-8"));
			byte[] hash = crypt.digest();
			Formatter formatter = new Formatter();
			for (byte b : hash) {
				formatter.format("%02x", b);
			}
			signature = formatter.toString();
			formatter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signature;
	}
	
	/**
	 * <p>取得随机noncestr
	 * @return <p>
	 * String
	 */
	public static String getRandomNoncestr(){
		return UUID.randomUUID().toString();
	}
		
	/**
	 * <p>取得随机timestamp
	 * @return <p>
	 * String
	 */
	public static String getRandomTimestamp(){
		long time = System.currentTimeMillis();
		
		return String.valueOf(time / 1000);
	}
	
	/**
	 * <p>获取微信的AccessToken(调用此方法不能太频繁，每天有次数限制)
	 * @return <p>
	 * String
	 */
	public static String getRemoteAccessToken(){

		// 微信App.id
		String secretId = getSecretId();
		
		// 微信App.id
		String appId = getAppId();
		
		
		String apiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=S_APPID&secret=S_APPSECRET";
		
		apiUrl = apiUrl.replace("S_APPID", appId);
		apiUrl = apiUrl.replace("S_APPSECRET", secretId);

		try {
			JSONObject json = getWeixinResponse(apiUrl);
			return json.getString("access_token");

		} catch (JSONException e) {
			return null;
		}
	}
	

	/**
	 * <p>获取微信的AccessToken(调用此方法不能太频繁，每天有次数限制)
	 * @return <p>
	 * String
	 */
	public static String getRemoteJsapiTicket(String accessToken){

		String apiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=S_ACCESS_TOKEN&type=jsapi";
		
		apiUrl = apiUrl.replace("S_ACCESS_TOKEN", accessToken);

		try {
			JSONObject json = getWeixinResponse(apiUrl);
			return json.getString("ticket");
		} catch (JSONException e) {
			return null;
		}
	}
	
	/**
	 * 获取微信的OpenId
	 * @param content
	 * @return
	 */
	public static String getRemoteOpenId(String code) {
		
		// 微信App.id
		String secretId = getSecretId();
		
		// 微信App.id
		String appId = getAppId();
		
		String apiUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=S_APPID&secret=S_APPSECRET&code=S_CODE&grant_type=authorization_code";
		
		apiUrl = apiUrl.replace("S_APPID", appId);
		apiUrl = apiUrl.replace("S_APPSECRET", secretId);
		apiUrl = apiUrl.replace("S_CODE", code);

		try {
			JSONObject json = getWeixinResponse(apiUrl);
			return json.getString("openid");

		} catch (JSONException e) {
			return null;
		}
	}
	
	private static JSONObject getWeixinResponse(String apiUrl){
		
		/** 发送httpget请求 */
		HttpGet request = new HttpGet(apiUrl);
		String result = "";
		try {
			HttpResponse response = HttpClients.createDefault()
					.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** 请求失败处理 */
		if (null == result) {
			return null;
		}

		return JSONObject.fromObject(result);
	}
	
	/**
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(getAppId());
	}

}
