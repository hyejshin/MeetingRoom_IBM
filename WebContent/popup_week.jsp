<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="css/datepicker.css">
<script src="js/bootstrap-datepicker.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
<title>Insert title here</title>
</head>

<script>
<%

request.setCharacterEncoding("UTF-8");

%>

window.onload = function() {
	document.frm.start_dt.value = window.opener.document.myForm.date.value;
	document.frm.phone.value = window.opener.document.myForm.phone.value;
	document.frm.name.value = window.opener.document.myForm.name.value;
	document.frm.start_time.value = window.opener.document.myForm.start_time.value;
	document.frm.end_time.value = window.opener.document.myForm.end_time.value;
	document.frm.email.value = window.opener.document.myForm.email.value;
	document.frm.confer_nm.value = window.opener.document.myForm.confer_nm.value;
	document.frm.title.value = window.opener.document.myForm.title.value;
	document.frm.site.value = window.opener.document.myForm.site.value;
	document.frm.del_pw.value = window.opener.document.myForm.del_pw.value;
	
	$("#summary").empty();
	$("#summary").val("");
	var sDate = $("#start_dt").val();
	/* ���� �Ľ� �κ� */
	var yy = parseInt(sDate.substr(0, 4), 10);
    var mm = parseInt(sDate.substr(5, 2), 10);
    var dd = parseInt(sDate.substr(8), 10);
	var day = null;
    
    var d = new Date(yy,mm - 1, dd);
    var weekday=new Array(7);
    weekday[0]="��";
    weekday[1]="��";
    weekday[2]="ȭ";
    weekday[3]="��";
    weekday[4]="��";
    weekday[5]="��";
    weekday[6]="��";
    
    sDate = sDate.replace(/\-/g,''); // 2016-09-20���� -������ 20160920 �����
    //getSecofWeek(sDate); // ~��° �ֿ��� �� �����
	/* �Ľ��� ������ �ѱ۰� ��ġ�� */
	
	mm = mm + "�� "; // ~��
    day = weekday[d.getDay()] + "���� "; // ~���� 
    var dayofweek = getSecofWeek(sDate) + "��° �� "; // ~��° ��
    var total = "�ſ� " + dayofweek + day;
    $("#summary").val(total);
}

function selectRadio()
{
	var test =$(":radio[name=rd]:checked").val();
	
	if(test == "repeat")
	{		
		$("input[name=end_dt]").attr("disabled",true);
		$("input[name=end_dt]").val("");
		
	}
	if(test == "end_dt") {
		$("input[name=end_dt]").attr("disabled",false);
		
		
	}
}

$(document).ready(function()
{
    $('#dayFormat').change(function(){
        var test = $('#dayFormat option:selected').val();
        
        if(test=="day") {
        	$("#summary").empty();
        	$("#summary").val("");
        	var sDate = $("#start_dt").val();
        	/* ���� �Ľ� �κ� */
        	var yy = parseInt(sDate.substr(0, 4), 10);
    	    var mm = parseInt(sDate.substr(5, 2), 10);
    	    var dd = parseInt(sDate.substr(8), 10);
    		var day = null;
    	    
    	    var d = new Date(yy,mm - 1, dd);
    	    var weekday=new Array(7);
    	    weekday[0]="��";
    	    weekday[1]="��";
    	    weekday[2]="ȭ";
    	    weekday[3]="��";
    	    weekday[4]="��";
    	    weekday[5]="��";
    	    weekday[6]="��";
    	    
    	    sDate = sDate.replace(/\-/g,''); // 2016-09-20���� -������ 20160920 �����
    	    //getSecofWeek(sDate); // ~��° �ֿ��� �� �����
			/* �Ľ��� ������ �ѱ۰� ��ġ�� */
			
			mm = mm + "�� "; // ~��
    	    day = weekday[d.getDay()] + "���� "; // ~���� 
    	    var dayofweek = getSecofWeek(sDate) + "��° �� "; // ~��° ��
    	    var total = "�ſ� " + dayofweek + day;
    	    $("#summary").val(total);
    	   	        	    
        } else if(test == "date") {
        	$("#summary").empty();
        	$("#summary").val("");
        	var sDate = $("#start_dt").val();
    		var dd = parseInt(sDate.substr(8), 10);
    		var total = "�ſ� " + dd +"��";
    		$("#summary").val(total);
    	}
    });
});

/* ���° ������ ��ȯ�ϴ� �Լ� */
function getSecofWeek(date){
	var d = new Date( date.substring(0,4), parseInt(date.substring(4,6))-1, date.substring(6,8) );
	var fd = new Date( date.substring(0,4), parseInt(date.substring(4,6))-1, 1 );
	return Math.ceil((parseInt(date.substring(6,8))+fd.getDay())/7);
}

function closeMe(f) {

	//var selectBox = document.getElementById("dayFormat");
	//var selected = selectBox.options[selectBox.selectedIndex].value;
	
	f.action="RsvEveryWeekByDay.do";
	return true;
			
    window.close();

}


</script>
<body>
<form name="frm" method="post" onsubmit="return closeMe(this);">
<input type="hidden" id="phone" name="phone"><input type="hidden" id="name" name="name">
<input type="hidden" id="start_time" name="start_time"><input type="hidden" id="email" name="email">
<input type="hidden" id="end_time" name="end_time"><input type="hidden" id="confer_nm" name="confer_nm">
<input type="hidden" id="title" name="title"><input type="hidden" id="del_pw" name="del_pw">
<input type="hidden" id="site" name="site">
<center>�ݺ��� : ����  </center><br>
<br><br>
<center>�ݺ��ֱ�
<select id="per" name="per">
<option value="1" selected="selected">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
</select>
</center>

							<!-- ����ڰ� ������ ���೯¥�� ���۳�¥�� ������ -->
<center>���۳�¥ : <input type="text" name="start_dt" id="start_dt" readonly style="width:20%" onfocus="getWeekday(this.value);"></center><br> 
<center><br>

			 ���ᳯ¥ : 
			  <input type="text" name="end_dt" id="end_dt" style="width:20%"><br>
			  <script>
			  
			  $('#end_dt').datepicker({
				   dateFormat : 'yy-mm-dd'
				   
				});

				$('#end_dt').datepicker('hide');

			  </script>
			  
			  <br><br><br><br>Summary : <input type="text" readonly id="summary" name="summary">
</center>
<center><input type="submit" value="����" onClick="closeMe();">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="���" onClick="window.close();">
</center>
</form>
</body>
</html>