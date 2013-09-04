package com.ztchen.google.image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FlickrImgTest
{
	public static void main(String[] args) throws MalformedURLException, IOException 
	{
		String str = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7676b0a957164c2720df6d5525f02362&text=macbook&per_page=20&format=json";
		
		
		HttpURLConnection conn = (HttpURLConnection) new URL(str).openConnection();
		conn.setRequestMethod("POST");
		
		int code = conn.getResponseCode();
		System.out.println(code);
		
		InputStream is = conn.getInputStream();
		
		InputStreamReader isr = new InputStreamReader(is);
		
		BufferedReader br = new BufferedReader(isr);
		
		String line = null;
		
		StringBuffer sb = new StringBuffer();
		
		while(null != (line = br.readLine()))
		{
			sb.append(line);
		}
		
		
		br.close();
		isr.close();
		is.close();
		
		
		System.out.println(sb);
		
	}
}

