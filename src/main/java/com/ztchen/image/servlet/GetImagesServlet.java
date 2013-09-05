package com.ztchen.image.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.ztchen.image.util.GetJsonUtil;
import com.ztchen.image.util.UrlConstant;

public class GetImagesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String keyword = req.getParameter("keyword");
		String imageSource = req.getParameter("imageSource");
		List<String> imageUrls = new ArrayList<String>();

		if ("google".equals(imageSource))
		{
			handlerGoogle(keyword, imageUrls);
		} else
		{
			handleFlickr(keyword, imageUrls);
		}

		Gson gson = new Gson();
		String returnedUrls = gson.toJson(imageUrls);

		resp.setContentType("application/json; charset=utf-8");
		resp.setHeader("pragma", "no-cache");
		resp.setHeader("cache-control", "no-cache");

		PrintWriter out = resp.getWriter();
		out.println(returnedUrls);
		out.flush();
	}

	private void handleFlickr(String keyword, List<String> imageUrls)
	{
		try
		{
			String url_flickr = UrlConstant.FLICKR_URL + keyword;
			System.out.println(url_flickr);

			String resutl_flickr = GetJsonUtil.getJsonResult(url_flickr);

			resutl_flickr = resutl_flickr.substring(14,
					resutl_flickr.length() - 1);

			JSONObject flickr_json = new JSONObject(resutl_flickr);

			JSONObject jsonObj = flickr_json.getJSONObject("photos");

			JSONArray jsonArray = jsonObj.getJSONArray("photo");

			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject obj = jsonArray.getJSONObject(i);

				int farm = obj.getInt("farm");
				String server = obj.getString("server");
				String id = obj.getString("id");
				String secret = obj.getString("secret");

				// http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
				String imageUrl = "http://farm" + farm + ".staticflickr.com/"
						+ server + "/" + id + "_" + secret + ".jpg";
				// System.out.println(imageUrl);

				imageUrls.add(imageUrl);

			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	private void handlerGoogle(String keyword, List<String> imageUrls)
	{
		try
		{
			List<String> google_resultList = new ArrayList<String>();// 存放每次请求google的结果
			for (int i = 0; i < 2; i++)
			{
				String url_google = UrlConstant.GOOGLE_URL + keyword;
				// url_google += "&start=" + (10 * i + 1);
				String reslut = GetJsonUtil.getJsonResult(url_google);

				google_resultList.add(reslut);
				// Thread.sleep(1000);
			}

			for (String string : google_resultList)
			{
				// System.out.println(string);
				JSONObject google_json = new JSONObject(string);
				JSONArray jsonArray = google_json.getJSONArray("items");

				for (int i = 0; i < jsonArray.length(); i++)
				{
					JSONObject obj = jsonArray.getJSONObject(i);
					String link = obj.getString("link");
					System.out.println(link);
					imageUrls.add(link);
				}

			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
