/* 사이트,회의실정보,날짜 인수를 모두 받아와서 Ajax 인수에 넘겨주는 함수 */
function allValues()
{
	var site = $('#site').val();
	var date = $('#date').val();
	//var confer_nm = $('#confer_nm').val();
	
	callAjaxListByValues(site,date);
}

/* 회의실 Tab을 클릭할 때마다 색깔이 변하면서 포커싱되고 회의실을 인자로 전달하는 Ajax 호출 */
function changeConf(idx, name){
		var id = "";
		for(var i=1; i<=4; i++){
			id = "#conf" + i;
			$(id).removeClass('active');
		}
		id = "#conf" + String(idx);
		$(id).addClass('active');
		document.myForm.confer_nm.value = name;
		//allValues();
		
			
}

/* 예약현황을 뿌려주는 알고리즘 함수 */
function rsvPrint(temp1,temp2,flag1,flag2,flag3,flag4,flag5,flag6,flag7,flag8,
		flag9,flag10,flag11,flag12,flag13,flag14,flag15,flag16,flag17,flag18)
{
	if ((temp1 >= 900 && temp1 < 930) || (temp2 > 900 && temp2 <= 930)) { //0900~0930
        if (temp1 == temp2) {

        } else {
           flag1 = "X"; // flag ="X";
           temp1 = temp1 + 30;
           
        }

     }

     if ((temp1 >= 930 && temp1 < 1000) || (temp2 > 930 && temp2 <= 1000)) { //0930~1000

        if (temp1 == temp2) {

        } else {

           flag2 = "X"; // flag ="X";
           temp1 = temp1 + 70;
           
        }

     }

     if ((temp1 >= 1000 && temp1 < 1030) || (temp2 > 1000 && temp2 <= 1030)) { //1000~1030

        if (temp1 == temp2) {

        } else {
           flag3 = "X"; // flag ="X";
           temp1 = temp1 + 30;
          
        }

     }

     if ((temp1 >= 1030 && temp1 < 1100) || (temp2 > 1030 && temp2 <= 1100)) { //1030~1100

        if (temp1 == temp2) {

        } else {
           flag4 = "X"; // flag ="X";
           temp1 = temp1 + 70;
          

        }
     }

     if ((temp1 >= 1100 && temp1 < 1130) || (temp2 > 1100 && temp2 <= 1130)) { //1100~1130

        if (temp1 == temp2) {

        } else {
           flag5 = "X"; // flag ="X";
           temp1 = temp1 + 30;
           
        }
     }

     if ((temp1 >= 1130 && temp1 < 1200) || (temp2 > 1130 && temp2 <= 1200)) { //1130~1200

        if (temp1 == temp2) {

        } else {
           flag6 = "X"; // flag ="X";
           temp1 = temp1 + 70;
           
        }

     } 
     
     // ADD
     if ((temp1 >= 1200 && temp1 < 1230) || (temp2 > 1100 && temp2 <= 1130)) { //1100~1130

         if (temp1 == temp2) {

         } else {
            flag7 = ""; // flag ="X";
            temp1 = temp1 + 30;
            
         }
      }

      if ((temp1 >= 1230 && temp1 < 1300) || (temp2 > 1130 && temp2 <= 1200)) { //1130~1200

         if (temp1 == temp2) {

         } else {
            flag8 = ""; // flag ="X";
            temp1 = temp1 + 70;
           
         }

      } // Add

     if ((temp1 >= 1300 && temp1 < 1330) || (temp2 > 1300 && temp2 <= 1330)) { //1300~1330

        if (temp1 == temp2) {

        } else {
           flag9 = "X"; // flag ="X";
           temp1 = temp1 + 30;
           
        }

     }

     if ((temp1 >= 1330 && temp1 < 1400) || (temp2 > 1330 && temp2 <= 1400)) { //1330~1400

        if (temp1 == temp2) {

        } else {
           flag10 = "X"; // flag ="X";
           temp1 = temp1 + 70;
           
        }

     }
     if ((temp1 >= 1400 && temp1 < 1430) || (temp2 > 1400 && temp2 <= 1430)) { //1400~1430

        if (temp1 == temp2) {

        } else {
           flag11 = "X"; // flag ="X";
           temp1 = temp1 + 30;
           
        }

     }

     if ((temp1 >= 1430 && temp1 < 1500) || (temp2 > 1430 && temp2 <= 1500)) { //1430~1500

        if (temp1 == temp2) {

        } else {
           flag12 = "X"; // flag ="X";
           temp1 = temp1 + 70;
           
        }

     }

     if ((temp1 >= 1500 && temp1 < 1530) || (temp2 > 1500 && temp2 <= 1530)) { // temp1(09:00) ~ temp2( 11:00)

        if (temp1 == temp2) {

        } else {
           flag13 = "X"; // flag ="X";
           temp1 = temp1 + 30;
           
        }

     }

     if ((temp1 >= 1530 && temp1 < 1600) || (temp2 > 1530 && temp2 <= 1600)) { // temp1(09:00) ~ temp2( 11:00)

        if (temp1 == temp2) {

        } else {
           flag14 = "X"; // flag ="X";
           temp1 = temp1 + 70;
           
        }

     }

     if ((temp1 >= 1600 && temp1 < 1630) || (temp2 > 1600 && temp2 <= 1630)) { // temp1(09:00) ~ temp2( 11:00)

        if (temp1 == temp2) {

        } else {
           flag15 = "X"; // flag ="X";
           temp1 = temp1 + 30;
          
        }

     }

     if ((temp1 >= 1630 && temp1 < 1700) || (temp2 > 1630 && temp2 <= 1700)) { // temp1(09:00) ~ temp2( 11:00)

        if (temp1 == temp2) {

        } else {
           flag16 = "X"; // flag ="X";
           temp1 = temp1 + 70;
           
        }

     }

     if ((temp1 >= 1700 && temp1 < 1730) || (temp2 > 1700 && temp2 <= 1730)) { // temp1(09:00) ~ temp2( 11:00)

        if (temp1 == temp2) {

        } else {
           flag17 = "X"; // flag ="X";
           temp1 = temp1 + 30;
           
        }

     }

     if ((temp1 >= 1730 && temp1 < 1800) || (temp2 > 1730 && temp2 <= 1800)) { // temp1(09:00) ~ temp2( 11:00)

        if (temp1 == temp2) {

        } else {
           flag18 = "X"; // flag ="X";
           temp1 = temp1 + 70;
           
        }

     }    
    
 	$('#td900').append(flag1);  $('#td930').append(flag2); $('#td1000').append(flag3); $('#td1030').append(flag4); 
 	$('#td1100').append(flag5);  $('#td1130').append(flag6); $('#td1300').append(flag9); $('#td1330').append(flag10);
 	$('#td1400').append(flag11);  $('#td1430').append(flag12); $('#td1500').append(flag13); $('#td1530').append(flag14);
 	$('#td1600').append(flag15);  $('#td1630').append(flag16); $('#td1700').append(flag17); $('#td1730').append(flag18);
}


