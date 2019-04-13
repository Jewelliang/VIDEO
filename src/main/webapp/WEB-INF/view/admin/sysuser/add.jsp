<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<jsp:param value="user" name="fromJsp"/>
	</jsp:include>
	<div class="container">
		<div class="jumbotron">
			<h2>权限管理</h2>
		</div>
		
		<!-- 
		<form .....method="post"..............>  
			姓名1：<input  type="text" name="id"   value="">  
			年龄1：<input  type="text" name="age"   value="">  
			地址1：<input  type="text" name="address"   value="">  
			  
			姓名2：<input  type="text" name="id"   value="">  
			年龄2：<input  type="text" name="age"   value="">  
			地址2：<input  type="text" name="address"   value="">
			<pre code_snippet_id="1597573" snippet_file_name="blog_20160304_1_2164278" name="code" class="html">
		</form>

 		-->




		<form class="form-horizontal" action="admin/sysuser/add.action" method="post" enctype="multipart/form-data">
		
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">管理员账号名</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="username"
						id="username" placeholder="用户名">
				</div>
			</div>
			
			
			
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">密码</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="password" id="password" placeholder="密码">
				</div>
			</div>
			
			<%-- 
			<div class="form-group">
			  	<label for="icon" class="col-sm-2 control-label">权限</label>
			  	<div class="col-sm-3">
				    <select name="roleIds" class="form-control">
				    	<option value="0">请选择权限</option>
				    	<c:forEach items="${role }" var="r">
				    		<option  <c:if test="${query.id==r.id }">selected</c:if>  value="${r.id }">${r.role }</option>
				    	</c:forEach>
				    </select>
				  </div>
			  </div>
			  --%>
			 
			 <div class="form-group has-success">
			 <label for="roleIds" class="col-sm-2 control-label">角色</label>
					<div class="col-sm-2">
						最高权限&nbsp;&nbsp;<input type="checkbox"  id="roleIds" name="roleIds" value="1" >
					</div>
					<div class="col-sm-2">
						可查看\添加\编辑 &nbsp;&nbsp;<input type="checkbox"  id="roleIds" name="roleIds" value="2"  >
					</div>
					<div class="col-sm-2">
						可查看\添加 &nbsp;&nbsp;<input type="checkbox"  id="roleIds" name="roleIds" value="3">
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