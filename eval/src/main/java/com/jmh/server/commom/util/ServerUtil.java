package com.jmh.server.commom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ServerUtil {
	/**
	 * 
	 * @return WebRoot目录的绝对路径
	 */
	public static String getWebRootAbsolutePath() {
		// String path = null;
		// String folderPath =
		// ServerUtil.class.getProtectionDomain().getCodeSource()
		// .getLocation().getPath();
		// if (folderPath.indexOf("WEB-INF") > 0) {
		// path = folderPath.substring(0, folderPath
		// .indexOf("WEB-INF/classes"));
		// }
		// return path;

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassLoader.getSystemClassLoader();
		}
		java.net.URL url = classLoader.getResource("");
		String ROOT_CLASS_PATH = url.getPath() + "/";
		File rootFile = new File(ROOT_CLASS_PATH);
		String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";
		File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
		String strReturn = webInfoDir.getParent() + "/";
		strReturn = strReturn.replace("\\", "/");
		return strReturn;
	}

	/**
	 * 预览图片
	 * <p>
	 * 
	 * @param response
	 * @param inFileName
	 * @throws IOException
	 *             <p>
	 *             void
	 */
	public static void previewImage(HttpServletResponse response,
			String inFileName) throws IOException {

		try {
			FileInputStream hFile = new FileInputStream(inFileName); // 以byte流的方式打开文件
																		// d:\1.gif
			int i = hFile.available(); // 得到文件大小
			byte data[] = new byte[i];
			hFile.read(data); // 读数据
			hFile.close();
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
			toClient.flush();
		} catch (IOException e) // 错误处理
		{
			PrintWriter toClient = response.getWriter(); // 得到向客户端输出文本的对象
			toClient.write("无法打开图片!");
			toClient.close();
			toClient.flush();
		}

	}
}