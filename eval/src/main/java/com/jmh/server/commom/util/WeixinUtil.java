package com.jmh.server.commom.util;

import java.io.IOException;

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
	 * <p>取得随机noncestr
	 * @return <p>
	 * String
	 */
	public static String getRandomNoncestr(){
		return DateTimeUtil.getCurrentTime();
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
	 * <p>获取微信的AccessToken
	 * @return <p>
	 * String
	 */
	public static String getRemoteAccessToken(){


		// 微信App.id
		String secretId = CustomizedPropertyConfigurer.getContextProperty("weixin.app.secret");
		
		// 微信App.id
		String appId = CustomizedPropertyConfigurer.getContextProperty("weixin.app.id");
		
		
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
	 * <p>获取微信的AccessToken
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
		String secretId = CustomizedPropertyConfigurer.getContextProperty("weixin.app.secret");
		
		// 微信App.id
		String appId = CustomizedPropertyConfigurer.getContextProperty("weixin.app.id");
		
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
		System.out.print(getRandomTimestamp());
	}

}
