<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

.navbar-header {
	float: left;
	padding: 1px;
	text-align: center;
	width: 100%;
	margin-bottom:1px; 
}

.navbar-brand {
	float: none;
}

#myNavbar {
	float: right;
	padding: 1px;
	text-align: center;
	width: 100%;
	margin-top:1px; 
	padding-top:1%; 
	padding-left:60%; 
}

.navbar .navbar-collapse {
	float: center;
	padding: 1px;
	text-align: center;
	width: 100%;
}

.navbar-inverse {
	background-color: rgba(255, 255, 255, 1);
	border: 0px solid #000000;
}

.img-responsive2 {
	display: inline-block;
	max-width: 100%;
	height: 2%;
	margin-bottom: 3%;
}

.img-responsive3 {
	display: inline-block;
	width: 100%;
	max-width: 100%;
	height: 1px;
	margin-top: 1%;
}

.font-style{
	font-family: 'Nanum Pen Script';
	font-size: 25px;
}
</style>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		
		<div class="navbar-header">			
			<img class="img-responsive2" src="image/colorbar-01.png" title="top">
			<div class="col-md-12 col-sm-12 col-xs-12">
			<a class="navbar-brand" href="#"
			style="margin-top:1px;color:#000; font-family:'Nanum Pen Script', serif;font-size:48px">
			   IBM 회의실 예약 시스템</a>
			</div>
		</div>
		
		<div id="myNavbar" >
			<div class="col-md-12 col-sm-12 col-xs-12">
			<ul class="nav navbar-nav inline">
				<li><a href="home.do" class="font-style" style="color:#000;">예약/현황</a></li>
				<li><a href="Search.jsp" class="font-style" style="color:#000;">검색</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%if(session.getAttribute("project").equals("master")){ %>
				<li><a href="SelectProject.do" class="font-style" style="color:#000;"><span
						class="glyphicon glyphicon-user"></span>관리자</a></li>
				<%}else if(session.getAttribute("admin").equals("yes")){ %>
				<li><a href="AdminRsv.jsp" class="font-style" style="color:#000;"><span
						class="glyphicon glyphicon-user"></span>관리자</a></li>
				<%}%>
				<%if(session.getAttribute("admin").equals("no")){ %>
				<li><a href="AdminLogin.jsp" class="font-style" style="color:#000;"><span
						class="glyphicon glyphicon-log-in"></span> 관리자Login</a></li>
				<%}else{ %>
				<li><a href="AdminLogout.do" class="font-style" style="color:#000;"><span
						class="glyphicon glyphicon-log-out"></span> Log out</a></li>
				<%} %>
			</ul>
		</div></div>
		
	</div>
	</nav>