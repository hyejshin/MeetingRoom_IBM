<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.AdminDTO"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자페이지</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<!-- temporary -->
	<link rel="stylesheet" href="css/dialog.css">
	<script src="js/dialog.js"></script>
	<link rel="shortcut icon" href="/favicon.ico" />

	<style>
body {
	background: url(image/10.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}
</style>

	<script> 

function check_onclick() {
   theForm = document.registerForm;
      if(theForm.name.value =="" || theForm.id.value=="" || theForm.passwd.value=="" )
      {
         if(theForm.name.value =="" ){
            alert("프로젝트 이름을 입력하세요."); 
            theForm.name.focus(); 
            return false ; 
         }
         
         else if(theForm.id.value =="" ){
            alert("관리자 이름을 입력하세요."); 
            theForm.id.focus();
            return false ; 
         }
         
         else if (theForm.passwd.value =="" ){
            alert("관리자 비밀번호를 입력하세요."); 
            theForm.passwd.focus();
            return false ; 
         }
      }
      
      else{
         document.registerForm.submit();
      }
};
</script>
</head>
<body>

	<!-- navigation bar -->
	<%@ include file="header.jsp"%>


	<br>
	<br>

	<div id="register" style="margin-bottom: 40px;">
		<form method="post" name="registerForm" action="InsertProject.do">
			<div class="form-inline">
				<table>
					<td><label for="name">프로젝트명: </label> <input type="text"
						class="form-control" name="name" placeholder="사이트명"></td>

					<td><label for="id">Admin ID: </label> <input type="text"
						class="form-control" name="id" placeholder="관리자 아이디"></td>

					<td><label for="pw">Admin PW: </label> <input type="text"
						class="form-control" name="passwd" placeholder="관리자 비밀번호">
					</td>

					<td>
						<button type=button class="btn btn-primary"
							onclick="check_onclick()" style="margin-left:15px;">등록</button>
					</td>

				</table>
			</div>
		</form>
	</div>


	<center>
	<table class="table table-hover" style="margin-top:30px; width:600px;">
		<tr>
			<th>프로젝트 이름</th>
			<th>프로젝트 아이디</th>
			<th></th>
		</tr>

		<%
			ArrayList<AdminDTO> dtos = (ArrayList) request.getAttribute("list");
			if (dtos != null) {
				for (int i = 0; i < dtos.size(); i++) {
					AdminDTO dto = dtos.get(i);
		%>

		<tr>
			<td><%=dto.getAdmin_Proj()%></td>
			<td><%=dto.getAdmin_Id()%></td>
			<td><a href="DeleteProject.do?seq=<%=dto.getAdmin_Seq()%>">삭제</a></td>
			<!--  위 함수 인자 설명 첫번째: Confirm창 문구, 두번째: 삭제function으로갈지op, 세번째: 삭제할index -->
			<!-- onclick="Confirm.render('프로젝트를 정말 삭제하시겠습니까?','delete_project', dto.seq)" -->
		</tr>
		<%
         }}
      %>

	</table>
	</center>
	
</body>


</html>