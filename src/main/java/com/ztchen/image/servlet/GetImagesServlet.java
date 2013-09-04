package com.ztchen.image.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetImagesServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String keyword = req.getParameter("q");
		String imageSource = req.getParameter("imageSource");
		
		
		
		if("google".equals(imageSource))
		{
			
			
			
			
		}else {
			
		}
		
		
	}
}
