<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.ConfDTO"%>
<%@ page import="com.ibm.cof.dto.ProjectDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회의실 예약 시스템</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="js/ajax_auto.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<script src="js/RsvView.js"></script>
<link rel="stylesheet" type="text/css" href="css/RsvView.css">

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
				<li><a href="home.do">예약/현황</a></li>
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

	<form method="post" name="myForm" action="Reservation.do">
	<div class="container">
		<!-- 사이트 선택 -->
		<div class="row" style="padding-bottom:25px;">
			<div class="col-md-6 col-sm-9 col-xs-12">
				<select class="form-control" id="site" name="site" onchange="displayConf(this.value);">
				<option value="">선택하세요</option>
				<%ArrayList<ProjectDTO> dtos = (ArrayList) request.getAttribute("proj");
					if (dtos != null) {
						for (int i = 0; i < dtos.size(); i++) {
							ProjectDTO dto = dtos.get(i);%>
					<option value="<%=dto.getProj_Nm()%>"><%=dto.getProj_Nm()%></option>
				<%}}%>
				</select>
			</div>
		</div>

			<div class="row">

				<!-- 달력 -->
				<div class="col-md-5 col-sm-6 col-xs-12" style="margin-top: 20px;">
					<%@ include file="calendar/calendar.jsp"%>
				</div>

				<!-- 회의실tab & 예약현황 -->
				<div class="col-md-7 col-sm-6 col-xs-12">

					<!-- 회의실탭 -->
					<div id="conference"></div>

					<div id="schedule">
						<div id="timeDiv"></div>
						<div id="meetings"></div>
					</div>
				</div>


				<!-- 회의실 예약 입력창 -->
				<div class="search-container">

					<div class="row">
						<div class="col-md-4">
							전화번호<input type="text" class="form-control" id="phone" name="phone">
						</div>
						<div class="col-md-4">
							이름<input type="text" class="form-control" id="name" name="name">
						</div>
						<div class="col-md-4">
							이메일<input type="text" class="form-control" id="email" name="email">
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							날짜<input type="text" class="form-control" id="date" name="date">
						</div>
						<div class="col-md-4">
							시작시간<input type="text" class="form-control" id="start_time" name="start_time">
						</div>
						<div class="col-md-4">
							끝시간<input type="text" class="form-control" id="end_time" name="end_time">
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							회의실<input type="text" class="form-control" id="confer_nm" name="confer_nm">
						</div>
						<div class="col-md-4">
							회의제목<input type="text" class="form-control" id="title" name="title">
						</div>
						<div class="col-md-4">
							비밀번호<input type="password" class="form-control" id="del_pw"	name="del_pw">
						</div>

						<!-- 예약 버튼 -->
						<div id="register">
							<button type="submit" class="btn btn-primary">예약</button>
						</div>

						<!-- 수정 및 삭제 -->
						<div id="registerInfo">
							<button type="button" class="btn btn-primary">수정</button>
							<button type="button" class="btn btn-primary">삭제</button>
						</div>
					</div>

				</div>
			</div>



		</div>
	
	</form> 

<div style="margin-top:30px"></div>
</body>
</html>