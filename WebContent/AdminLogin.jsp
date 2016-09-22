<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"
	type="text/javascript"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/dialog.css">
<title>관리자 로그인</title>

<style>
@CHARSET "UTF-8";

* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	outline: none;
}

.form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	@
	include
	box-sizing(border-box);
	&:
	focus
	{
	z-index
	:
	2;
}

}
body {
	background: url(image/10.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

.login-form {
	margin-top: 60px;
}

form[role=login] {
	color: #5d5d5d;
	background: #f2f2f2;
	padding: 26px;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
}

form[role=login] img {
	display: block;
	margin: 0 auto;
	margin-bottom: 35px;
}

form[role=login] input,form[role=login] button {
	font-size: 18px;
	margin: 16px 0;
}

form[role=login]>div {
	text-align: center;
}

.form-links {
	text-align: center;
	margin-top: 1em;
	margin-bottom: 50px;
}

.form-links a {
	color: #fff;
}

.button {
	border: 0px solid #000000;
	background: #000000;
	background: -webkit-gradient(linear, left top, left bottom, from(#060e14),
		to(#000000));
	background: -webkit-linear-gradient(top, #060e14, #000000);
	background: -moz-linear-gradient(top, #060e14, #000000);
	background: -ms-linear-gradient(top, #060e14, #000000);
	background: -o-linear-gradient(top, #060e14, #000000);
	background-image: -ms-linear-gradient(top, #060e14 0%, #000000 100%);
	padding: 10px 20px;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0, inset
		rgba(255, 255, 255, 0.4) 0 1px 0;
	-moz-box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0, inset
		rgba(255, 255, 255, 0.4) 0 1px 0;
	box-shadow: rgba(255, 255, 255, 0.4) 0 1px 0, inset
		rgba(255, 255, 255, 0.4) 0 1px 0;
	text-shadow: #ffffff 0 1px 0;
	color: #eaeced;
	font-size: 15px;
	font-family: helvetica, serif;
	text-decoration: none;
	vertical-align: middle;
}

.button:hover {
	border: 0px solid #0a3c59;
	text-shadow: #dce7ed 0 1px 0;
	background: #403e40;
	background: -webkit-gradient(linear, left top, left bottom, from(#3a3b3b),
		to(#403e40));
	background: -webkit-linear-gradient(top, #3a3b3b, #403e40);
	background: -moz-linear-gradient(top, #3a3b3b, #403e40);
	background: -ms-linear-gradient(top, #3a3b3b, #403e40);
	background: -o-linear-gradient(top, #3a3b3b, #403e40);
	background-image: -ms-linear-gradient(top, #3a3b3b 0%, #403e40 100%);
	color: #fff;
}

.button:active {
	text-shadow: #667680 0 1px 0;
	border: 0px solid #000000;
	background: #151717;
	background: -webkit-gradient(linear, left top, left bottom, from(#1f1f1f),
		to(#403e40));
	background: -webkit-linear-gradient(top, #1f1f1f, #151717);
	background: -moz-linear-gradient(top, #1f1f1f, #151717);
	background: -ms-linear-gradient(top, #1f1f1f, #151717);
	background: -o-linear-gradient(top, #1f1f1f, #151717);
	background-image: -ms-linear-gradient(top, #1f1f1f 0%, #151717 100%);
	color: #fff;
}
</style>
<body>

	<!-- navigation bar -->
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
				<li><a href="#">예약/현황</a></li>
				<li><a href="#">검색</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			</ul>
		</div>
	</div>
	</nav>



	<div class="container">

		<div class="row" id="pwd-container">
			<div class="col-md-4"></div>

			<div class="col-md-4">
				<section class="login-form">
				<form method="post" action="AdminLogin.do" role="login">
					<img src="image/admin-picture.png" class="img-responsive" alt="" />

					<input type="text" name="id" id="id" required
						class="form-control input-lg" placeholder="ID" value="" /> <input
						type="password" name="pw" id="pw" class="form-control input-lg"
						id="password" placeholder="Password" required="" />


					<div class="pwstrength_viewport_progress"></div>


					<button class="button btn-block" type="submit" name="go"
						role="loginOK">Admin Login</button>

				</form>

				<div class="form-links">
					<a href="www.ibm.com">www.ibm.com</a>
				</div>
				</section>
			</div>

			<div class="col-md-4"></div>


		</div>

		<p>
			<a
				href="http://validator.w3.org/check?uri=http%3A%2F%2Fbootsnipp.com%2Fiframe%2FW00op"
				target="_blank"><small>HTML</small><sup>5</sup></a> <br> <br>
		</p>


	</div>
</body>
</html>