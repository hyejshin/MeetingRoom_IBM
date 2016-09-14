// 분을 시간 문자열로 변환해 준다
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
				var top, height, j=0;
				
				$('#meetings').empty();
				for (var i = 0; i < data.confers.length; i++) {
					while(data.meetings[j].confer_nm == data.confers[i].confname){
						top = (timeToMin(data.meetings[j].start) - 540) / 30 * 20;
						height = (timeToMin(data.meetings[j].end) - timeToMin(data.meetings[j].start)) / 30 * 20;
						$('#meetings').append("<div class='meeting'"
								+ "style='top:"+top+"px; left:"+left+"px; width:"+width+"px; height:"+height+"px;'"
								+ "onClick='reserveInfo("+data.meetings[j].seq+");'>"+data.meetings[j].title+"</div>");
						j++;
					}
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
		alert(seq);
	}
	
	// 빈 회의 시간표를 클릭시 예약 할 수 있도록 해준다.
	function reserve(){
		alert("reserve");
	}