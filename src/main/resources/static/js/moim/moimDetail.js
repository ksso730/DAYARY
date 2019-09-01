$('#signup_btn').off().on('click', function () {

    var moimNo = $('#moimNo').attr("data-moimNo");
    var peopleNo = $('#peopleNo').attr("data-peopleNo");
    console.log(peopleNo)
    
    $.ajax({
    	type : "POST",  
    	url : '/moimParticipant/'+peopleNo,  
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