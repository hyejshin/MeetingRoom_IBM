<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.RsvDTO"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회의실 예약 검색</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
@import url(http://fonts.googleapis.com/earlyaccess/kopubbatang.css);/*font*/
@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);
body {
   font-family: 'Noto Sans KR', sans-serif;
}

input {
   /*사용자가 입력하는 내용은 나눔명조체*/
   font-family: 'Noto Sans KR', sans-serif !important;
}

textarea:focus,input:focus,input[type]:focus,.uneditable-input:focus {
   border-color: rgbargba(0, 29, 89, 0.34);
   box-shadow: 0 1px 1px rgba(0, 29, 89, 0.34) inset, 0 0 8px
      rgba(0, 29, 89, 0.34);
   outline: 0 none;
}
.col-centered {
   float: none;
   margin-right: auto;
   margin-left: auto;
}
</style>
</head>

<body>

   <!-- navigation bar -->
   <%@ include file="header.jsp"%>

	<center>
		<form method="post" name="myForm" action="SearchRsv.do">
			<div class="row col-sm-10 col-md-7 col-centered">
				<div class="col-md-2 col-sm-3 col-xs-3">
					<select class="form-control" id="option" name="option">
						<option value="all">전체</option>
						<option value="rsv_mem_nm">예약자</option>
					</select>
				</div>

				<div class="col-sm-6 col-md-6 col-sm-5 col-xs-6">
					<input type="text" class="form-control" name="context">
				</div>

				<div class="col-sm-2 col-md-2 col-sm-2 col-xs-2">
					<button type="submit" class="btn btn-info">
						검색 <span class='glyphicon glyphicon-search'></span>
					</button>
				</div>
			</div>
		</form>
	</center>
	<br><br>
	<%
		ArrayList<RsvDTO> dtos = (ArrayList) request.getAttribute("list");
	%>

   <div class="col-md-10 col-sm-8 col-xs-12 col-centered">
         <table class="table table-hover" align="center">
            <thead>
               <tr>
                  <td><b>프로젝트</b></td>
                  <td><b>날짜</b></td>
                  <td><b>시간</b></td>
                  <td><b>예약자</b></td>
                  <td><b>전화번호</b></td>
                  <td><b>회의실</b></td>
                  <td><b>제목</b></td>
               </tr>
            </thead>
            <tbody>
               <%
                  if(dtos != null){
                       for(int i=0; i<dtos.size(); i++){
                          RsvDTO dto = dtos.get(i);
                          String startTime = dto.getRsv_Start_Time();
                          String endTime = dto.getRsv_End_Time();
                          String time = startTime.substring(0,2) + ":" + startTime.substring(2,4) + "~";
                          time += endTime.substring(0,2) + ":" + endTime.substring(2,4);
               %>
               <tr>
                  <td><%=dto.getRsv_Site()%></td>
                  <td><%=dto.getRsv_Date()%></td>
                  <td><%=time%></td>
                  <td><%=dto.getRsv_Mem_Nm()%></td>
                  <td><%=dto.getRsv_Mem_Pn()%></td>
                  <td><%=dto.getRsv_Confer_Nm()%></td>
                  <td><%=dto.getRsv_Title()%></td>
               </tr>
               <%
                  }}
               %>
            </tbody>
         </table>
      </div>


</body>
</html>