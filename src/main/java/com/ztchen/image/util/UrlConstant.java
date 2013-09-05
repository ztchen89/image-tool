package com.ztchen.image.util;

public class UrlConstant 
{
	private static String GOOGLE_KEY = "AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U";
	private static String GOOGLE_CX = "001092378821530568921:6vxzfitcm3k";
	private static String FLICKR_KEY = "7676b0a957164c2720df6d5525f02362";
	private static String FLICKR_PER_PAGE = "100";
	public static final String GOOGLE_URL="https://www.googleapis.com/customsearch/v1?key=" + GOOGLE_KEY + "&cx="+ GOOGLE_CX + "&alt=json&searchType=image&imgSize=large&q=";
	public static final String FLICKR_URL="http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" + FLICKR_KEY + "&per_page="+FLICKR_PER_PAGE+"&format=json&text=";
	
}
