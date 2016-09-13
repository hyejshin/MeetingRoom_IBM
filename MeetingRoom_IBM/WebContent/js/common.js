/* 사이트,회의실정보,날짜 인수를 모두 받아와서 Ajax 인수에 넘겨주는 함수 */
function allValues()
{
	var site = $('#site').val();
	var date = $('#date').val();
	var confer_nm = $('#confer_nm').val();
	
	callAjaxListByValues(site,date,confer_nm);
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

/* 달력값이 바뀔때마다 포커싱되고 달력값을 인자로 전달하는 Ajax 호출 */


/* 회의실 이름,날짜를 Ajax 인수로 넘김 */
function callAjaxListByValues(site,date,confer_nm)
{
	
	
	$.ajax({
        type: "post",
        url : "SelectByCondition.do",
        dataType : 'json',
        data: {
        	id1 : site,
        	id2 : date,
        	id3 : confer_nm
        },
       
        success : function(data) {
        	
        },
       
        error : function() {
        	console.log("error");
        }
	});
}





