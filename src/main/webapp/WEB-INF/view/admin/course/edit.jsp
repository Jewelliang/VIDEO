<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程列表 -课程管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap.min.js"></script>
</head>
<body>

 	<jsp:include page="../header.jsp">
 		<jsp:param value="course" name="fromJsp"/>
 	</jsp:include>
	
	<div class="container">
		<div class="jumbotron">
			<h2>编辑课程 - 课程管理</h2>
		</div>
		
		<form action="admin/course/edit.action" class="form-horizontal" method="post">
			<input type="hidden" name="courseId" value="${course.id }">
			<div class="form-group">
				<label for="courseName" class="col-sm-2 control-label">标题</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="courseName"
						id="courseName" value="${course.courseName }" placeholder="请输入课程名称">
				</div>
			</div>
			
			<div class="form-group">
				<label for="id" class="col-sm-2 control-label">所属学科</label>
				<div class="col-sm-3">
					<select name="id">
						<c:forEach var="subject" items="${subjects }">
							<option value="${subject.id }">${subject.subjectName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
			<label for="insertTime" class="col-sm-2 control-label">创建时间</label>
				<div class="col-sm-3">
					<input type="date" name="insertTime">
				</div>
			</div>
			
			<div class="form-group">
			<label for="updateTime" class="col-sm-2 control-label">更新时间</label>
				<div class="col-sm-3">
					<input type="date" name="updateTime">
				</div>
			</div>
			
			
			
			<div class="form-group">
				<label for="courseDesc" class="col-sm-2 control-label">课程简介</label>
				<div class="col-sm-10">
					<textarea  class="form-control" name="courseDesc" id="courseDesc" 
						rows="3"> ${course.courseDescr } </textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
					<a href="admin/speaker/index.action" class="btn btn-default">返回列表</a>
				</div>
			</div>
			
		</form>
	</div>



</body>
</html>


