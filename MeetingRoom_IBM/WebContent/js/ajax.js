function btn(value)
{
	var param = $("input[type=radio][id=rd]:checked").val();
	//alert(value);
	callAjax(value);
}

$(document).ready(function() {
	$(function() {
		$("#term").autocomplete({
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
						//alert(data);
					},
					
			        select: function( event, ui ) {
			            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
			        	alert("asdasd");
			        }
				});
			}
		});
	});
});


function callAjax(value){
	    
	  $.ajax({
	        type: "post",
	        url : "test.do",
	        // dataType : 'json',
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

/*var dtlist = document.getElementById('dtlist');
var input = document.getElementById('term');

$(function() {
    $( "#term" ).autocomplete({
      
      source: function( request, response ){
    $.ajax({
        type: 'post',
        url: "test_auto.do",
        dataType: "json",
        //request.term = $("#tags").val(),
        data: { id : request.term },
        success : function(data){
        	 Third Method 
        	$('#dtlist').empty();
        	
        	for(var i=0; i<data.result.length; i++) {
        		
        		$('#dtlist').append("<option value="+data.result[i].name+">");
        		
        							 
  			  }
        	
           First Method 
          //alert(data.result);
          /*response(
            $.map(data, function(item) {
                        return { label: item.data,
                        		 value: item.data 
                        }
           })//map
          );//response
          //var jsonOptions = JSON.parse(requet.term);
          
          			//Second Method 
          data.forEach(function(item) {
        	  var option = document.createElement('option');
        	  option.value = item.name;
        	  dtlist.appendChild(option);
          });
          for(var i=0;i<data.result.length;i++) {
        	  var option = document.createElement('option');
        	  option.value = data.result[i].name;
        	  dtlist.appendChild(option);
          }
    	
        }//success
    });//
    },
    //source
      
    //조회를 위한 최소글자수
        minLength: 1,
        select: function( event, ui ) {
            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
        	
        }
    }); //autocomplete
  });//function()*/