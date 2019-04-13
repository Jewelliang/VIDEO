<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<jsp:param value="sysuser" name="fromJsp" />
	</jsp:include>

	<div class="container">
		<div class="jumbotron">
			<h2>权限管理</h2>
		</div>
		
		<div>
			<a href="admin/sysuser/add.action" class="btn btn-primary">添加管理员</a>
		</div>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>权限</th>
					<th>编辑</th>
					<th>删除</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ sysuser }" var="sys"  varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${sys.username }</td>
					<td>${sys.roleIds }</td>
	
					<td><a href="admin/sysuser/edit.action?id=${sys.id }"><span 
						class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
					<td><a href="admin/sysuser/delete.action?id=${sys.id }"><span 
						class="glyphicon glyphicon-trash"></span></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<script src="static/js/jquery-1.12.4.min.js"></script>


</body>
</html>







