<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	
	<!-- 引入公用的导航信息 -->
	<jsp:include page="../header.jsp">
		<jsp:param value="speaker" name="fromJsp"/>
	</jsp:include>
	
	<div class="container">
		<div class="jumbotron">
			<h2>编辑课程 - 课程管理</h2>
		</div>

		<form class="form-horizontal" action="admin/course/add.action"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="speakerName" class="col-sm-2 control-label">课程名称</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="courseName"
						id="speakerName" placeholder="课程名称">
				</div>
			</div>
			
			<div class="form-group">
			  	<label for="icon" class="col-sm-2 control-label">所属学科</label>
			  	<div class="col-sm-10">
				    <select name="subjectId" class="form-control">
				    	<option value="0">请选择课程</option>
				    	<c:forEach items="${subjects }" var="s">
				    		<option  <c:if test="${query.subjectId==s.id }">selected</c:if>  value="${s.id }">${s.subjectName }</option>
				    	</c:forEach>
				    </select>
				  </div>
			  </div>
			 
			<%-- <div class="form-group">
						<select name="speakerId" class="form-control">
							<option value="0">请选择主学科</option>
							<c:forEach items="${subject }" var="sp">
								<option <c:if test="${query.speakerId==sp.speakerId }">selected</c:if> value="${subject.subjectId }">${subject.subjectName }</option>
							</c:forEach>
						</select>
			</div>
			 --%>
			
			<div class="form-group">
				<label for="insertTime" class="col-sm-2 control-label">创建时间</label>
				<div class="col-sm-3">
					<input type="date" name="insertTime">
				</div>
			</div>
			
			<div class="form-group">
				<label for="courseDescr" class="col-sm-2 control-label">课程简介</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="courseDescr"
						id="courseDescr" rows="3"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn  btn-primary">保存</button>
					<a href="admin/course/index.action" class="btn btn-default">返回列表</a>
				</div>
			</div>
		</form>

	</div>


</body>
</html>