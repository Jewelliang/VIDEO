<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主讲人列表 - 课程管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

	<jsp:include page="../header.jsp">
		<jsp:param value="speaker" name="fromJsp" />
	</jsp:include>

	<div class="container">

		<div class="jumbotron">
			<h2>主讲人列表 - 主讲人管理</h2>
		</div>

		<div class="row">
			<shiro:hasPermission name="add">
				<a href="admin/speaker/add.action" class="btn btn-primary">添加主讲人</a>
			</shiro:hasPermission>
			<div style="float: right;">
				<form class="form-inline" action="admin/speaker/index.action"
					method="post">
					<div class="form-group">
						<label for="exampleInputName2">名称</label> <input type="text"
							class="form-control" name="queryName" id="exampleInputName2"
							value="${queryName }" placeholder="主讲人名称">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">职位</label> <input type="text"
							class="form-control" name="queryJob" id="exampleInputEmail2"
							value="${queryJob }" placeholder="主讲人职位">
					</div>
					<button type="submit" class="btn btn-primary">查询</button>
				</form>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>职位</th>
					<th>简介</th>
					<shiro:hasPermission name="speaker:update">
						<th>编辑</th>
					</shiro:hasPermission>
					<shiro:hasPermission name="speaker:delete">
						<th>删除</th>
					</shiro:hasPermission>

				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pageInfo }">
					<c:forEach items="${pageInfo.results }" var="sp" varStatus="i">
					
					
						<tr>
							<td>${i.index+1 }</td>
							<td>${sp.speakerName }</td>
							<td>${sp.speakerJob }</td>
							<td>${sp.speakerDescr }</td>
							<shiro:hasPermission name="update">
								<td><a href="admin/speaker/edit.action?id=${sp.id }"><span
										class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
							</shiro:hasPermission>
							<shiro:hasPermission name="delete">
								<td><a
									href="admin/speaker/delete.action?id=${sp.id }"><span
										class="glyphicon glyphicon-trash"></span></a></td>
							</shiro:hasPermission>
						</tr>
						
						
					</c:forEach>
				</c:if>
				<c:if test="${empty pageInfo }">
					<tr>
						<td>当前查询结果为空</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<jsp:include page="../page.jsp"></jsp:include>
	</div>

	<form id="pageForm" action="admin/speaker/index.action" method="post">
		<input type="hidden" name="queryName" value="${queryName }"> <input
			type="hidden" name="queryJob" value="${queryJob }"> <input
			type="hidden" name="pageNum" value="${pageNum }" id="queryPage">
	</form>

	<shiro:authenticated>  
		    用户已身份验证通过 
		    用户已经身份验证通过，即Subject.login登录成功，不是记住我登录的
	</shiro:authenticated>
	<br>
	<shiro:notAuthenticated>
		    未身份验证（包括记住我）
		    即没有调用Subject.login进行登录，包括记住我自动登录的也属于未进行身份验证。 
	 
	 </shiro:notAuthenticated>
	<br>

	<shiro:lacksPermission name="speaker:add">  
	   	 用户没有权限speaker:add<br />
	</shiro:lacksPermission>
	<br>
	<shiro:user>  
		欢迎登录，<a href="${pageContext.request.contextPath}/admin/logout.action">退出</a>  
		用户已经身份验证/记住我登录后显示相应的信息
	</shiro:user>



	<script src="static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">
		function queryPage(page) {
			$('#queryPage').val(page);
			$('#pageForm').submit();
		}
	</script>


</body>
</html>







