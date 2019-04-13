<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台用户列表 - 前台用户管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<jsp:include page="../header.jsp">
		<jsp:param value="frontuser" name="fromJsp" />
	</jsp:include>

	<div class="container">

		<div class="jumbotron">
			<h2>用户管理</h2>
		</div>

		<div class="row">

			<div style="float: right;">
				<form class="form-inline" action="admin/user/index.action"
					method="post">
					<div class="form-group">
						<label for="exampleInputName2">名称</label> <input type="text"
							class="form-control" name="queryName" id="exampleInputName2"
							value="${queryName }" placeholder="用户昵称">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">email</label> <input type="text"
							class="form-control" name="queryJob" id="exampleInputEmail2"
							value="${queryEmail }" placeholder="用户email">
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
				</form>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>昵称</th>
					<th>邮箱</th>
					<th>性别</th>
					<th>省份</th>
					<th>城市</th>
					<th>生日</th>
					<th>注册时间</th>
					<shiro:hasPermission name="update">  <th>编辑</th></shiro:hasPermission>
 					<shiro:hasPermission name="delete">  <th>删除</th></shiro:hasPermission>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ user }" var="user"  varStatus="u">
				<tr>
					<td>${u.index+1 }</td>
					<td>${user.nickName }</td>
					<td>${user.email }</td>
					<td>${user.sex }</td>
					<td>${user.province }</td>
					<td>${user.city }</td>
					<td>${user.birthday }</td>
					<td>${user.insertTime }</td>
					
					<shiro:hasPermission name="update">   
					<td><a href="admin/user/edit.action?id=${user.userId }"><span 
						class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
					</shiro:hasPermission>
					<shiro:hasPermission name="delete">   
					<td><a href="admin/user/delete.action?id=${user.userId }"><span 
						class="glyphicon glyphicon-trash"></span></a></td>
					</shiro:hasPermission>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		
	</div>

	<form id="pageForm" action="admin/user/index.action" method="post">
		<input type="hidden" name="queryName" value="${queryName }"> <input
			type="hidden" name="queryJob" value="${queryJob }"> <input
			type="hidden" name="pageNum" value="${pageNum }" id="queryPage">
	</form>



	<script src="static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		function queryPage(page) {
			$('#queryPage').val(page);
			$('#pageForm').submit();
		}
	</script>


</body>
</html>







