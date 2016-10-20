<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.HistoryDTO" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자페이지</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- date picker -->
<link rel="stylesheet" href="css/datepicker.css">
<script src="js/bootstrap-datepicker.js"></script>

<style>
#admintitle {
	font-family: 'Jeju Gothic', serif;
	margin-left: 10%;
}

#subtitle{
	font-size: 18px;
	font-family: 'Jeju Gothic', serif;
	margin-left:11%;
	margin-top:1%;"
}
.nav-pills {
	font-family: 'Jeju Gothic', serif;
	font-size: 130%;
	margin-left: 10%;
	margin-top:3%
}

</style>

<script>
	function dateCheckDelete(){
		if (document.deleteForm.delete_date.value == "") {
			alert("날짜를 선택하세요.");
			document.deleteForm.delete_date.focus();
			return false;
		}
		
		document.deleteForm.submit();
	}
	
	function dateCheckSearch(){
		theForm = document.myForm;
		
		if (theForm.start_date.value == "") {
			alert("시작날짜를 선택하세요.");
			theForm.start_date.focus();
			return false;
		} 
		else if (theForm.end_date.value == "") {
			alert("끝날짜를 선택하세요.");
			theForm.end_date.focus();
			return false;
		} 
		else if (theForm.end_date.value < theForm.start_date.value) {
			alert("시작날짜보다 끝날짜가 이릅니다.");
			theForm.end_date.focus();
			return false;
		}
		
		document.myForm.submit();
	}
</script>

</head>
<body>
	<!-- navigation bar -->
	<%@ include file="header.jsp"%>
	<h3 id="admintitle">&nbsp;&nbsp;${project} 관리자 페이지</h3>
	
	<ul class="nav nav-pills">
		<li><a href="AdminRsv.jsp">예약관리</a></li>
		<li class="active"><a href="AdminRsvHist.jsp">예약내역</a></li>
		<li><a href="SearchMember.do?option=all">On-Boarding</a></li>
		<li><a href="SearchBlock.do?option=all">Off-Boarding</a></li>
		<li><a href="SelectConf.do">회의실관리</a></li>
		<li><a href="AdminSetting.do">설정</a></li>
	</ul>
	
	<div id = "subtitle">
	특정 기간동안 회원들이 예약한 내용을 확인할 수 있는 페이지입니다.  
	</div>	
	

	<div class="container">		
		
	<!-- 예약현황 삭제 -->
		<div align="right" style="margin-bottom:20px;">
		<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#delete">
		예약현황삭제<span class="glyphicon glyphicon-user-add"></span></button></div>
		
		<div id="delete" class="collapse" style="margin-bottom:40px;" align="right">
			<form method="post" name="deleteForm" action="DeleteRsvRecord.do">
			<input type="hidden" name="page" value="AdminMember.jsp">
				<div class="form-inline">
					<input type="text" name="delete_date" id="delete_date" class="form-control">
					이전 날짜의 예약현황을 삭제합니다.
					<script>
						$('#delete_date').datepicker({
							dateFormat : 'yyyy-mm-dd'
						});
						$('#delete_date').datepicker('hide');
					</script>
					<button type="button" onclick="dateCheckDelete();" class="btn btn-warning" style="margin-left:10px;">삭제</button>
				</div>
			</form>
		</div>
		<br>
		
	<!-- 예약내역 검색 -->
		<form method="post" name="myForm" action="SearchHistory.do">
		<div class="form-inline">
			시작 날짜<input type="text" name="start_date" id="start_date" class="form-control">
			끝 날짜<input type="text" name="end_date" id="end_date" class="form-control">
			<script>
				$('#start_date').datepicker({
					dateFormat : 'yyyy-mm-dd'
				});
				$('#start_date').datepicker('hide');
				
				$('#end_date').datepicker({
					dateFormat : 'yyyy-mm-dd',
				});
				$('#end_date').datepicker('hide');
			</script>

			<button type="button" onclick="dateCheckSearch();" class="btn btn-info">검색 <span class='glyphicon glyphicon-search'></span></button>
		</div>
	</form>
	<%
		ArrayList<HistoryDTO> dtos = (ArrayList)request.getAttribute("list");
	%>
	<table class="table" style="margin-top:30px;">
		<tr><td>예약날짜</td><td>시간</td><td>예약자</td><td>전화번호</td><td>회의실</td><td>회의제목</td><td>상태</td><td>예약/변경날짜</td></tr>
		<%if(dtos != null){
			for(int i=0; i<dtos.size(); i++){
				HistoryDTO dto = dtos.get(i);
				String startTime = dto.getHst_Rsv_Start_Time();
				String endTime = dto.getHst_Rsv_End_Time();
				String time = startTime.substring(0,2) + ":" + startTime.substring(2,4) + "~";
				time += endTime.substring(0,2) + ":" + endTime.substring(2,4);
				
				String stat = dto.getHst_State();
				if(stat.equals("reserve"))
					stat = "예약";
				else if(stat.equals("modify"))
					stat = "수정";
				else if(stat.equals("delete"))
					stat = "삭제";
			%>
		<tr><td><%=dto.getHst_Rsv_Date()%></td><td><%=time%></td><td><%=dto.getHst_Rsv_Mem_Nm()%></td>
		<td><%=dto.getHst_Rsv_Mem_Pn()%></td><td><%=dto.getHst_Rsv_Confer_Nm()%></td>
		<td><%=dto.getHst_Rsv_Title()%></td><td><%=stat%></td><td><%=dto.getHst_Date()%></td></tr>
		<%}}%>
	</table>
	
	<br><br><br><br>
	<!-- footer -->
   <%@ include file="footer.jsp"%>  
		
</div>
</body>
</html>