package com.ztchen.image.util;

public class UrlConstant 
{
	private static String GOOGLE_KEY = "AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U";
	private static String GOOGLE_CX = "001092378821530568921:6vxzfitcm3k";
	private static String FLICKR_KEY = "7676b0a957164c2720df6d5525f02362";
	private static String FLICKR_PER_PAGE = "20";
	public static final String GOOGLE_URL="https://www.googleapis.com/customsearch/v1?key=" + GOOGLE_KEY + "&cx="+ GOOGLE_CX + "&alt=json&searchType=image&imgSize=large&q=";
	public static final String FLICKR_URL="http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" + FLICKR_KEY + "&per_page="+FLICKR_PER_PAGE+"&format=json&text=";
	
	
	
//	public static void main(String[] args) {
//		String url = FLICKR_URL + "text=girl";
//		String url2 = GOOGLE_URL + "q=girl";
//		
//		String result = GetJsonUtil.getJsonResult(url2);
//		//result = result.substring(14, result.length() - 1);
//		System.out.println(result);
//	}
}
