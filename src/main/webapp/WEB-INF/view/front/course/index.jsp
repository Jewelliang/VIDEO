<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<base href="${BaseContext}">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta name="keywords"
	content="Web前端视频教程,大数据视频教程,HTML5视频教程,UI视频教程,PHP视频教程,java视频教程,python基础教程">
<meta name="description"
	content="智游教育在线课程视频,为您提供java,python,HTML5,UI,PHP,大数据等学科经典视频教程在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,智游教育,学习成就梦想！">
<%@include file="../include/style.html"%>
<title>在线公开课-智游教育|java|大数据|HTML5|python|UI|PHP视频教程</title>
</head>

<body class="w100">
	<jsp:include page="../include/header.jsp"></jsp:include>

	<div id="app">
		<!--banner图-->
		<div class="banner"
			style="background-image: url('static/img/banner-${subjectId}.jpg')"></div>

		<!--面包屑导航-->
		<div class="container mian-nav" id="navDiv">公开课 /${subject.subjectName }</div>
		<input type="hidden" id="subjectId" value="${subjectId}">
		<div class="classify">
			<div class="container" id="dataContainer">
			<c:forEach items="${courses }" var="course">
				<div class="section">
					<div class="classifyName">  
					
						<p class="title title-first">${course.courseName }</p>
					</div>
					<div class="kcIntro">
						<p class="int">
							<span>课程介绍：</span>
							${course.courseDescr }
						</p>
					</div>
					<ul>
						<c:forEach items="${course.videoList }" var="video">
							<li class="section-main" onclick="getVideo(${video.id})">
							<%-- <div class="thum" style="background-image: url(${video.videoImageUrl})"></div> --%>
							<img class="thum" alt="" src="/VIDEO/front/video/video.action?videoUrl=${video.videoImageUrl}">
								<p>${video.videoTitle }</p>
								<div class="classify-v-info">
									<span class="count" title="观看次数">
									<img src="static/img/count.png" alt="">${video.videoPlayTimes }</span>
									<span class="duration" title="视频时长">
										<img src="static/img/player.png" alt="">${video.videoLengthStr }</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:forEach>


			</div>

		</div>
	</div>

	<%@include file="../include/footer.jsp"%>

	<%@include file="../include/script.html"%>
	
	<script src="static/js/cookie_util.js"></script>
	
	 <script type="text/javascript">
	 	
	 		debugger;
	 		
			function getVideo(id){
				/* debugger;
				var subjectId=$('#subjectId').val();
				$.ajax({
					url:'front/video/index.action',
					type:'post',
					data:{"videoId":id,"subjectId":subjectId},
					dataType:"json",
					success:function(result){
						if(result.success){
							window.location.href='front/video/index.action?videoId='+id+'&subjectId='+subjectId;
						}
					},
					error:function(result){
						alert(result.errorMessage);
						$('#loginDiv').removeClass('hidden');
					}
				
				}); */
				
				
				/* var subjectId=$('#subjectId').val();
				$.post('front/video/index.action',function(result){
					debugger;
					if(result.success){
						location.href='front/video/index.action?videoId='+id+'&subjectId='+subjectId;
					}else{
						alert(result.errorMessage);
						$('#loginDiv').removeClass('hidden');
					}
				},'json'); */
				
				 debugger;
				var cook = getCookie('videoUser');
				alert(cook);
				//var cook = "${videoUser}";
				if (cook == null || cook == "") {
					alert('请先登录！')
					$('#loginDiv').removeClass('hidden');  // 显示登录弹框 
	            	
	            }else{
					var subjectId=$('#subjectId').val();
					//SetCookie("videoUser",user);
					location.href='front/video/index.action?videoId='+id+'&subjectId='+subjectId;
	            } 
				
			}
		
	</script> 

</body>

</html>