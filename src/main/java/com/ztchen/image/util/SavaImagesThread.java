package com.ztchen.image.util;

import java.io.File;

public class SavaImagesThread extends Thread
{
	private File dir;
	private String[] urls;
	
	public SavaImagesThread(File dir, String[] urls)
	{
		this.dir = dir;
		this.urls = urls;
	}
	
	@Override
	public void run()
	{
		try
		{
			long start = System.currentTimeMillis();
			
			for (String url : urls)
			{
				int pos = url.lastIndexOf("/");
				String imageName = url.substring(pos + 1);
				
				File file = new File(dir, imageName);
				
				GetJsonUtil.writeToLocal(url, file);
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println(Thread.currentThread().getName() +":" + (end - start));
		} catch (Exception e)
		{
			e.printStackTrace();
		}


	}
}
