var conference = [];

function ValidationCheck(){
	theForm = document.myForm;
	
	if (theForm.date.value == "") {
		alert("날짜를 선택하세요.");
		theForm.date.focus();
		return false;
	}
	else if (theForm.confer_nm.value == "") {
		alert("회의실을 선택하세요.");
		theForm.confer_nm.focus();
		return false;
	}
	else if (theForm.start_time.value >= theForm.end_time.value) {
		alert("유효하지 않은 시간입니다.");
		return false;
	}
	else if (theForm.title.value == "") {
		alert("회의제목을 입력하세요.");
		theForm.title.focus();
		return false;
	}
	else if (theForm.del_pw.value == "") {
		alert("비밀번호를 입력하세요.");
		theForm.del_pw.focus();
		return false;
	}
	else if (theForm.phone.value == "") {
		alert("전화번호를 입력하세요.");
		theForm.phone.focus();
		return false;
	}
	else if (theForm.name.value == "") {
		alert("이름을 입력하세요.");
		theForm.name.focus();
		return false;
	}
	else if (theForm.email.value == "") {
		alert("이메일을입력하세요.");
		theForm.email.focus();
		return false;
	}
	return true;
}

/*
function isPossible(seq, pw){
	var possible = false;
	$.ajax({
		type : "post",
		url : "PasswordCheck.do",
		dataType : 'json',
		data : {
			seq : seq,
			pw: pw
		},
		success : function(data) {
			possible = data.result.valid;
			return possible;
		},
		error : function() {
			console.log("error");
		}
	});
	
	return possible;
}*/


