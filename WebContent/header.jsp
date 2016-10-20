<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

.navbar-header {
   float: left;
   padding: 1px;
   text-align: center;
   width: 100%;
   margin-bottom:1px; 
}

.navbar-brand {
   float: none;
}

#myNavbar {
   float: right;
   padding: 1px;
   text-align: center;
   width: 100%;
   margin-top:1px; 
   padding-top:1%; 
   padding-left:60%; 
}

.navbar .navbar-collapse {
   float: center;
   padding: 1px;
   text-align: center;
   width: 100%;
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

.font-style{
   font-family: 'Jeju Gothic';
   font-size: 18px;
}

nav a {
   position:relative;
   display:inline-block;
   outline:none;
   text-decoration:none;
   letter-spacing:1px;
   

}
/* Effect 16: fall down */
.cl-effect-16 a {

}
.cl-effect-16 a::before {
   color:#18C9D2;
   content: attr(data-hover);
   position: absolute;
   opacity: 0;
   text-shadow: 0 0 1px rgba(255,255,255,0.3);
   -webkit-transform: scale(1.1) translateX(10px) translateY(-10px) rotate(4deg);
   -moz-transform: scale(1.1) translateX(10px) translateY(-10px) rotate(4deg);
   transform: scale(1.1) translateX(10px) translateY(-10px) rotate(4deg);
   -webkit-transition: -webkit-transform 0.3s, opacity 0.3s;
   -moz-transition: -moz-transform 0.3s, opacity 0.3s;
   transition: transform 0.3s, opacity 0.3s;
   pointer-events: none;
}

.cl-effect-16 a:hover::before,
.cl-effect-16 a:focus::before {
   -webkit-transform: scale(1) translateX(0px) translateY(0px) rotate(0deg);
   -moz-transform: scale(1) translateX(0px) translateY(0px) rotate(0deg);
   transform: scale(1) translateX(0px) translateY(0px) rotate(0deg);
   opacity: 1;
}
/*--nav-responsive--*/
@media screen and (max-width:800px) {
    span.menu {
     width: 35px;
     height: 35px;
     background: url('./image/nav.png') no-repeat;
     display: inline-block;
     cursor: pointer;
     float: right;
     margin-top: 0.5em;
   }

   .top-menu {
     width: 100%;
     display: none;
     text-align: center;
     padding: 0;
     margin-top: 4px;
   }
   .top-menu ul{
      float:none;
      width:100%;
   }
   .top-menu ul li{
      display:block;
      float: none;
      border:none;
   }
   .top-menu ul li a {
      color: #fff;
      display: block;
      padding: 10px 0px;
      margin: 0px 0;
   }
}


</style>
<nav class="navbar navbar-inverse">
   <div class="container-fluid">
      
      <div class="navbar-header">         
         <img class="img-responsive2" src="image/colorbar-01.png" title="top">
         <div class="col-md-12 col-sm-12 col-xs-12">
         <a class="navbar-brand" href="home.do"
         style="margin-top:1px;color:#000; font-family:'Jeju Gothic', serif;font-size:25px">
            IBM 회의실 예약 시스템</a>
         </div>
      </div>
      <span class="menu"></span>
      <nav>
      <div id="myNavbar" class="top-menu">
         <div class="col-md-12 col-sm-12 col-xs-12">
         <ul class="nav navbar-nav inline cl-effect-16">
            <li><a href="home.do" class="font-style" style="color:#000;">예약/현황</a></li>
            <li><a href="SearchRsv.do?option=all" class="font-style" style="color:#000;">검색</a></li>

            <%if(session.getAttribute("project").equals("master")){ %>
            <li><a href="SelectProject.do" class="font-style" style="color:#000;"><span
                  class="glyphicon glyphicon-user"></span>관리자</a></li>
            <%}else if(session.getAttribute("admin").equals("yes")){ %>
            <li><a href="AdminRsv.jsp" class="font-style" style="color:#000;"><span
                  class="glyphicon glyphicon-user"></span>관리자</a></li>
            <%}%>
            <%if(session.getAttribute("admin").equals("no")){ %>
            <li><a href="AdminLogin.jsp" class="font-style" style="color:#000;"><span
                  class="glyphicon glyphicon-log-in"></span> 관리자Login</a></li>
            <%}else{ %>
            <li><a href="AdminLogout.do" class="font-style" style="color:#000;"><span
                  class="glyphicon glyphicon-log-out"></span> Log out</a></li>
            <%} %>
         </ul>
      </div></div>
      </nav>
		<script>
			$("span.menu").click(function() {
				$(".top-menu").slideToggle("slow", function() {
					// Animation complete.
				});
			});
		</script>

	</div>
   </nav>
   