<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.ibm.cof.dto.ConfDTO"%>
<%@ page import="com.ibm.cof.dto.ProjectDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회의실 예약 시스템</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 자동채우기 -->
<script src="js/ajax_auto.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<script src="js/RsvView.js"></script>
<link rel="stylesheet" type="text/css" href="css/RsvView.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   
<!--이모티콘 Font Awesome (added because you use icons in your prepend/append)-->
<link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />


<style>
.bootstrap-iso .formden_header h2,.bootstrap-iso .formden_header p,.bootstrap-iso form
   {
   font-family: Arial, Helvetica, sans-serif;
   color: black
}

.bootstrap-iso form button,.bootstrap-iso form button:hover {
   color: white !important;
}

.asteriskField {
   color: red;
}

body {
   background: url(image/10.jpg) center fixed;
   -webkit-background-size: cover;
   -moz-background-size: cover;
   -o-background-size: cover;
   background-size: cover;
   font-family: 'Noto Sans KR', sans-serif !important;
}

input {
   /*사용자가 입력하는 내용은 나눔명조체*/
   font-family: 'Noto Sans KR', sans-serif !important;
}

#conference {
   position: relative;
   width: 100%;
   height: 20px;
   margin-bottom: 5px;
   background-color: rgba(255, 255, 255, 0); /*마지막0: 투명도 */
}

.conf {
   position: absolute;
   text-align: center;
}

#timeDiv {
   /*border: 5px inset #48BAE4;*/
   position: relative;
   text-align: center;
   border-radius: 10px; /*모서리 약간 둥글게*/
   border-bottom-right-radius: 10px;
   border-top-right-radius: 10px;
   width: 70px;
}

.time {
   position: absolute;
   width: 70px;
   left: 0px;
}

#schedule {
   position: relative;
   width: 100%;
   height: 400px;
   margin-left: auto;
   margin-right: auto;
   margin-bottom: 30px;
   border: 1px;
   background-color: #F9F9F9;
   border-radius: 10px; /*모서리 약간 둥글게*/
   border-bottom-right-radius: 10px;
   border-top-right-radius: 10px;
}

.meeting {
   position: absolute;
   text-align: center;
   background-color: #F8DEBD;
   border: 1px inset #FF7182;
}

.empty {
   position: absolute;
   text-align: center;
   background-color: #F9F9F9;
   border-radius: 10px;
}

.line {
   position: absolute;
   height: 400px;
   width: 1px;
   border: 1px dotted #F8DEBD;
}

