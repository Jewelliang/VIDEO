<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目列表 - 科目管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<jsp:include page="../header.jsp">
		<jsp:param value="subject" name="fromJsp" />
	</jsp:include>

	<div class="container">

		<div class="jumbotron">
			<h2>科目列表 - 科目管理</h2>
		</div>

		<div class="row">
		<shiro:hasPermission name="add">   
			<a href="admin/subject/add.action" class="btn btn-primary">添加科目</a>
		</shiro:hasPermission>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>科目序号</th>
					<th>科目名称</th>
					<shiro:hasPermission name="update">  <th>编辑</th></shiro:hasPermission>
					<shiro:hasPermission name="delete">  <th>删除</th></shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ subject }" var="sub"  varStatus="s">
				<tr>
					<td>${s.index+1 }</td>
					<td>${sub.subjectName }</td>
	
					<shiro:hasPermission name="update">   
					<td><a href="admin/subject/edit.action?id=${sub.id }"><span 
						class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
					</shiro:hasPermission>
					<shiro:hasPermission name="delete">   
					<td><a href="admin/subject/delete.action?id=${sub.id }"><span 
						class="glyphicon glyphicon-trash"></span></a></td>
					</shiro:hasPermission>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>

	<form id="pageForm" action="admin/subject/index.action" method="post">
		<input type="hidden" name="queryName" value="${queryName }"> <input
			type="hidden" name="queryJob" value="${queryJob }"> <input
			type="hidden" name="pageNum" value="${pageNum }" id="queryPage">
	</form>

	<script src="static/js/jquery-1.12.4.min.js"></script>


</body>
</html>







