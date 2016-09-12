<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.MemberDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자페이지</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
	function fill(idx){
		$.ajax({
	        type: "post",
	        url : "UpdateViewMember.do",
	        dataType : 'json',
	        data: {
	        	seq : idx
	        },
	       
	        success : function(data) {
	        	$('#seq').empty();
	        	$('#name').empty();
	        	$('#phone').empty();
	        	$('#email').empty();
	        	
      			for(var i=0; i<data.result.length; i++) {
      				$('input[name="seq"]').val(idx);
      				$('input[name="name"]').val(data.result[i].name);
      				$('input[name="phone"]').val(data.result[i].phone);
      				$('input[name="email"]').val(data.result[i].email);
      				$('input[name="site"]').val(data.result[i].site);
      			  }
	        },
	        error : function() {
	        	console.log("error");
	        }
 	});
	}
</script>
</head>
<body>

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
				<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<ul class="nav nav-pills">
		<li><a href="AdminRsv.jsp">예약관리</a></li>
		<li><a href="AdminRsvHist.jsp">예약내역</a></li>
		<li><a href="AdminProject.jsp">프로젝트관리</a></li>
		<li class="active"><a href="AdminMember.jsp">회원관리</a></li>
	</ul>
	<br>
	<br>


	<div class="container">
	
		<!-- 회원등록 -->
		<div style="margin-bottom:20px;">
		<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#register">
		회원등록▽<span class="glyphicon glyphicon-user-add"></span></button></div>
		
		<div id="register" class="collapse" style="margin-bottom: 40px;">
			<form method="post" name="registerForm" action="InsertMember.do">
			<input type="hidden" name="page" value="AdminMember.jsp">
				<div class="form-inline">
					<label for="name">이름:</label> <input type="text"
						class="form-control" name="name" placeholder="Enter name">

					<label for="phone">폰번호:</label> <input type="text"
						class="form-control" name="phone" placeholder="Enter phone number">

					<label for="email">이메일:</label> <input type="email"
						class="form-control" name="email" placeholder="Enter email">

					<label for="site">소속 회사:</label> <select class="form-control" name="site">
						<option value="">선택하세요</option>
						<option value="아모레퍼시픽">아모레</option>
						<option value="코웨이">코웨이</option>
						<option value="고려해운">고려해운</option>
					</select>
					
					<button type="submit" class="btn btn-primary" style="margin-left:20px;">등록</button>
				</div>
			</form>
		</div>

		<!-- 회원 정보 수정 -->
		<div id="update" class="collapse" style="margin-bottom: 40px;">
			<h3>회원 정보 수정</h3><br>
			<form method="post" name="modifyForm" action="UpdateMember.do">
			<input type="hidden" name="seq" id="seq">
				<div class="form-inline">
					<label for="name">이름:</label> <input type="text"
						class="form-control" name="name" id="name">

					<label for="phone">폰번호:</label> <input type="text"
						class="form-control" name="phone" id="phone">

					<label for="email">이메일:</label> <input type="email"
						class="form-control" name="email" id="email">

					<label for="site">소속 회사:</label> <select class="form-control"
						id="site" name="site">
						<option value="">선택하세요</option>
						<option value="아모레퍼시픽">아모레</option>
						<option value="코웨이">코웨이</option>
						<option value="고려해운">고려해운</option>
					</select>
					
					<button type="submit" class="btn btn-primary" style="margin-left:20px;">수정</button>
					<a href="#" class="btn btn-default" data-toggle="collapse" data-target="#update"> 취소</a>
				</div>
			</form>
		</div>
		
		<!-- 회원 검색 창 -->
		<form method="post" name="myForm" action="SearchMember.do">
			<div class="row">
				<div class="col-md-1 col-sm-1 col-xs-0"></div>

				<div class="col-md-2 col-sm-2 col-xs-3">
					<select class="form-control" id="option" name="option">
						<option value="all">전체</option>
						<option value="mem_site">프로젝트</option>
						<option value="mem_nm">이름</option>
					</select>
				</div>

				<div class="col-md-6 col-sm-6 col-xs-7">
					<input type="text" class="form-control" name="context">
				</div>

				<div class="col-md-2 col-sm-2 col-xs-2">
					<button type="submit" class="btn btn-info">
						검색 <span class='glyphicon glyphicon-search'></span>
					</button>
				</div>
			</div>

		</form>


		<%
			ArrayList<MemberDTO> dtos = (ArrayList) request.getAttribute("list");
		%>
		<table class="table table-hover" style="margin-top: 30px;">
			<tr>
				<td>이름</td>
				<td>폰번호</td>
				<td>이메일</td>
				<td>프로젝트</td>
				<td></td>
			</tr>
			<%
				if (dtos != null) {
					for (int i = 0; i < dtos.size(); i++) {
						MemberDTO dto = dtos.get(i);
			%>
			<tr>
				<td><%=dto.getMem_Nm()%></td>
				<td><%=dto.getMem_Pn()%></td>
				<td><%=dto.getMem_Em()%></td>
				<td><%=dto.getMem_Site()%></td>
				<td><a href="#" data-toggle="collapse" data-target="#update" onclick="fill(<%=dto.getMem_Seq()%>);">수정</a>|
				<a href="DeleteMember.do?seq=<%=dto.getMem_Seq()%>">삭제</a></td>
			</tr>
			<%}}%>
		</table>
</body>
</html>