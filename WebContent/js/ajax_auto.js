
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
              $('#name').empty();
              $('#email').empty();
                             
              for(var i=0; i<data.result.length; i++) {
                 
                 $('input[name="name"]').val(data.result[i].name);
                 $('input[name="email"]').val(data.result[i].email);
                 
            }
                         
           },
           error : function() {
              console.log("error");
           }
   });
}


$(document).ready(function() {
   $(function() {
      $("#phone").autocomplete(
            {
         source : function(request, response) {
            $.ajax({
               
               url : "auto_fill2.do",
               type : "POST",
               minLength: 1,
               data : {
                  phone : request.term
               },
               dataType : "json",
               
               success : function(data) {
                  response(data)   
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

