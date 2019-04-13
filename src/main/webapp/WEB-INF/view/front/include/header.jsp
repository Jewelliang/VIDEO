<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<script src="static/js/cookie_util.js"></script>
<script src="static/js/jquery-1.12.4.min.js"></script>
<header>
	<div class="container">
		<span>欢迎来到在线公开课学习平台</span>


		<c:if test="${not empty user}">
			<div id="userBlock" style="float:right">
				<a href="front/user/logout.action" id="logout">退出</a>
				<a href="front/user/index.action" id="account" >${sessionScope.user.email }</a>
			</div>
		</c:if>
		<c:if test="${empty user}">
			<div id="regBlock" style="float:right">
				<a href="javascript:;" id="reg_open"><img src="static/img/we.png">注册</a>
				<a href="javascript:;" id="login_open"><img src="static/img/we.png">登录</a>
			</div>
		</c:if>

		<a onclick="JavaScript:addFavorite2()"><img src="static/img/sc.png" draggable="false">加入收藏</a>
		<a target="_blank" href="admin/login.action"><img src="static/img/we.png" draggable="false">后台管理</a>
		<a class="color_e4"><img src="static/img/phone.png" draggable="false"> 0000-1234567&#x3000;&#x3000;0000-123-456</a>

	</div>
</header>
<script>
	$(function(){
		$("#logout").click(function(){
			debugger;
			delCookie('videoUser');
		})
	})
</script>