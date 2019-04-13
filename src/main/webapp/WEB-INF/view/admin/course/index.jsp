<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程列表 - 课程管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/jquery-confirm.min.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="../header.jsp">
		<jsp:param value="course" name="fromJsp" />
	</jsp:include>

	<div class="container">
		<div class="jumbotron">
			<h2>课程列表 - 课程管理</h2>
		</div>
		
		<div>
		<shiro:hasPermission name="add">   
			<a href="admin/course/add.action" class="btn btn-primary">添加课程</a>
		</shiro:hasPermission>   
		</div>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>标题</th>
					<th>学科</th>
					<th>简介</th>
					<shiro:hasPermission name="update">  <th>编辑</th></shiro:hasPermission>
					<shiro:hasPermission name="delete">  <th>删除</th></shiro:hasPermission>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ course }" var="sp"  varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${sp.courseName }</td>
					<td>${sp.subjectName }</td>
					
					<td>${sp.courseDescr }</td>
					
					<shiro:hasPermission name="update">   
					<td><a href="admin/course/edit.action?id=${sp.id }"><span 
						class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
					</shiro:hasPermission>
					<shiro:hasPermission name="delete">   
					<td><a href="admin/course/delete.action?id=${sp.id }"><span 
						class="glyphicon glyphicon-trash"></span></a></td>
					</shiro:hasPermission>
				</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<script src="static/js/jquery-1.12.4.min.js"></script>


</body>
</html>







