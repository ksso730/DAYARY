$('#signup_btn').off().on('click', function () {

    var moimNo = $('#moimNo').attr("data-moimNo");
    console.log(moimNo);
    $.ajax({
    	url : '/moimParticipant/'+moimNo, 
    	type : "post",   
    	processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
        contentType: false,
    	contentType: 'application/json; charset=UTF-8',
    	dataType   : 'json',  
    	data	   : JSON.stringify(moimNo),
        success:function(data){
        	if(data.code==1){
        		console.log("success callback data");
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