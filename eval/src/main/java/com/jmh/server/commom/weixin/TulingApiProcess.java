package com.jmh.server.commom.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 调用图灵机器人api接口，获取智能回复内容
 * 
 * @author pamchen-1
 * 
 */
public class TulingApiProcess {
	/**
	 * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
	 * 
	 * @param content
	 * @return
	 */
	public String getTulingResult(String content) {
		/** 此处为图灵api接口，参数key需要自己去注册申请，先以11111111代替 */
		String apiUrl = "http://www.tuling123.com/openapi/api?key=8d8350c8bc555105f8436ef6c2295209&info=";
		String param = "";
		try {
			param = apiUrl + URLEncoder.encode(content, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // 将参数转为url编码

		/** 发送httpget请求 */
		HttpGet request = new HttpGet(param);
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
			return "对不起，你说的话真是太高深了……";
		}

		try {
			JSONObject json = JSONObject.fromObject(result);
			// 以code=100000为例，参考图灵机器人api文档
			
			switch(json.getInt("code"))
			{
				case 100000:

					result = json.getString("text");
					
					break;
				case 306000:
					StringBuffer buff = new StringBuffer();
					buff.append(json.getString("text")).append("\n");
					
					JSONArray arr = json.getJSONArray("list");
					
					for(int index = 0; index< arr.size(); index ++){
						JSONObject o = arr.getJSONObject(index);
						buff.append(o.get("flight")).append(o.get("starttime")).append("\n");
					}
					
					result = buff.toString();
					
					break;					
				default:
					
					break;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
