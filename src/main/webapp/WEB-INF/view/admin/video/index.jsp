<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${BaseContext }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频列表 - 视频管理</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/jquery-confirm.min.css" rel="stylesheet">

</head>
<body>
	<!-- 引入公用的导航信息 -->
	<jsp:include page="../header.jsp">
		<jsp:param value="video" name="fromJsp"/>
	</jsp:include>
	<%-- <%@include file="" %> --%>

	<div class="container">
		<div class="jumbotron">
  			<h2>视频列表 - 视频管理</h2>
		</div>
		<div class="row">
		
		<shiro:hasPermission name="add">
		<a href="admin/video/add.action" class="btn btn-primary">添加视频</a>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="delete">
		<button class="btn btn-primary" type="button" onclick="batchDelete();">
		  批量删除 <span class="badge" id="countSpan">0</span>
		</button>
		</shiro:hasPermission>
		
		<div style="float: right;">
			<form class="form-inline" action="admin/video/index.action" method="post">
			  <div class="form-group">
			    <input type="text" class="form-control" name="queryName"  value="${query.queryName }" placeholder="视频标题">
			  </div>
			  
			   <div class="form-group">
			    <select name="id"  class="form-control">
			    	<option value="0">请选择主讲人</option>
			    	<c:forEach items="${speakers }" var="sp">
			    		<option <c:if test="${query.speakerId==sp.id }">selected</c:if> value="${sp.id }">${sp.speakerName }</option>
			    	</c:forEach>
			    </select>
			  </div>
			  
			  <div class="form-group">
			    <select name="courseId" class="form-control">
			    	<option value="0">请选择课程</option>
			    	<c:forEach items="${courses }" var="c">
			    		<option  <c:if test="${query.courseId==c.id }">selected</c:if>  value="${c.id }">${c.courseName }</option>
			    	</c:forEach>
			    </select>
			  </div>
			  
			  <button type="submit" class="btn btn-primary">查询</button>
			</form>
		</div>
		</div>
		<form action="admin/video/batchDelete.action" name="deleteForm" method="get">
		<table class="table table-hover">
 			<thead>
 				<tr>
 					<th><input type="checkbox" id="checkAll"></th>
 					<th>序号</th>
 					<th>名称</th>
 					<th>介绍</th>
 					<th>讲师</th>
 					<th>课程</th>
 					<th>时长(秒 )</th>
 					<th>播放次数</th>
 					<shiro:hasPermission name="update"><th>编辑</th></shiro:hasPermission>
 					<shiro:hasPermission name="delete"><th>删除</th></shiro:hasPermission>
 				</tr>
 			</thead>
 			<tbody>
 				<%-- <c:if test="${not empty results }">
 					<c:forEach items="${results }" var="sp" varStatus="i"> --%>
 					<c:if test="${not empty pageInfo }">
					<c:forEach items="${pageInfo.results }" var="sp" varStatus="i">
					
 					
		 				<tr>
		 					<td><input type="checkbox" name="checkid" value="${sp.id }" onclick="countCheck();"> </td>
		 					<td>${i.index+1 }</td>
		 					<td>${sp.videoTitle }</td>
		 					<td>${sp.videoDescr }</td>
		 					<td>${sp.speakerName }</td>
		 					<td>${sp.courseName }</td>
		 					<td>${sp.videoLength}</td>
		 					
		 					<td>${sp.videoPlayTimes }</td>
		 					<shiro:hasPermission name="update">
		 					<td><a href="admin/video/edit.action?id=${sp.id }"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a></td>
		 					</shiro:hasPermission>
		 					<shiro:hasPermission name="delete">
		 					<td>
			 					<a href="#" onclick="return deleteInfo(${sp.id });">

			 					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			 					</a>
		 					</td>
		 					
		 					</shiro:hasPermission>
		 				</tr>
		 				
		 				
	 				</c:forEach>
 				</c:if>
 				<%-- <c:if test="${empty results }">
 					<tr><td>当前查询结果为空!</td></tr>
 				</c:if> --%>
 				<c:if test="${empty pageInfo }">
					<tr>
						<td>当前查询结果为空</td>
					</tr>
				</c:if>
 				
 			</tbody>
		</table>
	</form>
	<jsp:include page="../page.jsp"></jsp:include>
	</div>
	
	<form id="pageForm" action="admin/video/index.action" method="post">
		<input type="hidden" name="queryName" value="${queryName }"> <input
			type="hidden" name="queryJob" value="${queryJob }"> <input
			type="hidden" name="pageNum" value="${pageNum }" id="queryPage">
	</form>


	<script type="text/javascript">
		function queryPage(page) {
			$('#queryPage').val(page);
			$('#pageForm').submit();
		}
	</script>
<script src="static/js/jquery-1.12.4.min.js"></script>
<script src="static/js/jquery-confirm.min.js"></script>
<script src="static/js/jquery.json.js"></script>

<script>


