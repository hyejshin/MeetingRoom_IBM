<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script>

function btn(value)
{
	var param = $("input[type=radio][id=rd]:checked").val();
	//alert(value);
	callAjax(value);
}

function callAjax(value){
	    
	  $.ajax({
	        type: "post",
	        url : "test.do",
	        //dataType : 'json',
	        data: {
	        	id : value
	        },
	       
	        success : function(data) {
	        	$('#td1').empty();
	        	$('#td2').empty();
	        	$('#td3').empty();
	         	//alert(data.result[0].phone);     	
	        	//$('#tr1').append("<tr><td>"+data.result[i].phone+"</td></tr>");
	        	for(var i=0; i<data.result.length; i++) {
	        		$('#td1').append("<tr><td>"+data.result[i].phone+"</td></tr>");  
	        		$('#td2').append("<tr><td>"+data.result[i].email+"</td></tr>");
	        		$('#td3').append("<tr><td>"+data.result[i].site+"</td></tr>");
   					
   					 
      			  }
	        	
	        	
	        },
	        error : function() {
	        	console.log("error");
	        }
 	});
	
}

</script>

<body>
<div style="width:500px;margin:auto;margin-top:200px;">
	 <table id="table" width="500" cellpadding="0" cellspacing="0" border="1">
	 <form id="frm">
	 	   
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
	 
	 <!-- <div id="ajaxReturn">ajaxReturnOutput</div> -->
		
			
		
	 
	 
</div>
</body>
</html>