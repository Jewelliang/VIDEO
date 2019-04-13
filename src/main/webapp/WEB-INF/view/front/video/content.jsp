<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhouyc
  Date: 2017/6/30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<script>
	
</script>
<div class="intro">
	<div class="container">
		<div class="v-intro">
			<div class="left">
			<video  controls width="627" height="280"
			webkit-playsinline="" x-webkit-airplay="" preload="auto"  data-role="txp_video_tag"  
			src="/front/video/video.action?videoUrl=${video.videoUrl}">
			</video> 
			
			<%-- <video autoplay controls preload='none' class='hotVideo' style='width: 100%; height: auto;margin: 0 auto;' 
			src="/VIDEO/front/video/video.action?videoUrl="${video.videoUrl}>
			</video> --%>
			
			<%-- <video   controls width="627" height="280" autoplay preload='auto' data-role="txp_video_tag"  class='hotVideo'  
			src="/VIDEO/front/video/video.action?videoUrl="+${video.videoUrl}>
			</video> --%>
			
				<%--  
				<video id="videoPlayer" src="http://ugcydzd.qq.com/m1409eu0et8.mp4?sdtfrom=v1010&guid=41c7d4a11db1697bb636a514e6ac2a7e&vkey=81C72321A39307A867B1D6C198B0AEDD919397CDA5D84F5E073831D557DD6EA2D6A88B323826C6C7C7097FF783473FD16D9E27C7DF82D704C543825F0FEEAB8AFF23675256182CA72A07481DDE1E8BF25B88DBC7458F45F661EBDDC1EE9B0B814926C6AD5B392F200002A2CFAB3C7823166CD3C5DB2DDC82" 
				class="video-js vjs-default-skin" controls width="627" height="280"
					   webkit-playsinline data-role="txp_video_tag" preload="auto" poster="${video.videoImageUrl}" data-setup="{}">
				</video>  
				<EMBED id="videoPlayer" 
				src="blob:http://www.iqiyi.com/0345b023-bd9d-47c4-a04e-f030d52d5c91" 
				width="627" height="280" type="audio/x-wav" loop="false" >
				</EMBED>
				<video width="627" height="300" x-webkit-airplay="allow" 
				src="blob:http://www.iqiyi.com/0345b023-bd9d-47c4-a04e-f030d52d5c91"></video> --%>
			</div>

			<div class="right">
				<p class="right-title">${video.videoTitle}</p>
				<div class="avatar">
					<span style="background-image: url(${speaker.speakerHeadUrl})"></span>
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