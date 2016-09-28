<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dao.AdminDAO"%>
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
	function pwValidationCheck() {
		theForm = document.pwForm;
		if (theForm.oldpw.value == "") {
			alert("기존 비밀번호를 입력하세요.");
			theForm.oldpw.focus();
			return false;
		}
		if (theForm.newpw.value == "") {
			alert("새로운 비밀번호를 입력하세요.");
			theForm.newpw.focus();
			return false;
		}
		document.pwForm.submit();
	};
	
	function monthValidationCheck() {
		theForm = document.monthForm;
		
		if (theForm.newmonth.value == "") {
			alert("새로운 예약가능한 개월 수를 입력하세요.");
			theForm.newmonth.focus();
			return false;
		}

		if (!parseInt(theForm.newmonth.value)){
			alert("0 보다 큰 정수를 입력하세요.");
			theForm.newmonth.focus();
			return false;
		}
		if (parseInt(theForm.newmonth.value) < 1){
			alert("0 보다 큰 정수를 입력하세요.");
			theForm.newmonth.focus();
			return false;
		}
		if (parseInt(theForm.newmonth.value) > 12){
			alert("12보다 작은 수를 입력하세요.");
			theForm.newmonth.focus();
			return false;
		}
		document.monthForm.submit();
	};
</script>

<style>
#admintitle {
	font-family: 'Jeju Gothic', serif;
	margin-left: 10%;
}

#subtitle{
	font-size: 130%;
	font-family: 'Nanum Pen Script', serif;
	margin-left:11%;
	margin-top:1%;"
}
.nav-pills {
	font-family: 'Jeju Gothic', serif;
	font-size: 130%;
	margin-left: 10%;
	margin-top:3%
}

#well-1 {
	border-radius: 10px;
	background:rgba(0, 90, 158, 0.2);
	margin-left:8%;
	margin-right:auto;
	margin-top:1%; 
	margin-bottom:5%;
}

#well-2 {
	border-radius: 10px;
	background:rgba(0, 90, 158, 0.2);
	margin-left:8%;
	margin-right:auto;
	margin-top:1%; 
	margin-bottom:5%;
}
</style>

</head>
<body>

	<!-- navigation bar -->
	<%@ include file="header.jsp"%>	
	<h3>&nbsp;&nbsp;${project} 관리자 페이지</h3>


	<ul class="nav nav-pills">
		<li><a href="AdminRsv.jsp">예약관리</a></li>
		<li><a href="AdminRsvHist.jsp">예약내역</a></li>
		<li><a href="SearchMember.do?option=all">회원관리</a></li>
		<li><a href="SelectConf.do">회의실관리</a></li>
		<li class="active"><a href="AdminSetting.do">설정</a></li>
	</ul>

	<br>
	
	<center>
      <div class="container">

         <div style="margin-top: 1%; padding: 3%">
            <div class="well well-sm col-md-11 col-sm-11 col-xs-12 col-centered"
               style="border-radius: 10px;">
               <form method="post" name="pwForm" action="ChangePassword.do">
                  <div class="form-inline" style="margin-top: 30px;">
                     기존 비밀번호 <input name="oldpw" id="oldpw" type="password"
                        class="form-control"> 
                        
                        새 비밀번호 <input name="newpw" type="password" class="form-control">
                        <button type="button" class="btn btn-info"
                        		onclick="pwValidationCheck();" style="margin-left: 20px;">변경하기</button>
                  </div>
               </form>
					<%
						String message = (String) request.getAttribute("message");
						if (message != null && message == "sucess") {
							%><%="비밀번호가 성공적으로 변경되었습니다."%><%
						} else if (message != null && message == "fail") {
							%><%="비밀번호가 일치하지 않습니다."%><%
						}
					%>				
					<br>
            </div>
         </div>
      </div>
   </center>
	
	<!-- 예약 가능한 달 수 바꾸기   -->
	<center>
      <div class="container">

         <div style="margin-top: 1%; padding: 3%">
            <div class="well well-sm col-md-11 col-sm-11 col-xs-12 col-centered"
               style="border-radius: 10px;">
               <form method="post" name="monthForm" action="ChangeMonth.do">
               <div class="form-inline" style="margin-top: 30px;">
                  기존 예약 가능한 달 수: ${pmonth}달&nbsp;&nbsp;
                  
                  새 예약 가능한 달 수 
                  <input name="newmonth" id="newmonth" class="form-control">
                  
                  <button type="button" class="btn btn-info" style="margin-left: 20px;" 
                  		onClick ="monthValidationCheck()">변경하기</button>
               </div>
            </form>
            <br>
            </div>
         </div>
      </div>
   </center>

	<!-- footer -->
   <%@ include file="footer.jsp"%>  
</body>
</html>