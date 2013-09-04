<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var config = {
		append : false,
		perPage : 8,
		page : 0
	}
	$('#searchForm').submit(function() {
		googleSearch();
		return false;
	});
	function googleSearch(settings) {
		settings = $.extend({}, config, settings);
		settings.term = $('#s').val();
		var apiURL = 'https://www.googleapis.com/customsearch/v1?&cx=001092378821530568921:6vxzfitcm3k&key=AIzaSyDH8LEAZOjX5xoyRpySBezTMAPyyzvU84U&callback=?';
		var resultsDiv = $('#resultsDiv');
		$.getJSON(apiURL, {
			q : settings.term,
			rsz : settings.perPage,
			start : settings.page * settings.perPage
		}, function(r) {
			var results = r.responseData.results;
			if (results.length) {
				var pageContainer = $('<div>', {
					'class' : 'pageContainer'
				});
				for ( var i = 0; i < results.length; i++) {
					pageContainer.append(new result(results[i]) + '');
				}
				if (!settings.append) {
					resultsDiv.empty();
				}
				pageContainer.appendTo(resultsDiv).fadeIn('slow');
				var cursor = r.responseData.cursor;
				if (+cursor.estimatedResultCount > (settings.page + 1)
						* settings.perPage) {
					$('<div>', {
						'class' : 'moreresults',
						html : 'More…'
					}).appendTo(resultsDiv).click(function() {
						googleSearch({
							append : true,
							page : settings.page + 1
						});
						$(this).fadeOut();
					});
				}
			} else {
				resultsDiv.empty();
				$('<p>', {
					'class' : 'notFound',
					html : '没找到你想搜神马⋯'
				}).hide().appendTo(resultsDiv).fadeIn();
			}
		});
	}
	function result(r) {
		arr = [
				'<section class="webResult">',
				'<header><h2 class="entriestitle"><a href="',r.unescapedUrl,'">',
				r.title, '</a></h2></header>',
				'<div class="articlecontent"><p>', r.content, '</p></div>',
				'</section>' ];
		this.toString = function() {
			return arr.join('');
		}
	}
</script>

</head>
<body>
	<form id="searchForm" method="post">
		<input id="s" type="text" value="搜索内容..." />
	</form>
</body>
</html>