$('#community_write_btn').off().on('click', function () {//게시판(타임라인)글 쓰기 by choiseongjun
	const board_group_id = $('#board_group_id').attr("data-boardGroupNo");
	
	let communityBoard = {};
	communityBoard.memo=$('#memo').val();
	$.ajax({
        url:'/community/communityList/'+board_group_id+'/communityWrite',
        type:'POST',
		contentType: 'application/json; charset=UTF-8',
        dataType:'json',
        data: JSON.stringify(communityBoard),
        success:function(data){
			if(data.code==1){
				alert(data.message);
				location.href='/community/communityList/'+board_group_id;
			}else{
				alert(data.message)
			}
		},
		error:function(xhr,error){
			
		}
    });
	
});


$(document).ready(function(){//게시판(타임라인)글 삭제 by choiseongjun
	  $("#community_delete_btn").click(function(){
		  	const board_group_no = $('#board_group_no').attr("data-boardGroupNo");
			const timeLineListNo = $('#timeLineListNo').attr("data-timeLineListNo");
			console.log(timeLineListNo);
			
			$.ajax({
		        url:'/community/communityList/'+board_group_no+'/communityDelete/'+timeLineListNo,
		        type:'DELETE',
				contentType: 'application/json; charset=UTF-8',
		        dataType:'json',
		        success:function(data){
					if(data.code==1){
						alert(data.message);
						location.href='/community/communityList/1';
					}else{
						alert(data.message)
					}
				},
				error:function(xhr,error){
					
				}
		    });
	  });
	});