$(function(){

	//全选
	$("#checkAll").click(function(){
		var check = $(this).is(":checked");
		//根据全选复选框的状态，来操作上边三个复选框的状态
		var checkboxes = document.getElementsByName('checkid');
		for(var i=0;i<checkboxes.length;i++){
			var c = checkboxes[i];//当前遍历到的checkbox
			if(check){
				//如果是全部选中的状态，则把当前遍历的check选中
				c.checked=true;
			}else{	
				//如果是不选的状态，则把当前遍历的check非选中
				c.checked=false;
			}	
		}
		//在修改全选的状态之后调用计数
		countCheck();
	})
	function checkAll(){
		debugger;
		var ca = document.getElementById('checkAll');
		console.log("是否选中："+ca.checked);
		
	}
	
	//计数功能，计算有多少复选框选中了
	function countCheck(){
		console.log('...count');
		var count=0;
		//遍历复选框，对选中的进行计数
		var checkboxes = document.getElementsByName('checkid');
		for(var i=0;i<checkboxes.length;i++){
			var c = checkboxes[i];//当前遍历到的checkbox
			if(c.checked){
				count++;
			}
		}
		//把计数更新到按钮徽章上
		var span = document.getElementById('countSpan');
		span.innerHTML=count;
		
	}
	//批量删除操作
	function batchDelete(){
		//http://blog.csdn.net/anialy/article/details/8295765
		// springMvc 参数如果想接收复杂类型比如list<object> ，我们一个
		//比较常见的方案，是把数据以json 形式传过去
		var totalArr = [];
		$('input:checkbox[name=checkid]:checked').each(function(){
			var video=new Object();
			video.videoId = $(this).val();
			video.videoTitle="测试";
			totalArr.push(video);
		  });
				
		console.log(JSON.stringify(totalArr));
		
		 $.ajax({    
	            type: "POST",   
	            url: "http://localhost:8080/VIDEO/admin/video/batchDelete.action",   
	            data:JSON.stringify(totalArr),
	            // 参数类型是json 形式
	            contentType:"application/json",    
	            dataType: "json",
	            success: function (response) {
	        		console.log(response['content']);
	        		location.reload();    
	            }, error: function (re) {    
	                alert("error");    
	            }    
	        })      
		
	}
	
	//删除数据操作
	function deleteInfo(id){
		 debugger;
		//admin/video/delete.do	
		$.confirm({
			title:'删除确认提示',
			content:'确定要删除id为'+id+'的数据吗？',
			type:'red',
			buttons:{
				confirm:{
					text:'删除',
					btnClass:'btn-primary',
					action:function(){
						//根据id发送删除请求ajax
						$.get('admin/video/delete.action',{id:id},function(data){
							if(data.success){
								//成功，刷新页面
								$.alert({
									content:'删除数据成功',
									onAction:function(){
										location.reload();
									}
								});
								
								//location.href='';
							}else{
								$.alert(data.message);
							}
							
						},'json');
						
						
					}
				},
				cancel:function(){
					//取消删除，自动关闭弹窗，不做其他操作
					
				}
			}
		});	
		
		return false;
	}
	
})
</script>






<!-- 	
<script>
	function checkAll(){
		var ca = document.getElementById('checkAll');
		console.log("是否选中："+ ca.checked);
		var check = ca.checked;
		var checkboxes = doucument.getElementsByName('checkid');
		for(var i=0; i<checkboxes.length;i++){
			var c = checkboxes[i];
			if(check){
				c.checked=true;
			}else{
				c.checked=false;
			}
		}
		countCheck();
	}
	
	function countCheck(){
		console.log('...count');
		var count=0;
		var checkboxes = document.getElementsByName('checkid');
		for(var i=0; i<checkboxes.length; i++){
			var c = checkboxes[i];
			if(c.checked){
				count++;
			}
		}
		
		var span = document.getElementById('countSpan');
		span.innerHTML=count;
	}

	function batchDelete(){
		var totalArr=[];
		$('input:checkbox[name=checkid]:checked').each(function(){
			var video=new Object();
			video.id=$(this).val();
			videl.videoTitle="测试";
			totalArr.push(video);
			
		});
		
		console.log(JSON.stringify(totalArr));
		
		$.ajax({
			type:"POST",
			url:"http://localhost:8080/VIDEO/admin/video/batchDelet.action",
			data:JSON.stringify(totalArr),
			
			contentType:"application/json",
			dataType:"json",
			success:function(response){
				console.log(response['content']);
				location.reload();
			},error:function(re){
				alert("error");
			}
		})
		
	}
	 
	function deleteInfo(id){
		$.confirm({
			title:'删除确认提示',
			content:'确定要删除id为'+id+'的数据吗？',
			type:'orange',
			buttons:{
				confirm:{
					text:'删除',
					btnClass:'btn-primary',
					action:function(){
						$.get('admin/video/delete.action',{id:id},function(data){
							if(data.success){
								$.alert({
									content:'删除数据成功',
									onAction:function(){
										location.reload();
									}
								});
							}else{
								$.alert(data.message);
							}
						},'json');
					}
				},
				cancel:function(){
					//取消删除，自动关闭弹窗，不做其他操作
				}
			}
		})
		return false;
	}
	
</script> -->
	
	
	
	

	
</body>
</html>




