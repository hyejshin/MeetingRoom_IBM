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


/* 날짜 validation */
function getAdminMonth(projectname){
   $.ajax({
      type: "post",
      url : "GetAdminMonth.do",
      dataType : 'json',
      data: {
         project : projectname
      },

      success : function(data) {      
         
         var adminmonth =0;
         var newdate; 
         var d = new Date();
         var date = leadingZeros(d.getFullYear(), 4) + '-' +
         leadingZeros(d.getMonth() + 1, 2) + '-' +
         leadingZeros(d.getDate(), 2);

         adminmonth = data.result[0].admin_month; //db에서 admin_month 가져옴 
         //alert("이번달날짜:"+date);
         
         if((d.getMonth()+adminmonth+1)>12) //이번달 + admin_month 가 12월을 초과하면 
         {
            newdate = leadingZeros((d.getFullYear()+1), 4) + '-' +
            leadingZeros((d.getMonth()+adminmonth-12) + 1, 2) + '-' +
            leadingZeros(d.getDate(), 2) + ' '; //유의해야할게 1월은 javascript에서 0으로 표시 ㅎ... 
         }
         else 
         {
            newdate = leadingZeros(d.getFullYear(), 4) + '-' +
            leadingZeros((d.getMonth()+adminmonth) + 1, 2) + '-' +
            leadingZeros(d.getDate(), 2) + ' ';
         }

         //alert("달력에서고른날짜"+document.myForm.date.value);
         if (document.myForm.date.value > newdate) {
            alert("관리자가 지정한 날짜보다 초과하였습니다. 날짜를 다시 선택해주세요.");
            $('input[name="date"]').val("");    
         }
         else if(document.myForm.date.value < date){
            $('input[name="date"]').val("");
            alert("오늘보다 이전 날짜는 예약이 되지 않습니다. 날짜를 다시 선택해주세요.");
         }

         else {
            $('input[name="confer_nm"]').val("");
         }

      },
      error : function() {
         console.log("error");
      }
   });
   return adminmonth; 
}

   /*adminMonthValidation()*/
   

   function leadingZeros(n, digits) {
       var zero = '';
       n = n.toString();

       if (n.length < digits) {
           for (var i = 0; i < digits - n.length; i++)
               zero += '0';
       }
       return zero + n;
   }
   
   

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
                  
                  $('#meetings').append("<div class='line' style='top:0px; left:"+left+"px;'></div>");

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
          for (var i = 0; i <= 10; i++) {
             /*if(time == 720) {
                time += 60;
                continue;
             }*/
             if(i!=10){
             $('#timeDiv').append("<div class='time' style='top:"+top+"px; '>"+minToTime(time)+"</div>");
             }
             //가로선긋기
             $('#meetings').append("<div class='line2' style='top:"+top+"px; height:1px; width:"+(document.getElementById('schedule').offsetWidth-70)+"px;'</div>"); //시간별 가로라인

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
               var top, top2=0, height, j=0, bottom;
               var start, end, alphaT;
               var confName = "";
               var confIdx = 0;

               $('#meetings').empty();      

               conference = [];
               var check = [];
               for (var i = 0; i < data.confers.length; i++) {
                  conference.push(data.confers[i].confname);
                  check.push(false);
               }

               for (var i = 0; i < 10; i++) {
                  $('#meetings').append("<div class='line2' style='top: "+top2+"px; height:1px; left:0px; width:"+(document.getElementById('schedule').offsetWidth)+"px; </div>"); //시간별 가로라인
                  top2 += 40;
               }
               
               for (var i = 0; i < data.confers.length; i++) {
                  var flag = false;
                  bottom = 0;
                  
                  if(j < data.meetings.length){
                     confName = data.meetings[j].confer_nm;
                     confIdx = conference.indexOf(confName);
                     left = 70 + width * confIdx;
                     check[conference.indexOf(confName)] = true;
                  }  
                  
                  while (j < data.meetings.length && data.meetings[j].confer_nm == confName) {
                     flag = true;
                     
                     top = (timeToMin(data.meetings[j].start) - 540) / 30 * 20;
                     height = (timeToMin(data.meetings[j].end) - timeToMin(data.meetings[j].start)) / 30 * 20;

                     // 예약되어있지 않는 공간
                     start = bottom/20*30+540;
                     end = top/20*30+540;
                     alphaT = 0;
                     for(var k = start; k < end; k += 30){
                        $('#meetings').append("<div id='empty' class='empty'"
                           + "style='top:"+(bottom+alphaT)+"px; left:"+left+"px; width:"+width+"px; height:20px;'"
                           + "onClick='reserve("+confIdx+", "+start+", "+end+", "+k+");'></div>");
                        alphaT += 20;
                     }

                     bottom = top + height;

                     j++;
                  }

                  if(flag){
                     // 예약되어있지 않는 공간
                     start = bottom/20*30+540;
                     end = 360/20*30+540;
                     alphaT = 0;
                     for(var k = start; k < end; k += 30){
                        $('#meetings').append("<div id='empty' class='empty'"
                           + "style='top:"+(bottom+alphaT)+"px; left:"+left+"px; width:"+width+"px; height:20px;'"
                           + "onClick='reserve("+confIdx+", "+start+", "+end+", "+k+");'></div>");
                        alphaT += 20;
                     }
                     
                     $('#meetings').append("<div class='line' style='top:0px; left:"+left+"px;'></div>"); //회의실별 세로라인 
                  }
               }
               
               for(var i = 0; i < data.confers.length; i++){
                  if(check[i] == false){
                     left = 70 + width * i;
                     top = 0;
                     for(var k = 540; k<1080; k+=30){
                        $('#meetings').append("<div id='empty' class='empty'"
                              + "style='top:"+top+"px; left:"+left+"px; width:"+width+"px; height:20px;'"
                              + "onClick='reserve("+i+", "+540+", "+1080+", "+k+");'></div>");
                        top += 20;
                     }
                     //회의실별 세로라인
                     $('#meetings').append("<div class='line' style='top:0px; left:"+left+"px;'></div>");
                  }
               }
               $('#meetings').append("<div class='line' style='top:0px; left:"+(left+width)+"px;'></div>");
               
               displayTime();
               
               // 예약된 공간
               for(var j = 0; j < data.meetings.length; j++) {
            	   confName = data.meetings[j].confer_nm;
                   confIdx = conference.indexOf(confName);
                   left = 70 + width * confIdx;
                   
                   top = (timeToMin(data.meetings[j].start) - 540) / 30 * 20;
                   height = (timeToMin(data.meetings[j].end) - timeToMin(data.meetings[j].start)) / 30 * 20;

                   var color = data.meetings[j].color;
                   if(height <= 20)//30분짜리 예약일때만 (글씨가 작아지므로)
                      $('#meetings').append(
                           
                            "<div id='reserved' class='meeting'"
                            + "style='background-color:"+color+"; top:"+top+"px; left:"+left+"px; width:"+width+"px; height:"+height+"px;'"
                            + "onClick='reserveInfo("+data.meetings[j].seq+");'>"+data.meetings[j].title+"</div>");

                   else
                   $('#meetings').append(
                        
                         "<div id='reserved' class='meeting'"
                         + "style='background-color:"+color+"; top:"+top+"px; left:"+left+"px; width:"+width+"px; height:"+height+"px; padding-top:"+(height-15)/2+"px'"
                         + "onClick='reserveInfo("+data.meetings[j].seq+");'>"+data.meetings[j].title+"</div>");
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
               $('#rsv_correct_pw').empty();
               $('#rsv_repeat_seq').empty();

               for(var i=0; i<data.result.length; i++) {
                  $('input[name="name"]').val(data.result[i].name);
                  $('input[name="phone"]').val(data.result[i].phone);
                  $('input[name="email"]').val(data.result[i].email);
                  $('input[name="date"]').val(data.result[i].date);
                  $('input[name="confer_nm"]').val(data.result[i].confer_nm);
                  $('input[name="title"]').val(data.result[i].title);
                  $('input[name="rsv_seq"]').val(""+seq);
                  $('input[name="rsv_correct_pw"]').val(data.result[i].password);
                  $('input[name="rsv_repeat_seq"]').val(data.result[i].repeat_seq);
                  $("#color").val(data.result[i].color).attr("selected", "selected");
                  
                  if(data.result[i].repeat_seq == 0){
                	  $('#adminRsvButton').empty();
                	  $("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Modify(0);'>수정</button>");
                	  $("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Delete(0);'>삭제</button>");
                  }else{
                	  $('#adminRsvButton').empty();
                	  $("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Modify(0);'>수정</button>");
                	  $("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Delete(0);'>삭제</button>");
                	  $("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Modify(1);'>전체수정</button>");
                	  $("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Delete(1);'>전체삭제</button>");
                  }
               }

               timeSelectListAll(data.result[0].start_time, data.result[0].end_time);
            },
            error : function() {
               console.log("error");
            }
         });
      }

      // 빈 회의 시간표를 클릭시 예약 할 수 있도록 해준다.
      function reserve(conf_idx, start, end, selectT){
         //alert(minToTime(start) + " - " + minToTime(end));
         //alert(minToTime(start)+"/"+minToTime(end)+"/"+minToTime(selectT));
         
         $("#registerInfo").hide();
         $("#register").show();

         timeSelectList(start, end, selectT);

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
         $("#color").val("#00599D").attr("selected", "selected");
      }

      function timeSelectList(start, end, selectT){
         $('#start_time').empty();
         for(var i=start; i<end; i+=30) {
            $('#start_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
         }
         $("#start_time").val(minToStr(selectT)).attr("selected", "selected");

         $('#end_time').empty();
         for(var i=start+30; i<=end; i+=30) {
            $('#end_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
         }
         var endTime;
         if(selectT + 60 <= 1080)
        	 endTime = selectT + 60;
         else
        	 endTime = 1080;
         
         $("#end_time").val(minToStr(endTime)).attr("selected", "selected");
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
