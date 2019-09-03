$('#signup_btn').off().on('click', function () {

    var moimNo = $('#moimNo').attr("data-moimNo");
    console.log(moimNo);
    $.ajax({
    	url : '/moimParticipant/'+moimNo, 
    	type : "POST",   
    	contentType: 'application/json; charset=UTF-8',
    	dataType : 'json',  
        success:function(data){
        	if(data.code==1){
				alert(data.message);
				location.href='/';
			}else{
				alert(data.message);
			}
        },
        error:function(e){

        }
    });
});