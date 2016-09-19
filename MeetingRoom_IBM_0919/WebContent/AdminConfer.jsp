<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.ConfDTO"%>
<%@ page import="java.util.ArrayList"%>
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

<script>
	function fillConferInfo(idx) {
		
		$.ajax({
	        type: "post",
	        url : "UpdateViewConfer.do",
	        dataType : 'json',
	        data: {
	        	seq : idx
	        },
	       
	        success : function(data) {
	        	$('#stat').empty();
	        	$('#name').empty();
	        	
	        	
      			for(var i=0; i<data.result.length; i++) {
      				$('input[name="seq"]').val(data.result[i].seq);
      				$('input[name="name"]').val(data.result[i].name);
      				$('input[name="stat"]').val(data.result[i].stat);
      			
      				
      			  }
	        },
	        error : function() {
	        	console.log("error");
	        }
 	});
}
		
</script>

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
		<li class="active"><a href="SearchConfer.do">회의실관리</a></li>
		<li><a href="SearchMember.do?option=all">회원관리</a></li>
	</ul>
	<br>
	<br>

	<div class="container">
	
		<!-- 회의실등록 -->
		<div style="margin-bottom:20px;">
		<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#register"
		onclick="fillSite('#siteR');"> 회의실등록▽<span class="glyphicon glyphicon-user-add"></span></button></div>
		
		<div id="register" class="collapse" style="margin-bottom: 40px;">
			<form method="post" name="registerForm" action="InsertConf.do">
			<input type="hidden" name="page" value="AdminMember.jsp">
				<div class="form-inline">
					<label for="name">회의실이름:</label> <input type="text"
						class="form-control" name="name" placeholder="Input ConferenceRoom Name">

					<label for="stat">회의실상태:</label> <input type="text"
						class="form-control" name="stat" placeholder="Input ConferenceRoom Status">
								
					<button type="submit" class="btn btn-primary" style="margin-left:20px;">등록</button>
				</div>
			</form>
		</div>

		<!-- 회의실 정보 수정 -->
		<div id="update" class="collapse" style="margin-bottom: 40px;">
			<h3>회의실 정보 수정</h3><br>
			<form method="post" name="modifyForm" action="UpdateConfer.do">
			<input type="hidden" name="seq" id="seq">
				<div class="form-inline">
				
					<label for="seq"></label> <input type="hidden"
						class="form-control" name="seq" id="seq">
					
					<label for="name">회의실이름:</label> <input type="text"
						class="form-control" name="name" id="name">

					<label for="stat">회의실상태:</label> <input type="text"
						class="form-control" name="stat" id="stat">

					
					
					<button type="submit" class="btn btn-primary" style="margin-left:20px;">수정</button>
					<a href="#" class="btn btn-default" data-toggle="collapse" data-target="#update"> 취소</a>
				</div>
			</form>
		</div>
		
		<%
			ArrayList<ConfDTO> dtos = (ArrayList) request.getAttribute("list");
		%>
		<table class="table table-hover" style="margin-top: 30px;">
			<tr>
				<td>회의실SEQ</td>
				<td>회의실이름</td>
				<td>회의실상태</td>
				
				<td></td>
			</tr>
			<%
				if (dtos != null) {
					for (int i = 0; i < dtos.size(); i++) {
						ConfDTO dto = dtos.get(i);
			%>
			<tr>
				<td><%=dto.getConfrn_Seq()%></td>
				<td><%=dto.getConfrn_Nm()%></td>
				<td><%=dto.getConfrn_Stat()%></td>
				
				<td><a href="#" data-toggle="collapse" data-target="#update" 
					onclick="fillConferInfo(<%=dto.getConfrn_Seq()%>);">수정</a>|
					<a href="DeleteConf.do?seq=<%= dto.getConfrn_Seq() %>">삭제</a></td>
			</tr>
			<%}}%>
		</table>
</body>
</html>