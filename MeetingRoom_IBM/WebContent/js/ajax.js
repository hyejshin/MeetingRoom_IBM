function btn(value)
{
	//alert(value);
	//callAjax(value);
}

/* 이름을 입력하면 자동으로 나머지 필드(폰번호,이메일,사이트)가 자동으로 입력되게 하는 Ajax */
function AutoFillElements(value)
{	
	 $.ajax({
	        type: "post",
	        url : "auto_fill.do",
	        dataType : 'json',
	        data: {
	        	id : value
	        },
	       
	        success : function(data) {
	        	$('#phone').empty();
	        	$('#email').empty();
	        		         	
	        	for(var i=0; i<data.result.length; i++) {
	        		$('input[name="phone"]').val(data.result[i].phone);
	        		$('input[name="email"]').val(data.result[i].email);
	        		
	        		/*$('#phone').value=data.result[i].phone;  
	        		$('#email').value=data.result[i].email;
	        		$('#site').value=data.result[i].site;*/
				}
	        	        	
	        },
	        error : function() {
	        	console.log("error");
	        }
	});
}

/* 글자를 하나만 입력해도 DB를 검색하여 유사한 단어를 드롭박스에 나타내주는 Ajax */
$(document).ready(function() {
	$(function() {
		$("#term").autocomplete(
				{
			source : function(request, response) {
				$.ajax({
					
					url : "test_auto.do",
					type : "POST",
					minLength: 1,
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(data) {
											
						response(data);
						
					},
					
					
				});
			},
				select : function(event,ui) {
					AutoFillElements(ui.item.value);
					
				},
				focus: function( event, ui ) {
					return false; 
				}					
		});
	});
});


/* 이름(라디오버튼)에 따라 정보를 뿌려주는 Ajax */
function callAjax(value){
	    
	  $.ajax({
	        type: "post",
	        url : "test.do",
	        dataType : 'json',
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

