$('#signup_btn').off().on('click', function () {

    var moimNo = $('#moimNo').attr("data-moimNo");
    var peopleNo = $('#peopleNo').attr("data-peopleNo");
    console.log(peopleNo)
    
    $.ajax({
    	type : "POST",  
    	url : '/moimParticipant/'+moimNo,  
    	dataType : 'json',  
        success:function(data){
            
        },
        error:function(e){

        }
    });
});