<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<script>
	
	function fillConferInfo(idx){
		$.ajax({
	        type: "post",
	        url : "UpdateViewConf.do",
	        dataType : 'json',
	        data: {
	        	seq : idx
	        },
	       
	        success : function(data) {
	        	$('#seq').empty();
	        	$('#name').empty();

      			for(var i=0; i<data.result.length; i++) {
      				$('input[name="seq"]').val(idx);
      				$('input[name="name"]').val(data.result[i].name);
      				$("#stat").val(data.result[i].stat).attr("selected", "selected");
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

	<!-- navigation bar -->
	<%@ include file="header.jsp"%>	
	<h3>&nbsp;&nbsp;${project} 관리자 페이지</h3>
	
	<ul class="nav nav-pills">
	<li><a href="AdminRsv.jsp">예약관리</a></li>
	<li><a href="AdminRsvHist.jsp">예약내역</a></li>
	<li><a href="SearchMember.do?option=all">회원관리</a></li>
	<li class="active"><a href="SelectConf.do">회의실관리</a></li>
	<li><a href="AdminSetting.jsp">설정</a></li>
</ul>	
	
	<div class="container">
	
		<!-- 회의실등록 -->
		<div style="margin-bottom:20px; margin-top:20px;">
		<button type="button" class="btn btn-default" data-toggle="collapse" 
		data-target="#register"> 회의실등록▽<span class="glyphicon glyphicon-user-add"></span></button></div>
		
		<div id="register" class="collapse" style="margin-bottom: 40px; align:center;">
			<form method="post" name="registerForm" action="InsertConf.do">
			<input type="hidden" name="page" value="AdminMember.jsp">
				<div class="form-inline">
					<label for="name">회의실이름:</label> <input type="text"
						class="form-control" name="name" placeholder="Input ConferenceRoom Name">

					<label for="stat">회의실상태:</label>
					<select class="form-control" name="stat">
						<option value="Y">사용가능</option>
						<option value="N">사용불가</option>
					</select>
								
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
					<label for="name">회의실이름:</label> <input type="text"
						class="form-control" name="name" id="name">

					<label for="stat">회의실상태:</label>
					<select class="form-control" name="stat" id="stat">
						<option value="Y">사용가능</option>
						<option value="N">사용불가</option>
					</select>

					<button type="submit" class="btn btn-primary" style="margin-left:20px;">수정</button>
					<a href="#" class="btn btn-default" data-toggle="collapse" data-target="#update"> 취소</a>
				</div>
			</form>
		</div>
		
		<%
			ArrayList<ConfDTO> dtos = (ArrayList) request.getAttribute("list");
		%>
		<table class="table table-hover" style="margin-top:30px; text-align:center;">
			<tr>
				<td>회의실번호</td>
				<td>회의실이름</td>
				<td>회의실상태</td>
				<td></td>
			</tr>
			<%	int no = 1;
				String state;
				if (dtos != null) {
					for (int i = 0; i < dtos.size(); i++) {
						ConfDTO dto = dtos.get(i);
						
						if(dto.getConfrn_Stat().equals("Y")){
							state = "사용가능";
						}else{
							state = "사용불가";
						}
			%>
			<tr>
				<td><%=no%></td>
				<td><%=dto.getConfrn_Nm()%></td>
				<td><%=state%></td>
				
				<td><a href="#" data-toggle="collapse" data-target="#update" 
					onclick="fillConferInfo(<%=dto.getConfrn_Seq()%>);">수정</a>|
					<a href="DeleteConf.do?seq=<%=dto.getConfrn_Seq()%>">삭제</a></td>
			</tr>
			<%no++;
			}}%>
		</table>
</body>
</html>