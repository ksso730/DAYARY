$('#community_write_btn').off().on('click', function () {//게시판(타임라인)글 쓰기 by choiseongjun
	const board_group_id = $('#board_group_id').attr("data-boardGroupNo");
	let communityBoard = {};
	communityBoard.memo=$('#memo').val();
	$.ajax({
        url:'/community/timeLine/write',
        type:'POST',
		contentType: 'application/json; charset=UTF-8',
        dataType:'json',
        data: JSON.stringify(communityBoard),
        success:function(data){
			if(data.code==1){
				alert(data.message);
				location.href='/community/timeLine/';
			}else{
				alert(data.message)
			}
		},
		error:function(xhr,error){
			
		}
    });
	
});
function fn_writeReply(id,it){
	const board_group_id = $('#board_group_id').attr("data-boardGroupNo");
	let communityBoardReply={};
	communityBoardReply.memo=it.parentElement.previousElementSibling.value;
	$.ajax({
        url:'/community/board/'+board_group_id+"/reply/"+id,
        type:'POST',
		contentType: 'application/json; charset=UTF-8',
        dataType:'json',
        data: JSON.stringify(communityBoardReply),
        success:function(data){
			if(data.code==1){
				alert(data.message);
				it.parentElement.previousElementSibling.value="";
				fn_getReply(id);
				
			}else{
				alert(data.message)
			}
		},
		error:function(xhr,error){
			
		}
    });
	
}
function fn_getReply(id){
	 
	$.ajax({
		url:'/community/timeLine/replyList/'+id,
		type:'POST',
		contentType: 'application/json; charset=UTF-8',
		dataType:'json',
		success:function(data){
			if(data.list.length>0){
				var list=data.list;
				var html="";
				for(var i=0;i<list.length;i++){
					html+="<div>"+list[i].memo+" 작성자:"+list[i].people.name+list[0].createdAt.replace('T',' ')+"</div>"
				}
				$("#commentbox_"+id).html(html);
			}else{
				alert("조회오류")
			}
		},
		error:function(xhr,error){
			
		}
	});
}

$(document).ready(function(){//게시판(타임라인)글 삭제 by choiseongjun
	  $("#community_delete_btn").click(function(){
		  	// const board_group_no = $('#board_group_no').attr("data-boardGroupNo");

		  	if(confirm('정말 삭제하시겠습니까?')) {
				const boardId = $('#boardId').attr("data-boardId");
				// console.log(timeLineListNo);

				$.ajax({
					url: '/community/board/delete/' + boardId,
					type: 'DELETE',
					contentType: 'application/json; charset=UTF-8',
					dataType: 'json',
					success: function (data) {
						if (data.code == 1) {
							alert(data.message);
							location.href = '/community/timeLine';
						} else {
							alert(data.message)
						}
					},
					error: function (xhr, error) {

					}
				});
			}
	  });
	  
	  
	});