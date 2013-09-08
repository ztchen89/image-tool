package com.ztchen.image.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztchen.image.util.SavaImagesThread;

public class SaveImagesServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		HttpSession session = req.getSession();
		
		String keyword = (String) session.getAttribute("keyword");
		String imageSource = (String) session.getAttribute("imageSource");
		List<String> images = (List<String>) session.getAttribute("images");
		
		String path = req.getSession().getServletContext().getRealPath("/imageStore");
		
		System.out.println(path);
		File dir = new File(path, imageSource + "/" + keyword);
		
		if(!dir.exists())
		{
			dir.mkdirs();	
		}
		
		//long start = System.currentTimeMillis();
		
		String[] urlArray = null;
		
		for (int i = 0; i < images.size(); i++)
		{
			String url = images.get(i);
			int pos = url.lastIndexOf("/");
			
			String imageName = url.substring(pos + 1);
			
			File file = new File(dir, imageName);
			
			
			if(i % 5 == 0)
			{
				if(0 != i)
				{
					new SavaImagesThread(dir, urlArray).start();
				}
				urlArray = new String[5];
				
			}
			urlArray[i % 5] = url;
			
			//write2Local(url, file);
		}
		
		//long end = System.currentTimeMillis();
		//System.out.println("下载的时间：" + (end - start));
		
		resp.getWriter().print("success");
		resp.getWriter().flush();
		
	}
	
	
//	private void write2Local(String imageUrl, File file)
//	{
//		InputStream is = null;
//		OutputStream os = null;
//		
//		try
//		{
//			URL url = new URL(imageUrl);
//			is = url.openStream();
//			
//			os = new FileOutputStream(file);
//			
//			int length = -1;
//			byte[] buffer = new byte[8192];
//			
//			while(-1 != (length = is.read(buffer, 0, 8192)))
//			{
//				os.write(buffer, 0, length);
//			}
//		} catch (MalformedURLException e)
//		{
//			e.printStackTrace();
//		} catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		} catch (IOException e)
//		{
//			e.printStackTrace();
////		}finally{
////			try
////			{
////				if(null != is)
////				{
////					is.close();
////				}
////			} catch (IOException e)
////			{
////				e.printStackTrace();
////			}
////			
////			try
////			{
////				if(null != os)
////				{
////					os.close();
////				}
////			} catch (IOException e)
////			{
////				e.printStackTrace();
////			}
////		}
//	}
	
//	}
}
