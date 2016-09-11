<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<!-- User defied css -->
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script src="js/ajax.js"></script>

<body>
<div style="width:500px;margin:auto;margin-top:200px;">
	 <table id="table" width="500" cellpadding="0" cellspacing="0" border="1">
	 <form id="frm">
				<div class="search-container">
					<div class="ui-widget">
						Name :<input type="text" id="term" name="term" class="search" /><br>
						Phone:<input type="text" id="phone" name="phone"><br>
	       				Email:<input type="text" id="email" name="email"><br>
	      			 	Site :<input type="text" id="site" name="site"><br>
					</div>
				</div>
			
	 	   
	     	<input id="rd" name="rd" type="radio" value="jung" onclick="btn(this.value)">jung
	       	<input id="rd" name="rd" type="radio" value="yoon" onclick="btn(this.value)">yoon
	       	<input id="rd" name="rd" type="radio" value="hong" onclick="btn(this.value)">hong
	       	<input id="rd" name="rd" type="radio" value="park" onclick="btn(this.value)">park
	       	<br>
	              	
	       	<tr>
				<td>Phone</td>
				<td>Email</td>
				<td>Site</td>
			</tr>
			
			<tr>
				<td id=td1>
				
				</td>
				<td id=td2>
				
				</td>
				<td id=td3>
				
				</td>
			</tr>
			
			
				     
	 </form>
	 </table>
	  
	 
</div>
</body>
</html>