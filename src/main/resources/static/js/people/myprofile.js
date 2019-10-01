$(document).ready(function(){//회원 탈퇴 by choiseongjun 2019-10-01
	  $("#people_delete_btn").click(function(){
		  var peopleId = $('#peopleId').attr("data-peopleId");
		  
			$.ajax({
		        url:'/myprofileView/deletePeople/'+peopleId,
		        type:'DELETE',
				contentType: 'application/json; charset=UTF-8',
		        dataType:'json',
		        success:function(data){
					if(data.code==1){
						alert(data.message);
						location.href='/logout';
					}else{
						alert(data.message)
					}
				},
				error:function(xhr,error){
					
				}
		    });
	  });
	});