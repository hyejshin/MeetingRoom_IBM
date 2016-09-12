<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회의실 예약 검색</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

	<!--style="background-color: #0040FF;"-->
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"
				style="color: white; font-weight: bold">IBM 회의실 예약 시스템</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="RsvView.jsp">예약/현황</a></li>
				<li><a href="Search.jsp">검색</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="AdminRsv.jsp"><span
						class="glyphicon glyphicon-user"></span> 관리자</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
						Log out</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<form method="post" name="myForm">
		<div class="container">
		
			<div class="col-md-1 col-sm-1 col-xs-0">
			</div>
			
			<div class="col-md-2 col-sm-2 col-xs-3">
			<select class="form-control" id="sel1" name="site">
				<option value="all">전체</option>
				<option value="site">프로젝트</option>
				<option value="title">회의제목</option>
				<option value="name">예약자</option>
			</select>
			</div>
			
			<div class="col-md-6 col-sm-6 col-xs-7">
				<input type="text" class="form-control" id="context">
			</div>
			
			<div class="col-md-2 col-sm-2 col-xs-2">
			<button type="button" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span> 검색
			</button>
			</div>
		</div>

	</form>

	<table class="table" style="margin-top:30px;">
		<tr><td>제목</td><td>회의실</td><td>날짜</td><td>시간</td><td>예약자</td><td>전화번호</td></tr>
	</table>
</body>
</html>