<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"  type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!--이모티콘 Font Awesome (added because you use icons in your prepend/append)-->
<link rel="stylesheet"
   href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />

<link rel="stylesheet" href="css/dialog.css">
<title>관리자 로그인</title>


<style>
@import url(http://fonts.googleapis.com/earlyaccess/kopubbatang.css);
/*font*/
@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);


   /* 스마트폰 세로 */
   @media only screen and (max-width : 320px) {
      
   }
   
   /* iPhone4와 같은 높은 크기 세로 */
   @media only screen and (-webkit-min-device-pixel-ratio : 1.5) , only
      screen and (min-device-pixel-ratio : 1.5) {
      
   }   

   /* iPad 세로 */
   @media only screen and (min-device-width : 768px) and (max-device-width
      : 1024px) and (orientation : portrait) {
      
      
   }
   
   /* 데스크탑 브라우저 가로 */
   @media only screen and (min-width : 1224px) {
      
   }
   
   /* 큰 모니터 */
   @media only screen and (min-width : 1824px) {
      
   }




.col-centered {
   float: none;
   margin-right: auto;
   margin-left: auto;
}

.navbar-header {
   float: left;
   padding: 1px;
   text-align: center;
   width: 100%;
   margin-bottom: 1px;
}

.navbar-brand {
   float: none;
}

#myNavbar {
   float: right;
   padding: 1px;
   text-align: center;
   width: 100%;
   margin-top: 1px;
   padding-top: 1%;
   padding-left: 60%;
}

.navbar-inverse {
   background-color: rgba(255, 255, 255, 1);
   border: 0px solid #000000;
}

.img-responsive2 {
   display: inline-block;
   max-width: 100%;
   height: 2%;
   margin-bottom: 3%;
}

.img-responsive3 {
   display: inline-block;
   width: 100%;
   max-width: 100%;
   height: 1px;
   margin-top: 1%;
}

.font-style {
   font-family: 'Jeju Gothic', serif;
   font-size: 18px;
}
</style>
<body>

   <!-- navigation bar -->
   <nav class="navbar navbar-inverse">
   <div class="container-fluid">

      <div class="navbar-header">
         <img class="img-responsive2" src="image/colorbar-01.png" title="top">
         <div class="col-md-12 col-sm-12 col-xs-12">
            <a class="navbar-brand" href="#"
               style="margin-top: 1px; color: #000; font-family: 'Jeju Gothic', serif; font-size: 30px">
               IBM 회의실 예약 시스템</a>
         </div>
      </div>

      <div id="myNavbar">
         <div class="col-md-12 col-sm-12 col-xs-12">
            <ul class="nav navbar-nav inline">
               <li><a href="#" class="font-style" style="color: #000;">예약/현황</a></li>
               <li><a href="#" class="font-style" style="color: #000;">검색</a></li>
            </ul>
         </div>
      </div>
   </div>
   </nav>

   <div>
      <center
         style="margin-top: 3%; font-size: 34px; font-family: 'Jeju Gothic', serif;">
         <hr>
         Admin Login
         <hr>
      </center>
      <center
         style="font-size: 18px; font-family: 'Jeju Gothic', serif;">
         관리자 페이지는 각 사이트 담당자에게 배정된 아이디, 비밀번호로 이용 가능하며<br> 일반 사용자께서는 홈페이지
         주소를 다시 한번 확인 부탁드립니다.
      </center>
   </div>


   <div>
      <div class="well well-lg-7 col-lg-7 col-md-12 col-sm-12 col-xs-12 col-centered"
         style="border-radius: 2px; background: rgba(0, 90, 158, 0.2); margin-top: 2%;">

         <form method="post" action="AdminLogin.do">
            <div class="container">
               <div class="row" >
                  
                  <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                     <div class ="col-lg-3 col-md-3 col-sm-0 col-xs-0"></div>
                     <div class ="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                        <div class="form-group" style="float:right;">
                        
                           <label class="control-label"> 아이디 </label>
                           <div class="input-group" >
                              <div class="input-group-addon">
                                 <i class="fa fa-smile-o"> </i>
                              </div>
                              <input type="text" class="form-control" id="id" name="id">
                           </div>
                        </div>
                     </div>
                     <div>
                     <div class ="col-lg-3 col-md-3 col-sm-0 col-xs-0"></div>
                     <div class ="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                        <div class="form-group">
                           <label class="control-label"> 비밀번호 </label>
                           <div class="input-group">
                              <div class="input-group-addon">
                                 <i class="fa fa-lock"> </i>
                              </div>
                              <input type="password" class="form-control" id="pw" name="pw"
                                 data-title="Caps lock is on">
                           </div>
                        </div>
                     </div>
                  </div>
                  </div>
                  <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 ">
                     <button class="btn " type="submit" name="go"
                        style="font-size: 20px; margin-top: 30px; margin-buttom: 20px; width: 200px; height: 130px; background-color: #00599D; color: #FFFFFF">
                        AdminLogin</button>
                  </div>
                  
               </div>
            </div>

            <div class="row">
               <div class="col-md-12"></div>
            </div>


            <div class="row row-sapn"></div>
            <div class="row">
               <div class="col-md-12"></div>
            </div>

         </form>
      </div>
   </div>




</body>
</html>