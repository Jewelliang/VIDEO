<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!--
http://www.cnblogs.com/Ryan344453696/p/4996290.html

http://www.runoob.com/bootstrap/bootstrap-navbar.html

  -->    

<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">视频管理系统
          </a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li <c:if test='${param.fromJsp=="video"}'>class="active"</c:if>><a href="admin/video/index.action">视频管理</a></li>
            <li <c:if test='${param.fromJsp=="subject"}'>class="active"</c:if>><a href="admin/subject/index.action">科目管理</a></li>
            <li <c:if test='${param.fromJsp=="course"}'>class="active"</c:if>><a href="admin/course/index.action">课程管理</a></li>
            <li <c:if test='${param.fromJsp=="speaker"}'>class="active"</c:if>><a href="admin/speaker/index.action">主讲人管理</a></li>
            <li <c:if test='${param.fromJsp=="user"}'>class="active"</c:if>><a href="admin/user/index.action">用户管理</a></li>
            
			<shiro:hasPermission name="view">            
           		<li <c:if test='${param.fromJsp=="sysuser"}'>class="active"</c:if>>
           		<a href="admin/sysuser/index.action">权限管理</a>
           		</li>
            </shiro:hasPermission>
            <shiro:hasPermission name="view">     
            	<li <c:if test='${param.fromJsp=="state"}'>class="active"</c:if>>
            	<a href="admin/state/courseavg.action">统计分析</a>
            	</li>
            </shiro:hasPermission>
          </ul>
          
          <ul class="nav navbar-nav navbar-right">
        <li><a target="_blank"  href="admin/login.action" title="点击退出登录">${sessionScope.loginName }<i class="glyphicon glyphicon-log-out" aria-hidden="true"></i></a></li>
      </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    