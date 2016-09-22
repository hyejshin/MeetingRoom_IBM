<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dao.AdminDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자페이지</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<!-- navigation bar -->
	<%@ include file="header.jsp"%>	
	<h3>&nbsp;&nbsp;${project} 관리자 페이지</h3>


	<ul class="nav nav-pills">
		<li><a href="AdminRsv.jsp">예약관리</a></li>
		<li><a href="AdminRsvHist.jsp">예약내역</a></li>
		<li><a href="SearchMember.do?option=all">회원관리</a></li>
		<li><a href="SelectConf.do">회의실관리</a></li>
		<li class="active"><a href="AdminSetting.jsp">설정</a></li>
	</ul>

	<br>
	<br>

	<center>
		<div class="container">
			
			<form method="post" name="myForm" action="ChangePassword.do">
			<div class="form-inline" style="margin-top:30px;">
				기존 비밀번호 <input name="oldpw" id="oldpw" class="form-control">  
				새 비밀번호 <input name="newpw" class="form-control" >
				<button type="submit" class="btn btn-info" style="margin-left:20px;"> 변경하기</button>
			</div>
			</form>
			<br>${message}
		</div>
	</center>

</body>
</html>