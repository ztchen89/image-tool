package com.ztchen.image.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetJsonUtil
{
	public static String getJsonResult(String str)
	{
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try
		{
			URL url = new URL(str);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			if (responseCode == 200)
			{
				is = conn.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);

				String line = null;
				sb = new StringBuffer();

				while (null != (line = br.readLine()))
				{
					sb.append(line);
				}

			} else
			{
				System.out.println("连接失败！！！！ 状态码：" + responseCode);
			}

			return sb.toString();

		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (ProtocolException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			destroy(is, isr, br);
		}

		return null;
	}

	private static void destroy(InputStream is, InputStreamReader isr,BufferedReader br)
	{
		try
		{
			if (null != br)
			{
				br.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			if (null != isr)
			{
				isr.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			if (null != is)
			{
				is.close();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void writeToLocal(String imageUrl, File file)
	{
		InputStream is = null;
		OutputStream os = null;
		
		try
		{
			URL url = new URL(imageUrl);
			is = url.openStream();
			
			os = new FileOutputStream(file);
			
			int length = -1;
			byte[] buffer = new byte[8192];
			
			while(-1 != (length = is.read(buffer, 0, 8192)))
			{
				os.write(buffer, 0, length);
			}
			
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}finally{
			try
			{
				if(null != is)
				{
					is.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
			try
			{
				if(null != os)
				{
					os.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}
}