function meetupPeoplejoinCheck() {
  console.log("페이지 로딩 완료");

}


$('[name="grantpeople_btn"]').on('click', function () {//가입승인 대기중인사람 승인하기 by choiseongjun 2019-10-07
		 var moimNo = $('#moimNo').attr("data-moimNo");
	     let tempdata = {};
	     tempdata.no1= $(this).val();
	     tempdata.no2= $('#moimNo').attr("data-moimNo");
	    
	     console.log($(this).val())
	     

	    $.ajax({
	          url : '/moimParticipant/moimgrantjoinPeople', 
	          type : "post",   
	          contentType: 'application/json; charset=UTF-8',
	  		  dataType:'json',
	  		  data: JSON.stringify(tempdata),
	 		  success:function(data){
	 			if(data.code==1){
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
$('[name="banpeople_btn"]').on('click', function () {//회원 강퇴하기 by choiseongjun 2019-10-01
   var moimNo = $('#moimNo').attr("data-moimNo");

     let tempdata = {};
     tempdata.no1= $(this).val();
     tempdata.no2= $('#moimNo').attr("data-moimNo");
     

    $.ajax({
          url : '/moimParticipant/banjoinedPeople', 
          type : "post",   
          contentType: 'application/json; charset=UTF-8',
  		  dataType:'json',
  		  data: JSON.stringify(tempdata),
 		  success:function(data){
 			if(data.code==1){
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


$('#signup_btn').off().on('click', function () {//스터디 가입하기 by choiseongjun 2019-09-20
	
    var moimNo = $('#moimNo').attr("data-moimNo");
    var joinCondition = $('#joinCondition').attr("data-joinCondition");
    $.ajax({
       url : '/moimParticipant/'+moimNo, 
       type : "post",   
       processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
        contentType: false,
       contentType: 'application/json; charset=UTF-8',
       dataType   : 'json',  
       data      : JSON.stringify(moimNo),
        success:function(data){
           if(data.code==1){
             //  console.log("success callback data");
             //  sendEcho(moimNo);
               alert(data.message);
               location.href='/moimlistView/moimdetailView/'+moimNo;
        	   
        		var peopleEmail = $('#sessionUserEmail').attr("data-sessionUserEmail");
            	var moimTitle = $('#moimTitle').attr("data-moimTitle");
            	
            	console.log("moimPeopleList>>", moimPeopleList);
        	  
       		//textarea 값이 없을 땐 return;
       		//if()
            var moimPeopleListstr=moimPeopleList.join(',');
            console.log(moimPeopleListstr);
//       		if(!isStomp && socket.readyState!==1) return;
//       		let peopleId=$('#peopleId').attr("data-peopleId");
//       		let msg=$('#inputmsg').val();
//       		let moimNo=$('#moimNo').attr("data-moimNo");
//       		let peopleEmail=$('#email').attr("data-email");
//       		if(isStomp){
//       			//socket.send('/moimjoinNoti',{},JSON.stringify({moimNo:moimNo, peopleEmail: peopleEmail, moimTitle: moimTitle,moimPeopleList:moimPeopleList}));
//       			socket.send('/moimjoinNoti',{},JSON.stringify({ moimNo, peopleEmail, moimTitle,moimPeopleListstr}));
//       		}else{
//       			socket.send(msg);
//       		}
       		$('#inputmsg').val('');
         }else{
            alert(data.message);
         }
        },
        error:function(e){

        }
    });
});
$('#signup_btnY').off().on('click', function () {//스터디 가입하기 by choiseongjun 2019-09-20

    var moimNo = $('#moimNo').attr("data-moimNo");
    $.ajax({
       url : '/moimParticipantY/'+moimNo, 
       type : "post",   
       processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
        contentType: false,
       contentType: 'application/json; charset=UTF-8',
       dataType   : 'json',  
       data      : JSON.stringify(moimNo),
        success:function(data){
           if(data.code==1){
               console.log("success callback data");
               alert(data.message);
               location.href='/moimlistView/moimdetailView/'+moimNo;
               sendEcho(moimNo);
         }else{
            alert(data.message);
         }
        },
        error:function(e){

        }
    });
});

// 가입 알림 보내기 by yn 2019/11/15
//function sendEcho(moimId){
//    	console.log("reply.js::socket>>", socket);
//    	console.log('여기타나??');
//    	var sessionEmail = $('#sessionUserEmail').attr("data-sessionUserEmail");
//    	var moimTitle = $('#moimTitle').attr("data-moimTitle");
//		if (socket) {
//			// websocket에 보내기 (regist,모임id,모임명,가입유저명,모임가입자들)
//			console.log("moimPeopleList>>", moimPeopleList);
//			let socketMsg = "regist," + moimId + "," + moimTitle + "," + sessionEmail + "," + moimPeopleList; 
//			console.log("sssssssmsg>>", socketMsg);
//			socket.send(socketMsg);
//		}
//}

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
          data      : JSON.stringify(moimPeopleNo),
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

//스터디 삭제 by choiseongjun 2019-09-20
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


// 모임 비공개 전환 by hyozkim 2020-01-14
$("#moimClosedBtn").click(function() {
    var moimNo = $('#moimNo').attr("data-moimNo");

    $.ajax({
        url:'/moimUpdateClosed/'+moimNo,
        type:'PUT',
        contentType: 'application/json; charset=UTF-8',
        dataType:'json',
        success:function(data){
            if(data.code == 1){
                alert(data.message);
                location.href='/moimlistView';

            }else{
                alert(data.message);
            }
        },
        error:function(xhr,error) {

        }
    });

});

var moimPeopleList;
$(document).ready(function(){
	
	var moimNo = $('#moimNo').attr("data-moimNo");
	$.ajax({
			url : '/moimParticipant/searchJoinedPeople/'+moimNo, 
			type:'get',
			contentType: 'application/json; charset=UTF-8',
	        enctype: 'multipart/form-data',
	        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        contentType: false,
	        dataType:'json',
	        cache: false,
	        mimeType:"multipart/form-data",
	        success:function(data){
	         if(data.code==1){
	        	 moimPeopleList = data.moimPeople;
	            console.log(moimPeopleList);
	         }else{
	            alert(data.message)
	         }
      },
      error:function(xhr,error){
         
      }
    });
});

