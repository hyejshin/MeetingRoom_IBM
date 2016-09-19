<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자페이지</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<!-- navigation bar -->
	<%@ include file="header.jsp"%>	

	<ul class="nav nav-pills">
		<li class="active"><a href="AdminRsv.jsp">예약관리</a></li>
		<li><a href="AdminRsvHist.jsp">예약내역</a></li>
		<li><a href="AdminProject.jsp">프로젝트관리</a></li>
		<li><a href="SearchMember.do?option=all">회원관리</a></li>
	</ul>
	Admin reserve
</body>
</html>