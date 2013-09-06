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
    <style type="text/css">
    
    ul{list-style-type:none; margin:0;width:100%; }
	ul li{ width:200px; float:left;}
    
    
    </style>
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
    			$.get("https://www.googleapis.com/customsearch/v1?key=AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U&cx=001092378821530568921:6vxzfitcm3k&alt=json&searchType=image&imgSize=large", {q: $("#keyword").val()}, 
    			function(returnedData, status)
    			{
    				
    				$("div[id=pic] > ul").empty();
					var html = "";
					
					for(var i = 0; i < 10; i++)
					{
						var link = returnedData.items[i].link;
						//var title = returnedData.items[i].title;
						html += "<li><a href='" + link +"' target='_blank'>" + "<img src='" + link + "' width='180' height='150' />" + "</a>" + "</li>";
					}
					
					//alert(html);
					/*
    				for ( var i = 0; i < returnedData.length; i++) 
    				{
    					var link = returnedData.items[i].link;

    					html += "<img src=\"" + link +"\"" + " alt=\"macbook\"" + " width=\"135\"" + " height=\"135\""+">";
					}
    				
    				alert(html);*/
    				
    				$("div[id=pic] > ul").append(html);
    			});
    		});
    	});
    	
    	
    </script>
    
    
     
  </head>
  <body>

	<input type="text" id="keyword" value="mac" /><input type="button" value="get from google json" id="btn" />
	
	<div id="pic" style="height: 2380px;">
		<ul id="list"></ul>
	</div>
	
  </body>
</html>
