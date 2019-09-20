$('#community_write_btn').off().on('click', function () {
	const board_group_no = $('#board_group_no').attr("data-boardGroupNo");
	
	let communityBoard = {};
	communityBoard.memo=$('#memo').val();
	
	$.ajax({
        url:'/community/communityList/'+board_group_no+'/communityWrite',
        type:'POST',
		contentType: 'application/json; charset=UTF-8',
        dataType:'json',
        data: JSON.stringify(communityBoard),
        success:function(data){
			if(data.code==1){
				alert(data.message);
				location.href='/community/communityList/'+board_group_no;
			}else{
				alert(data.message)
			}
		},
		error:function(xhr,error){
			
		}
    });
	
});


$(document).ready(function(){
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
						location.href='/community/communityList/'+board_group_no;
					}else{
						alert(data.message)
					}
				},
				error:function(xhr,error){
					
				}
		    });
	  });
	});