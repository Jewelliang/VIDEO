<%@ page language="java" pageEncoding="UTF-8"%>
<!--页脚-->
<footer>
	<ul>
		<li class="mt25">
			<h1>明天会更好</h1>
			
		</li>
		<li class="mt25">
			<h3>校区地址</h3>
			<ul>
				<li>东风校区<br>河南-中原区-郑州轻工业学院东风校区</li>
				<li>科学校区一<br>河南-高新区-郑州轻工业学院科学校区</li>
			</ul>
		</li>
		<li class="mt25">
			<h3>联系我</h3>
			<ul id="foo_icon">
				<li>河南-郑州轻工业学院-3G1401-541413140121-梁经伟</li>
				<li>e-mail:ljw77721@163.com</li>
				<li>电话:0000-1234567 0000-123-456</li>
				<li class="erwei">
					<br>
					<div>
						<img class="weixin" src="static/img/wx1.png" alt="" draggable="false">
						<!-- <img class="weibo" src="static/img/微博公众号.png" alt="" draggable="false"> -->
					</div>
				</li>
			</ul>
		</li>
	</ul>
	<div class="record">在线公开课学习平台————您正确的选择</div>
</footer>


<!--登录注册弹出框-->
<div class="mask hidden" id="loginDiv">
	<div class="mask_content">
		<div class="mask_content_header">
			<img src="static/img/logo.png" alt="" class="ma">
		</div>
		<div class="mask_content_body">
			<form  id="loginForm" action="front/user/login.action" method="post">
				<h3>快速登录</h3>
				<input type="email" id="loginEmail" placeholder="请输入邮箱" name="email">
				<input type="password" id="loginPassword" placeholder="请输入密码" name="password">
				<div id="forget">
					<a href="forgetpwd.action">忘记密码？</a>
				</div>
				<input type="submit" value="登&#x3000;录">
			</form>
		</div>
		<div class="mask_content_footer">
			<span id="login_close">关&#x3000;闭</span>
		</div>
	</div>
</div>

<div class="mask hidden" id="regDiv">
	<div class="mask_content">
		<div class="mask_content_header">
			<img src="static/img/logo.png" alt="" class="ma">
		</div>
		<div class="mask_content_body">
			<form id="regForm" action="front/user/regist.action" method="post" >   					<!--   -->
				<h3>新用户注册</h3>
				<input type="email" id="regEmail" placeholder="请输入邮箱" name="email">
				<input type="password" id="regPsw" placeholder="请输入密码" name="passWord">
				<input type="password" id="regPswAgain" placeholder="请再次输入密码" name="pswAgain">
				<!-- <div id="yzm" class="form-inline">
					<input type="text" name="yzm" style="width: 45%; display: inline-block;">
					<div id="v_container" style="width: 45%;height: 40px;float:right;"></div>
				</div> -->
				<input type="submit" value="注&#x3000;册">
			</form>
		</div>
		<div class="mask_content_footer">
			<span id="reg_close">关&#x3000;闭</span>
		</div>
	</div>
</div>
