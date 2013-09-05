package com.ztchen.google.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class GoogleImgTest
{
	public static void main(String[] args) 
	{
		List<String> responseList = new ArrayList<String>();
		HttpsURLConnection conn = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		
		for(int i = 0; i < 1; i++)
		{
			String str = "https://www.googleapis.com/customsearch/v1?key=AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U&cx=001092378821530568921:6vxzfitcm3k&q=macbook&alt=json&searchType=image&imgSize=large&";
			
			str += "start=" +  (10 * i + 1);
			
			System.out.println(str);

			try
			{
				URL url = new URL(str);
				conn = (HttpsURLConnection) url.openConnection();
				//HttpsURLConnection conn = (HttpsURLConnection) new URL(str).openConnection();
				//((HttpURLConnection) conn).setRequestMethod("GET");
				conn.setRequestMethod("GET");
				
				int responseCode = conn.getResponseCode();
				
				if(responseCode == 200)
				{
					is = conn.getInputStream();
					
					isr = new InputStreamReader(is);
					
					br = new BufferedReader(isr);
					
					String line = null;
					
					sb = new StringBuffer();
					
					while(null != (line = br.readLine()))
					{
						sb.append(line);
					}
				}else {
					System.out.println("获取结果出错，返回HTTP代码：" + responseCode);
				}
				responseList.add(sb.toString());

				Thread.sleep(2000);
				
			} catch (MalformedURLException e)
			{
				e.printStackTrace();
			} catch (ProtocolException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}finally{
				try
				{
					if(br != null)
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				try
				{
					if(isr != null)
					isr.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}	
				try
				{
					if(is != null)
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	
		for (String string : responseList)
		{
			System.out.println(string);
		}
		
		//System.out.println(sb);
		
		
	}
}
