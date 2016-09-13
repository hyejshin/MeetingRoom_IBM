function btn(value)
{
	//alert(value);
	//callAjax(value);
}

/* �̸��� �Է��ϸ� �ڵ����� ������ �ʵ�(����ȣ,�̸���,����Ʈ)�� �ڵ����� �Էµǰ� �ϴ� Ajax */
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

/* ���ڸ� �ϳ��� �Է��ص� DB�� �˻��Ͽ� ������ �ܾ ��ӹڽ��� ��Ÿ���ִ� Ajax */
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


/* �̸�(������ư)�� ���� ������ �ѷ��ִ� Ajax */
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

