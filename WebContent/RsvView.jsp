<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회의실 예약 시스템</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
	button{
		margin-top: 20px;
		margin-left: 18px;
		width: 100px;
	}
</style>

<script>
	function changeConf(idx, name){
		var id = "";
		for(var i=1; i<=4; i++){
			id = "#conf" + i;
			$(id).removeClass('active');
		}
		id = "#conf" + String(idx);
		$(id).addClass('active');
		document.myForm.confer_nm.value = name;
	}
</script>
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
				<li><a href="AdminRsv.jsp"><span class="glyphicon glyphicon-user"></span>
						관리자</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
						Log out</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<form method="post" name="myForm">
	<div class="container">
		<!-- 사이트 선택 -->
		<div class="row" style="padding-bottom:25px;">
			<div class="col-md-6 col-sm-9 col-xs-12">
				<select class="form-control" id="site" name="site">
					<option value="">선택하세요</option>
					<option value="아모레퍼시픽">아모레</option>
					<option value="코웨이">코웨이</option>
					<option value="고려해운">고려해운</option>
				</select>
			</div>
			<input type="text" id="confer_nm">
		</div>

		<div class="row">

			<!-- 회의실탭 & 달력 -->
			<div class="col-md-6 col-sm-9 col-xs-12">
				<ul class="nav nav-pills">
				  <li id="conf1"><a href="#" onClick="changeConf(1, '회의실1');">회의실1</a></li>
				  <li id="conf2"><a href="#" onClick="changeConf(2, '회의실2');">회의실2</a></li>
				  <li id="conf3"><a href="#" onClick="changeConf(3, '회의실3');">회의실3</a></li>
				  <li id="conf4"><a href="#" onClick="changeConf(4, '회의실4');">회의실4</a></li>
				</ul>
				
				<%@ include file="calendar/calendar.jsp" %>
			</div>
			
			<!-- 회의실tab & 예약현황 -->
			<div class="col-md-6 col-sm-9 col-xs-12">
			
			<table class="table table-condensed">
				<tr><td>시간</td><td>회의제목</td><td>예약자</td><td></td><td></td></tr>
				<tr><td>09:00 - 09:30</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>09:30 - 10:00</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>10:00 - 10:30</td><td>daily meeting</td><td>신혜정</td><td>수정</td><td>취소</td></tr>
				<tr><td>10:30 - 11:00</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>11:00 - 11:30</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>11:30 - 12:00</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>13:00 - 13:30</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>13:30 - 14:00</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>14:00 - 14:30</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>14:30 - 15:00</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>15:00 - 15:30</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>15:30 - 16:00</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>16:00 - 16:30</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>16:30 - 17:00</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>17:00 - 17:30</td><td></td><td></td><td></td><td></td></tr>
				<tr><td>17:30 - 18:00</td><td></td><td></td><td></td><td></td></tr>
			</table>
			<!--
			<table class="table">
				<tr><td>시간</td><td>회의제목</td><td>예약자</td><td></td><td>
				<tr><td>09:00 - 10:00</td><td></td><td></td><td></td><td>
				<tr><td>10:00 - 11:00</td><td>daily meting</td><td>신혜정</td><td>수정 | 취소</td></tr>
				<tr><td>11:00 - 12:00</td><td></td><td></td><td></td><td>
				<tr><td>13:00 - 14:00</td><td></td><td></td><td></td><td>
				<tr><td>14:00 - 15:00</td><td></td><td></td><td></td><td>
				<tr><td>15:00 - 16:00</td><td></td><td></td><td></td><td>
				<tr><td>16:00 - 17:00</td><td></td><td></td><td></td><td>
				<tr><td>17:00 - 18:00</td><td></td><td></td><td></td><td>
			</table>
			</div>
			  -->
		</div>

		<!-- 입력창 -->
		<div class="row">
			<div class="col-md-4">
				이름<input type="text" class="form-control" id="name"> 
			</div>
			<div class="col-md-4">
				전화번호<input type="text" class="form-control" id="phone">
			</div>
			<div class="col-md-4">
				이메일<input type="text" class="form-control" id="email">
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				날짜<input type="text" class="form-control" id="date"> 
			</div>
			<div class="col-md-4">
				시작시간<input type="text" class="form-control" id="start_time"> 
			</div>
			<div class="col-md-4">
				끝시간<input type="text" class="form-control" id="end_time">
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				회의제목<input type="text" class="form-control" id="title"> 
			</div>
			<div class="col-md-4">
				비밀번호<input type="text" class="form-control" id="password">
			</div>
			<button type="button" class="btn btn-primary">예약</button>
		</div>

	</div>
	
	</form> 

<div style="margin-top:30px"></div>
</body>
</html>