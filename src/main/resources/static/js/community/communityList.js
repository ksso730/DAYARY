$('#community_write_btn').off().on('click', function () {
	var board_group_no = $('#board_group_no').attr("data-boardGroupNo");
	
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
				location.href='/';
			}else{
				alert(data.message)
			}
		},
		error:function(xhr,error){
			
		}
    });
	
});