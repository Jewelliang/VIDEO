<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频添加 - 视频管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap.min.js"></script>
</head>

<button type="button" class="btn btn-info" onclick="buyShop(1)">购买</button>

<body>

	<script src="static/js/jquery-1.12.4.min.js"></script>
	<script src="static/js/jquery-confirm.min.js"></script>
	<script>
		
		//购买
		function buyShop(id) {
			
			for (var i = 0; i < 20; i++) {

				//根据id发送删除请求ajax
				$.get('shop/buy.action', {
					id : id
				}, function(data) {
					if (data['errorCode'] == 0) {

						console.log('购买成功');
						//location.href='';
					} else {

					}

				}, 'json');
			}
			
			

		}
	</script>


</body>
</html>