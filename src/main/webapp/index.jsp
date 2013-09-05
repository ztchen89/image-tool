<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Get Images From google or flickr API</title>
<script type="text/javascript" src="js/jquery-1.6.4.js"></script>

<script type="text/javascript">
	$(function()
	{
		$("#btn").click(function()
		{
			
			$.get("GetImagesServlet", 
			{
				keyword: $("#keyword").val(), 
				imageSource: $("input[name=imageSource]:checked").val()
			},function(returnedData, status)
			{
				$("#showImage").empty();
				
				var html="";
				
				for ( var i = 0; i < returnedData.length; i++) 
				{
					html += "<img src=\"" + returnedData[i] + "\" width=\"150\" height=\"150\">";
					//alert(returnedData[i]);
				}
				
				$("#showImage").append(html);
			});
		});
	});


</script>

</head>
<body>

	
	<input type="radio" name="imageSource" value="google" checked="checked" />google
	<input type="radio" name="imageSource" value="flickr" />flickr
	<input type="text" id="keyword" value="macbook" />
	<input type="button" value="search" id="btn" />
	<input type="button" value="save" id="save_btn" />

	<div id="showImage"></div>
</body>
</html>