.line2 {
   position: absolute;
   left: 70px;
   border: 1px dotted #F8DEBD;
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
</style>


<script language="javascript">

function Reservation() {
	if(ValidationCheck() == false){	
		return false;
	}
	
	document.myForm.action = "Reservation.do";
	document.myForm.submit();
}

function Modify() {
	if(ValidationCheck() == false){	
		return false;
	}
	
	document.myForm.action = "ModifyRsv.do";
	document.myForm.submit();
}

function Delete() {
	//var seq = document.myForm.rsv_seq.value;
	//var pw = document.myForm.del_pw.value;

	document.myForm.action = "DeleteRsv.do";
	document.myForm.submit();
}

function adminMonthValidation(){
   var projectnm = "<%=(String)session.getAttribute("project")%>";
	if (document.myForm.date.value != "") {
		getAdminMonth(projectnm);
	}
}

<%String message = (String)request.getAttribute("message");
	if(message != null){
		%>alert('<%=message%>');<%
	}%>
	

</script>
</head>

<body>

	<!-- navigation bar -->
	<%@ include file="header.jsp"%>	

   <form method="post" name="myForm" action="Reservation.do">
      <div class="container">
         <!-- 사이트 선택 -->
         <div class="row" style="padding-bottom:25px;">
			<div class="col-md-6 col-sm-9 col-xs-12">
			<%if(session.getAttribute("project").equals("master")){ %>
				<select class="form-control" id="site" name="site" onchange="displayConf(this.value);">
				<option value="">선택하세요</option>
				<%ArrayList<String> proj = (ArrayList) request.getAttribute("proj");
					if (proj != null) {
						for (int i = 0; i < proj.size(); i++) {
							String name = proj.get(i);%>
					<option value="<%=name%>"><%=name%></option> <%}%>
				</select>
				<%}}else{
					String project = (String)session.getAttribute("project");%>
					<h2>${project} 회의실</h2>
					<input type="hidden" id="site" name="site" value=${project}>
					<script>displayConf('<%=project%>');</script>
				<%}%>
			</div>
		</div>

         <div class="row">
            <center>
               <!-- 달력 -->
               <div class="col-md-6 col-sm-6 col-xs-12" style="margin-top:15px;">

                  <div class="well well-lg col-md-12" style="padding-left:13%;">

                     <%@ include file="calendar/calendar.jsp"%>
                  </div>
               </div>
            </center>

            <!-- 회의실tab & 예약현황 -->
            <div class="col-md-6 col-sm-6 col-xs-12">
               <div>
               	  <!-- 회의실탭 -->
               	  <div class="col-md-6 col-sm-6 col-xs-12" id="conference"></div>
               
                  <div id="schedule" class="col-md-6 col-sm-6 col-xs-12">
                     <div id="timeDiv"></div>
                     <div id="meetings"></div>
                  </div>
               </div>
            </div>

         </div>

         <div class="row">
            <!-- 회의실 예약 입력창 -->
            <div class="search-container">
               <div class="row" id="resv_container">
                  <div class="col-md-12"></div>
                  <div>
                     <div class="well well-lg col-md-12" role="register">
                        <section class="register-form">
                        <div class="row">

                           
                        <div class="row">
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="date"> 날짜 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" readonly class="form-control" id="date"
                                       name="date">
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="start_time"> 시작시간
                                 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-clock-o"> </i>
                                    </div>

                                    <select class="form-control" name="start_time"
                                       id="start_time">
                                       <option value="">선택하세요</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="end_time"> 끝시간 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-clock-o"> </i>
                                    </div>

                                    <select class="form-control" name="end_time" id="end_time">
                                       <option value="">선택하세요</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="confer_nm"> 회의실 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-building"> </i>
                                    </div>
                                    <input type="text" readonly class="form-control" id="confer_nm"
                                       name="confer_nm">
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="title"> 회의제목 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-commenting"> </i>
                                    </div>
                                    <input type="text" class="form-control" id="title"
                                       name="title">
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="del_pw"> 비밀번호 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-lock"> </i>
                                    </div>
                                    <input type="password" class="form-control" id="del_pw"
                                       name="del_pw" data-toggle="tooltip" data-trigger="manual"
                                       data-title="Caps lock is on">
                                 </div>
                              </div>
                           </div>
                           
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="title"> 전화번호 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-phone"> </i>
                                    </div>
                                    <input type="text" id="phone" class="form-control"
                                       name="phone" value="0104995">
                                 </div>
                              </div>

                           </div>

                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="name"> 이름 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-smile-o"> </i>
                                    </div>
                                    <input class="form-control" id="name" name="name"
                                       placeholder="ex)홍길동" type="text" />
                                 </div>
                              </div>
                           </div>

                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label " for="email"> 이메일 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-envelope-o"></i>
                                    </div>
                                    <input type="text" class="form-control" id="email"
                                       name="email">
                                 </div>
                              </div>
                           </div>
                        </div>

                        <!-- 예약 버튼 -->
						<div id="register">
							<button type="button" class="btn btn-primary" onClick="Reservation();">예약</button>
						</div>

						<!-- 수정 및 삭제 -->
						<div id="registerInfo">
							<button type="button" class="btn btn-primary" onClick="Modify();">수정</button>
							<button type="button" class="btn btn-primary" onClick="Delete();">삭제</button>
						</div>
                        </div>
                        </section>
                        
                        <input type="hidden" id="rsv_seq" name="rsv_seq">
                     </div>
                  </div>


               </div>
            </div>
         </div>
   </form>

   <div style="margin-top: 30px"></div>
</body>
</html>