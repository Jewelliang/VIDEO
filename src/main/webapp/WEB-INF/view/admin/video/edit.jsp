<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
	<base href="${BaseContext }">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>视频列表 -视频管理</title>
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<script src="static/js/bootstrap.min.js"></script>
</head>
<body>

	
	<!-- 引入公用的导航信息 -->
	<jsp:include page="../header.jsp">
		<jsp:param value="video" name="fromJsp"/>
	</jsp:include>
	
	<div class="container">
		<div class="jumbotron">
			<h2>编辑视频 - 视频管理</h2>
		</div>

		<form class="form-horizontal" action="admin/video/edit.action"
			method="post" enctype="multipart/form-data">
			
			<input type="hidden" name="videoId" value="${video.id }">
			<div class="form-group">
				<label for="videoTitle" class="col-sm-2 control-label">标题</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="videoTitle"
						id="videoTitle" value="${video.videoTitle }" placeholder="标题">
				</div>
			</div>
			
			<div class="form-group">
				<label for="icon" class="col-sm-2 control-label">封面图片</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="icon"
						id="videoImageUrl"  value="${video.videoImageUrl }" >
				</div>
			</div>

			<div class="form-group">
				<label for="video1" class="col-sm-2 control-label">视频播放地址</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" name="video1"
						id="videoUrl"  value="${video.videoUrl }"  >
				</div>
			</div>
			
			<div class="form-group">
				<label for="videoLength" class="col-sm-2 control-label">视频时长</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="videoLength"
						id="videoLength" value="${video.videoLength }">
				</div>
			</div>
			
			   <div class="form-group">
			   	<label for="icon" class="col-sm-2 control-label">主讲人</label>
			   	<div class="col-sm-3">
				    <select name="speakerId"  class="form-control">
				    	<option value="0">请选择主讲人</option>
				    	<c:forEach items="${speakers }" var="sp">
				    		<option <c:if test="${query.speakerId==sp.id }">selected</c:if> value="${sp.id }">${sp.speakerName }</option>
				    	</c:forEach>
				    </select>
			    </div>
			  </div>
			  
			
			
			<div class="form-group">
			  	<label for="icon" class="col-sm-2 control-label">所属课程</label>
			  	<div class="col-sm-3">
				    <select name="courseId" class="form-control">
				    	<option value="0">请选择课程</option>
				    	<c:forEach items="${courses }" var="c">
				    		<option  <c:if test="${query.courseId==c.id }">selected</c:if>  value="${c.id }">"${c.courseName }" </option>
				    	</c:forEach>
				    </select>
			    </div>
			  </div>
			  
		
			
			
				
			<div class="form-group">
				<label for="insertTime" class="col-sm-2 control-label">创建时间</label>
				<div class="col-sm-3">
					<input type="date" name="insertTime" ${video.insertTime }>
				</div>
			</div>
			
			<div class="form-group">
			<label for="updateTime" class="col-sm-2 control-label">更新时间</label>
				<div class="col-sm-3">
					<input type="date" name="updateTime" ${video.updateTime }>
				</div>
			</div>
			
			<div class="form-group">
				<label for="videoDesc" class="col-sm-2 control-label">介绍</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="videoDesc"
						id="videoDesc" rows="3"> ${video.videoDescr }</textarea>
				</div>
			</div>
			
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn  btn-primary">保存</button>
					<a href="admin/video/index.action" class="btn btn-default">返回列表</a>
				</div>
			</div>
		</form>

	</div>


</body>
</html>