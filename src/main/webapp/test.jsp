<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>JSON/Atom Custom Search API Example</title>
    <script type="text/javascript" src="js/jquery-1.6.4.js"></script>
    
    <script type="text/javascript">
    	/*
    	function hndlr(response) 
     	{
      		for (var i = 0; i < response.items.length; i++) 
      		{
        		var item = response.items[i];
        		// in production code, item.htmlTitle should have the HTML entities escaped.
        		document.getElementById("content").innerHTML += "<br>" + item.htmlTitle;
      		}
    	}
    	*/
    	
    	$(function()
    	{
    		$("#btn").click(function()
    		{
    			$.get("https://www.googleapis.com/customsearch/v1?key=AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U&cx=001092378821530568921:6vxzfitcm3k", {q: $("#keyword").val()}, 
    			function(returnedData, status)
    			{
    				//var item = $(returnedData).find("items")[0];
    					//var item = returnedData.items[0];
    				//var link = item.link;
	    				//var pagemap = item.pagemap;
	    				//var src = pagemap.cse_image[0].src;
	    				//var html = "<img src=\"" + src +"\"" + " alt=\"macbook\"" + " width=\"135\"" + " height=\"135\""+">";
    				//alert(html);
    				
    					//$("#pic").append(html);
    				//alert(src);
    				alert(returnedData);
    			});
    		});
    	});
    	
    	
    </script>
    

    
    
    
    <!-- 
    <script src="https://www.googleapis.com/customsearch/v1?key=AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U&cx=017576662512468239146:omuauf_lfve&q=macbook&searchType=image&callback=hndlr">
    </script>
    <script src="https://www.googleapis.com/customsearch/v1?key=AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U&cx=013036536707430787589:_pqjad5hr1a&q=flowers&alt=json">
    </script>
     -->
     
  </head>
  <body>

	<input type="text" id="keyword" value="mac" /><input type="button" value="get from google json" id="btn" />
	
	<dir id="pic"></dir>
	
  </body>
</html>
