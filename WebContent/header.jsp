<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
				<%if(session.getAttribute("project").equals("master")){ %>
				<li><a href="AdminProject.jsp"><span class="glyphicon glyphicon-user"></span>관리자</a></li>
				<%}else if(session.getAttribute("admin").equals("yes")){ %>
				<li><a href="AdminRsv.jsp"><span class="glyphicon glyphicon-user"></span>관리자</a></li>
				<%}%>
				<%if(session.getAttribute("admin").equals("no")){ %>
				<li><a href="AdminLogin.jsp"><span class="glyphicon glyphicon-log-in"></span>
						관리자Login</a></li>
				<%}else{ %>
				<li><a href="AdminLogout.do"><span class="glyphicon glyphicon-log-out"></span>
						Log out</a></li>
				<%} %>
			</ul>
		</div>
	</div>
	</nav>