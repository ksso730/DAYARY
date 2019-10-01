function myPeopleCount() {
  console.log("페이지 로딩 완료");

}
$('#banpeople_btn').off().on('click', function () {//회원 강퇴하기 by choiseongjun 2019-10-01
	  var myValue = $(this).html();
	  alert(myValue); 
	var moimNo = $('#moimNo').attr("data-moimNo");
	var peopleIdKind = $('#peopleIdKind').text();
	moimNo*=1;
	  console.log(moimNo);
	  console.log(peopleIdKind);
//	 $.ajax({
//	    	url : '/moimParticipant/banjoinedPeople/'+moimNo, 
//	    	type : "DELETE",   
//	    	processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
//	        contentType: false,
//	        contentType: 'application/json; charset=UTF-8',
//	    	dataType   : 'json',  
//	    	data	   : JSON.stringify(moimPeopleNo),
//	        success:function(data){
//	        	if(data.code==1){
//	        		console.log("success callback data");
//	        		 alert(data.message);
//	                 location.href='/moimlistView';
//				}else{
//					alert(data.message);
//				}
//	        },
//	        error:function(e){
//
//	        }
//	    });
	
});
$('#signup_btn').off().on('click', function () {//스터디 가입하기 by choiseongjun 2019-09-20

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
                 location.href='/moimlistView/moimdetailView/'+moimNo;
			}else{
				alert(data.message);
			}
        },
        error:function(e){

        }
    });
});
$('#withdraw_btn').off().on('click', function () {//스터디 탈퇴하기 by choiseongjun 2019-09-20

	  var moimPeopleNo = $('#moimPeopleNo').attr("data-moimPeopleNo");
	  moimPeopleNo*=1;
	  console.log(moimPeopleNo);
	 $.ajax({
	    	url : '/moimParticipant/deletejoinedPeople/'+moimPeopleNo, 
	    	type : "DELETE",   
	    	processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        contentType: false,
	        contentType: 'application/json; charset=UTF-8',
	    	dataType   : 'json',  
	    	data	   : JSON.stringify(moimPeopleNo),
	        success:function(data){
	        	if(data.code==1){
	        		console.log("success callback data");
	        		 alert(data.message);
	                 location.href='/moimlistView';
				}else{
					alert(data.message);
				}
	        },
	        error:function(e){

	        }
	    });
	
});

$(document).ready(function(){//스터디 삭제 by choiseongjun 2019-09-20
	  $("#moim_delete_btn").click(function(){
		  var moimNo = $('#moimNo').attr("data-moimNo");
		  
			$.ajax({
		        url:'/moimDetail/moimDeleteOne/'+moimNo,
		        type:'DELETE',
				contentType: 'application/json; charset=UTF-8',
		        dataType:'json',
		        success:function(data){
					if(data.code==1){
						alert(data.message);
						location.href='/moimlistView';
					}else{
						alert(data.message)
					}
				},
				error:function(xhr,error){
					
				}
		    });
	  });
	});