<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <base href="${BaseContext}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="Web前端视频教程,大数据视频教程,HTML5视频教程,UI视频教程,PHP视频教程,java视频教程,python基础教程">
    <meta name="description" content="在线课程视频,为您提供java,python,HTML5,UI,PHP,大数据等学科经典视频教程在线浏览学习,精细化知识点解析,深入浅出,想学不会都难,智游教育,学习成就梦想！">
    <%@include file="../include/style.html"%>
    <link href="static/css/video-js.css" rel="stylesheet" type="text/css">
    <title>在线公开课-|java|大数据|HTML5|python|UI|PHP视频教程</title>

</head>
<style>
	img.thum1{border:1px solid #09C; border-radius:150px; width:110px;height:110px;}
</style>
<body class="w100">

<jsp:include page="../include/header.jsp"></jsp:include>
<div>
    <!--面包屑导航-->
    <div class="container mian-nav">公开课 / ${subject.subjectName}</div>
    <input type="hidden" id="videoId" value="${id}">
    <div id="content">
		<%-- <%@include file="../video/content.jsp" %> --%>
		<div class="intro">
		<div class="container">
			<div class="v-intro">
				<div class="left">
					<video id="videoOne"  controls width="627" height="280"
					webkit-playsinline="" x-webkit-airplay="" preload="auto"  data-role="txp_video_tag"  
					src="/VIDEO/front/video/video.action?videoUrl=${video.videoUrl}">
					</video> 
				</div>
	
				<div class="right">
					<p class="right-title">${video.videoTitle}</p>
					<div class="avatar">
						<span><img class="thum1" alt="" src="/VIDEO/front/video/speaker.action?speakerHeadUrl=${speaker.speakerHeadUrl}"></span>
						<%-- <span style="background-image: url(${speaker.speakerHeadUrl})"></span> --%>
						<p><b>讲师：${speaker.speakerName}</b><br><i>${speaker.speakerDescr}</i></p>
					</div>
					<p class="video-intro">
						<span>本节内容：</span> ${video.videoDescr}
					</p>
				</div>
			</div>
	
			<div class="kcjs">
				<p class="title">课程介绍</p>
				<p class="content">${course.courseDescr}</p>
			</div>
	
		</div>
	</div>
	<!--目录-->
	<div class="catalog">
		<div class="container">
			<p class="title">目录</p>
	
			<c:forEach items="${videoList}" var="video" >
				<div class="chapter">
					<p class="biaoti"><a href="front/video/index.action?videoId=${video.id}&subjectId=${subjectId}">${video.videoTitle}</a></p>
					<p class="lecturer">${video.videoDescr}</p>
					<p class="lecturer">讲师：${video.speakerName}</p>
					<div class="v-info">
						<span class="count"><img src="static/img/count.png" alt="">${video.videoPlayTimes}</span>
						<span class="duration"><img src="static/img/player.png" alt="">${video.videoLengthStr}</span>
					</div>
				</div>
			</c:forEach>
	
	
		</div>
	</div>
    </div>
</div>
    
    <%@include file="../include/footer.jsp"%>

    <%@include file="../include/script.html"%>
    <script src="static/js/video.js"></script>
    <!-- <script>
        $(function () {
        	var id = $('#videoId').val();
           $('#content').load('front/video/videoData.action?videoId='+id);
      
           //播放量统计,不需要返回结果处理
           $.get('front/video/state.action?videoId='+id);
		});
    </script> -->
    <script>
		//var videoUrl = $.trim(${video.videoUrl});
		$(function(){
			//$("#videoOne").attr('src','/VIDEO/front/video/video.action?videoUrl='+${video.videoUrl});
			
			/* var newVideo =  $('<video id="videoOne"  controls width="627" height="280" webkit-playsinline="" x-webkit-airplay="" preload="auto"  data-role="txp_video_tag">').attr('src','/VIDEO/front/video/video.action?videoUrl='${video.videoUrl});
			$('.left').append(newVideo); */
			console.log(${video.videoUrl}); 	 	
		})
		
	</script>
</body>

</html>
