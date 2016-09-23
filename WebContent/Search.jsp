<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.RsvDTO"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회의실 예약 검색</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
   src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
body {
   background: url(image/10.jpg) center fixed;
   -webkit-background-size: cover;
   -moz-background-size: cover;
   -o-background-size: cover;
   background-size: cover;
   font-family: 'Noto Sans KR', sans-serif;
}

input {
   /*사용자가 입력하는 내용은 나눔명조체*/
   font-family: 'Noto Sans KR', sans-serif !important;
}

textarea:focus,input:focus,input[type]:focus,.uneditable-input:focus {
   border-color: rgba(102, 102, 102, 0.7);
   box-shadow: 0 1px 1px rgba(51, 51, 51, 0.3) inset, 0 0 8px
      rgba(51, 51, 51, 0.9);
   outline: 0 none;
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
   padding: 9px 12px;
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
   font-size: 14px;
   font-family: 'Noto Sans KR', sans-serif;
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
			<div class="container">	
			<div class="form-inline">
				<select class="form-control" id="option" name="option">
					<option value="rsv_mem_nm">예약자</option>
				</select>

				<input type="text" class="form-control" size="70" name="context">

				<button type="submit" class="button btn-info"
					tittle="search by name">
					검색 <span class='glyphicon glyphicon-search'></span>
				</button>
			</div>
			</div>
		</form>
	</center>
	
	<%
		ArrayList<RsvDTO> dtos = (ArrayList) request.getAttribute("list");
	%>

   <div style="padding: 3%">
      <div class="well well-sm col-md-9 col-sm-8 col-xs-12 col-centered"
         style="border-radius: 10px;">
         <table class="table">
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
   </div>
</body>
</html>