// 분을 시간 문자열로 변환해 준다 540 -> 09:00
	function minToTime(time){
		var hr = time/60 - time/60%1;
		var min = time%60;
		var str;
		if(hr<10)
			str = "0"+hr+":";
		else
			str = hr+":";
		if(min<10)
			str += "0"+min;
		else
			str += min;
		return str;
	}
	
	//시간 문자열을 분으로 변경 0900 -> 540
	function timeToMin(time){
		var hr = parseInt(time.substring(0,2));
		var min = parseInt(time.substring(2,4));
		return hr*60 + min; 
	}
	
	//분을 디비에 저장하는 시간 문자열로 변경 540 -> 0900
	function minToStr(time){
		var hr = time/60 - time/60%1;
		var min = time%60;
		var str = "";
		if(hr<10)
			str += "0"+hr;
		else
			str += hr;
		if(min<10)
			str += "0"+min;
		else
			str += min;
		return str;
	}
	
	// 프로젝트별 회의실 목록을 보여준다.
	function displayConf(value){
		$.ajax({
			type : "post",
			url : "SelectBySite.do",
			dataType : 'json',
			data : {
				proj : value
			},

			success : function(data) {
				var confNumber = data.result.length;
				var width = (document.getElementById('schedule').offsetWidth-70)/confNumber;
				var left = 70;
				
				$('#meetings').empty();
				$('#conference').empty();
				for (var i = 0; i < data.result.length; i++) {
					$('#conference').append("<div class='conf' style='top:0px; left:"+left+"px; width:"+width+"px;'>"+
					data.result[i].confname+"</div>");
					left += width;
				}
				displayTime();
			},
			error : function() {
				console.log("error");
			}
		});
	}
	
	// 시간정보를 보여준다 09:00 ~ 18:00
	function displayTime(){
		var time = 540;
		var top = 0;
		$('#timeDiv').empty();
		for (var i = 0; i < 10; i++) {
			/*if(time == 720) {
				time += 60;
				continue;
			}*/
			$('#timeDiv').append("<div class='time' style='top:"+top+"px;'>"+minToTime(time)+"</div>");
			top += 40;
			time += 60;
		}
	}
	
	// 날짜를 클릭할 때마다 이벤트가 발생하며 회의 스케줄을 보여준다
	function displaySchedule(date){
		var site = document.getElementById('site').value;

		$.ajax({
			type : "post",
			url : "SelectRsvBySiteDate.do",
			dataType : 'json',
			data : {
				site : site,
				date : date
			},

			success : function(data) {
				var confNumber = data.confers.length;
				var width = (document.getElementById('schedule').offsetWidth - 70)/confNumber;
				var left = 70;
				var top, height, j=0, bottom;
				
				$('#meetings').empty();
				conference = [];
				
				for (var i = 0; i < data.confers.length; i++) {
					bottom = 0;
					
					conference.push(data.confers[i].confname);
					
					while (j < data.meetings.length && data.meetings[j].confer_nm == data.confers[i].confname) {
						top = (timeToMin(data.meetings[j].start) - 540) / 30 * 20;
						height = (timeToMin(data.meetings[j].end) - timeToMin(data.meetings[j].start)) / 30 * 20;

						// 예약되어있지 않는 공간
						$('#meetings').append("<div id='empty' class='empty'"
								+ "style='top:"+bottom+"px; left:"+left+"px; width:"+width+"px; height:"+(top-bottom)+"px;'"
								+ "onClick='reserve("+i+", "+(bottom/20*30+540)+", "+(top/20*30+540)+");'></div>");
						
						bottom = top + height;
						
						// 예약된 공간
						$('#meetings').append("<div id='reserved' class='meeting'"
								+ "style='top:"+top+"px; left:"+left+"px; width:"+width+"px; height:"+height+"px;'"
								+ "onClick='reserveInfo("+data.meetings[j].seq+");'>"+data.meetings[j].title+"</div>");
						j++;
					}
					
					// 예약되어있지 않는 공간
					$('#meetings').append("<div id='empty' class='empty'"
							+ "style='top:"+bottom+"px; left:"+left+"px; width:"+width+"px; height:"+(400-bottom)+"px;'"
							+ "onClick='reserve("+i+", "+(bottom/20*30+540)+", "+(360/20*30+540)+");'></div>");
					left += width;
				}
					
			},
			error : function() {
				console.log("error");
			}
		});
	}
	
	// 예약되어 있는 시간표를 클릭시 상세 정보를 보여준다.
	function reserveInfo(seq){
		$("#register").hide();
        $("#registerInfo").show();
        
        $.ajax({
	        type: "post",
	        url : "SelectRsvInfo.do",
	        dataType : 'json',
	        data: {
	        	seq : seq
	        },
	       
	        success : function(data) {
	        	$('#name').empty();
	        	$('#phone').empty();
	        	$('#email').empty();
	        	$('#date').empty();
	        	$('#confer_nm').empty();
	        	$('#title').empty();
	        	$('#rsv_seq').empty();
	        	
      			for(var i=0; i<data.result.length; i++) {
      				$('input[name="name"]').val(data.result[i].name);
      				$('input[name="phone"]').val(data.result[i].phone);
      				$('input[name="email"]').val(data.result[i].email);
      				$('input[name="date"]').val(data.result[i].date);
      				$('input[name="confer_nm"]').val(data.result[i].confer_nm);
      				$('input[name="title"]').val(data.result[i].title);
      				$('input[name="rsv_seq"]').val(""+seq);
      			  }
      			
      			timeSelectListAll(data.result[0].start_time, data.result[0].end_time);
	        },
	        error : function() {
	        	console.log("error");
	        }
 	});
	}
	
	// 빈 회의 시간표를 클릭시 예약 할 수 있도록 해준다.
	function reserve(conf_idx, start, end){
		//alert(minToTime(start) + " - " + minToTime(end));
		$("#registerInfo").hide();
        $("#register").show();
        
        timeSelectList(start, end)
        
        $('#name').empty();
    	$('#phone').empty();
    	$('#email').empty();
    	$('#confer_nm').empty();
    	$('#title').empty();
    	$('#del_pw').empty();
    	
    	$('input[name="name"]').val("");
		$('input[name="phone"]').val("");
		$('input[name="email"]').val("");
		$('input[name="confer_nm"]').val(conference[conf_idx]);
		$('input[name="title"]').val("");
		$('input[name="del_pw"]').val("");
	}
	
	function timeSelectList(start, end){
		$('#start_time').empty();
		for(var i=start; i<end; i+=30) {
			$('#start_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
		  }
		$("#start_time").val(minToStr(start)).attr("selected", "selected");
		
		$('#end_time').empty();
		for(var i=start+30; i<=end; i+=30) {
			$('#end_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
		  }
		$("#end_time").val(minToStr(end)).attr("selected", "selected");
	}
	
	function timeSelectListAll(start, end){
		
		$('#start_time').empty();
		for(var i=540; i<1080; i+=30) {
			$('#start_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
		  }
		$("#start_time").val(start).attr("selected", "selected");
		
		$('#end_time').empty();
		for(var i=570; i<=1080; i+=30) {
			$('#end_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
		  }
		$("#end_time").val(end).attr("selected", "selected");
	}
	
	
	$(document).ready(function(){
		$("#registerInfo").hide();

	});