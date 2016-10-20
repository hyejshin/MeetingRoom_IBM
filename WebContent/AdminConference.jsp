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
		fillOrder("#order2", 0);
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
      				$('input[name="prvName"]').val(data.result[i].name);
      				$("#order2").val(data.result[i].order).attr("selected", "selected");
      			  }
	        },
	        error : function() {
	        	console.log("error");
	        }
 	});
	}
	
	function fillOrder(id, idx){
		$.ajax({
	        type: "post",
	        url : "SelectBySite.do",
	        dataType : 'json',
	        data: { },
	       
	        success : function(data) {
	        	$('#seq').empty();
	        	$('#name').empty();
	        	$('input[name="name"]').val("");
	        	
	        	$(id).empty();
      			for(var i=1; i<=data.result.length+idx; i++) {
      				$(id).append("<option value="+i+">"+i+"번</option>");
      			  }
      			if(idx == 1)
      				$(id).val(data.result.length+idx).attr("selected", "selected");
	        },
	        error : function() {
	        	console.log("error");
	        }
 	});
	}
	
	function CheckAgain(idx, confer_nm){
	      
	      if (confirm("회의실을 삭제하시면 해당 회의실 예약도 같이 삭제됩니다. 그래도 진행하시겠습니까?") == true){//확인
	      
	         location.replace("DeleteConf.do?seq="+idx+"&confer_nm="+confer_nm);
	      }else{   //취소
	          return;
	      }
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
</style>

</head>
<body>

	<!-- navigation bar -->
	<%@ include file="header.jsp"%>	
	<h3>&nbsp;&nbsp;${project} 관리자 페이지</h3>
	
	<ul class="nav nav-pills">
	<li><a href="AdminRsv.jsp">예약관리</a></li>
	<li><a href="AdminRsvHist.jsp">예약내역</a></li>
	<li><a href="SearchMember.do?option=all">On-Boarding</a></li>
	<li><a href="SearchBlock.do?option=all">Off-Boarding</a></li>
	<li class="active"><a href="SelectConf.do">회의실관리</a></li>
	<li><a href="AdminSetting.do">설정</a></li>
</ul>	
	
	<div class="container">
	
		<!-- 회의실등록 -->
		<div style="margin-bottom:20px; margin-top:20px;">
		<button type="button" class="btn btn-default" data-toggle="collapse" 
		data-target="#register" onclick="fillOrder('#order1', 1);"> 회의실등록▽
		<span class="glyphicon glyphicon-user-add"></span></button></div>
		
		<div id="register" class="collapse" style="margin-bottom: 40px; align:center;">
			<form method="post" name="registerForm" action="InsertConf.do">
			<input type="hidden" name="page" value="AdminMember.jsp">
				<div class="form-inline">
					<label for="name">회의실이름:</label> <input type="text"
						class="form-control" name="name" placeholder="회의실이름">

					<label for="order">회의실번호:</label>
					<select class="form-control" name="order" id="order1">
					</select>
								
					<button type="submit" class="btn btn-primary" style="margin-left:20px;">등록</button>
				</div>
			</form>
		</div>

		<!-- 회의실 정보 수정 -->
		<div id="update" class="collapse" style="margin-bottom: 40px;">
			<h3>회의실 정보 수정</h3><br>
			<form method="post" name="modifyForm" action="UpdateConf.do">
			<input type="hidden" name="seq" id="seq">
				<div class="form-inline">					
					<label for="name">회의실이름:</label> <input type="text"
						class="form-control" name="name" id="name">
					<label for="order">회의실번호:</label>
					<select class="form-control" name="order" id="order2">
					</select>
					<input type="hidden" name="prvName" id="prvName">
					<button type="submit" class="btn btn-primary" style="margin-left:20px;">수정</button>
					<a href="#" class="btn btn-default" data-toggle="collapse" data-target="#update"> 취소</a>
				</div>
			</form>
		</div>
		
		<%
			ArrayList<ConfDTO> dtos = (ArrayList) request.getAttribute("list");
		%>
		<div class="col-md-10 col-sm-12 col-xs-12 col-centered">
		<table class="table table-hover" style="margin-top:30px; text-align:center;">
			<tr>
				<td>회의실번호</td>
				<td>회의실이름</td>
				<td></td>
			</tr>

			<%for(int i=0; i<dtos.size(); i++){
				ConfDTO dto = dtos.get(i);%>
			<tr>
				<td><%=dto.getConfrn_Order()%></td>
				<td><%=dto.getConfrn_Nm()%></td>
				
				<td><a href="#" data-toggle="collapse" data-target="#update" 
					onclick="fillConferInfo(<%=dto.getConfrn_Seq()%>);">수정</a> |
					<a href="#" onclick="CheckAgain(<%=dto.getConfrn_Seq()%>, '<%=dto.getConfrn_Nm()%>');">삭제</a></td>
			</tr>
			<%}%>
		</table>
		</div>
		</div>

	<br>
		
		<!-- footer -->
   		<%@ include file="footer.jsp"%>  
</body>
</html>