/* 회의실 이름,날짜를 Ajax 인수로 넘김 */
function callAjaxListByValues(site,date)
{	
	$.ajax({
        type: "post",
        url : "SelectByCondition.do",
        dataType : 'json',
        data: {
        	id1 : site,
        	id2 : date
        	
        },
       
        success : function(data) {
        	
        	$('#td900').empty();  $('#td930').empty(); $('#td1000').empty(); $('#td1030').empty(); 
     		$('#td1100').empty();  $('#td1130').empty(); $('#td1300').empty(); $('#td1330').empty();
     		$('#td1400').empty();  $('#td1430').empty(); $('#td1500').empty(); $('#td1530').empty();
     		$('#td1600').empty();  $('#td1630').empty(); $('#td1700').empty(); $('#td1730').empty();
     		        	
     		var flag1="O"; var flag2="O"; var flag3="O"; var flag4="O"; var flag5="O"; var flag6="O";
    		var flag7="O"; var flag8="O"; var flag9="O"; var flag10="O"; var flag11="O"; var flag12="O";
    		var flag13="O"; var flag14="O"; var flag15="O"; var flag16="O"; var flag17="O"; var flag18="O";
    		var room_name="회의실";
        	
    		for(var i=0;i<data.result.length;i++)
        	
        	{        		        		
        		var start_time = data.result[i].start_time; 
        		var end_time = data.result[i].end_time;
        		
        		var temp1 = parseInt(start_time,10);
        		var temp2 = parseInt(end_time,10);
        		
        		rsvPrint(temp1,temp2,flag1,flag2,flag3,flag4,flag5,flag6,flag7,flag8,
        				flag9,flag10,flag11,flag12,flag13,flag14,flag15,flag16,flag17,flag18);
        			        		    		
        	}
    		
    		/* 동적으로 회의실 번호에 따라 예약현황을 뿌려주는 뷰 Logic */
    		for(var i=1;i<=data.result[0].room_count;i++)
    		{
    			var temp_room_name = room_name + i;
    			for(var j=0;j<data.result.length;j++) 
    			{
    				if(data.result[j].confer_nm == temp_room_name) // 가져온 예약현황중 회의실 번호에 따라 동적으로 조건분기
    				{
    					var start_time = data.result[j].start_time; // 가져온 예약현황의 시작시간
    	        		var end_time = data.result[j].end_time; // 가져온 예약현황의 종료시간
    	        		
    	        		var temp1 = parseInt(start_time,10); // 시작시간 Integer형으로 변환
    	        		var temp2 = parseInt(end_time,10);   // 종료시간 Integer형으로 변환 
    	        		
    					rsvPrint(temp1,temp2,flag1,flag2,flag3,flag4,flag5,flag6,flag7,flag8,
    	        				flag9,flag10,flag11,flag12,flag13,flag14,flag15,flag16,flag17,flag18);
    					
    					// rsvPrint함수를 이용하든 다른 함수를 이용하든 가져온 현황에따라 div 또는 table에 뿌리기
    				}
    				
    			}
    		}
        	
        },
       
        error : function() {
        	console.log("error");
        }
	});
}





