<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$.ajax({
		type : "GET",
		url : "http://192.168.20.211:8080/VIDEO/sign/test.action",
		success : function(a) {
			alert("hhhhh");
			console.log(a);
			/* alert(response);
			location.reload(); */
			var mydiv = document.getElementById("mydiv");
			mydiv.innerHTML=a;			
		},
		error : function(re) {
			alert("error");
		}
	})
</script>
</head>
<body>

	<div id="mydiv"
		style="width: 200px; height: 200px; background-color: yellow">
	</div>



</body>
</html>