<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主讲人列表 -主讲人管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap.min.js"></script>
</head>
<body>

	
	<!-- 引入公用的导航信息 -->
	<jsp:include page="../header.jsp">
		<jsp:param value="speaker" name="fromJsp"/>
	</jsp:include>
	
	<div class="container">
		<div class="jumbotron">
			<h2>编辑主讲人 - 主讲人管理</h2>
		</div>

		<form class="form-horizontal" action="admin/speaker/add.action"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="speakerName" class="col-sm-2 control-label">名字</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="speakerName"
						id="speakerName" placeholder="主讲人姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="speakerJob" class="col-sm-2 control-label">职业</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="speakerJob"
						id="speakerJob" placeholder="主讲人职业">
				</div>
			</div>
			<div class="form-group">
				<label for="icon" class="col-sm-2 control-label">头像图片</label>
				<div class="col-sm-5">
					<input type="file" class="form-control" name="icon"
						id="speakerHeadUrl" >
				</div>
			</div>

			<div class="form-group">
				<label for="insertTime" class="col-sm-2 control-label">创建时间</label>
				<div class="col-sm-3">
					<input type="date" name="insertTime">
				</div>
			</div>
			
			
			<div class="form-group">
				<label for="speakerDescr" class="col-sm-2 control-label">简介</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="speakerDescr"
						id="speakerDescr" rows="3"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn  btn-primary">保存</button>
					<a href="admin/speaker/index.action" class="btn btn-default">返回列表</a>
				</div>
			</div>
		</form>

	</div>


</body>
</html>