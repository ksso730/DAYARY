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
			
				var list=data.list;
				var html="";
				for(var i=0;i<list.length;i++){
					html+=' <div class="box-comment"> <div class="user"><img src="https://bootdey.com/img/Content/avatar/avatar6.png"></div>';
					html+=' <div class="comment-text"> <span class="username">'+list[i].people.name;
				    html+=' <span class="text-muted pull-right">'+list[0].createdAt.replace('T',' ')+'</span></span>';
					html+=list[i].memo;
					if(list[i].people.id==$("#peopleId").attr("data")){
						html+='<button onclick="fn_modifyForm('+list[i].id+')" class="btn btn-xs btn-link">수정</button>';
						html+='<button onclick="fn_deleteReply('+list[i].id+','+id+')" class="btn btn-xs btn-link">삭제</button>';
					}
					html+='</div>';
					
					html+=' <div class="input" id="modifybox_'+list[i].id+'"  style="display: none">';
					html+='<div class="input-group"><input type="text" class="form-control rounded-corner" value="'+list[i].memo+'">';
					html+=' <span class="input-group-btn p-l-10">'
                    html+='<button class="btn btn-primary f-s-12 rounded-corner" type="button" onclick="fn_modifyReply('+list[i].id+',this,'+id+')">저장</button>';
					html+='</span></div></div><br>'
				}
				$("#commentbox_"+id).html(html);
			
		},
		error:function(xhr,error){
			
		}
	});
}

function fn_deleteReply(id,boardId){
	if(confirm('정말 삭제하시겠습니까?')){
		$.ajax({
			url:'/community/board/reply/delete/'+id,
			type:'DELETE',
			contentType: 'application/json; charset=UTF-8',
			dataType:'json',
			success:function(data){
				if(data.code==1){
					fn_getReply(boardId);
				}else{
					alert("조회오류")
				}
			},
			error:function(xhr,error){
				
			}
		});
	}
}
function fn_modifyReply(id,it,boardId){
	let communityBoardReply={};
		communityBoardReply.id=id;
		communityBoardReply.memo=it.parentElement.previousElementSibling.value;
	$.ajax({
        url:'/community/board/modifyReply',
        type:'POST',
		contentType: 'application/json; charset=UTF-8',
        dataType:'json',
        data: JSON.stringify(communityBoardReply),
        success:function(data){
			if(data.code==1){
				alert(data.message);
				it.parentElement.previousElementSibling.value="";
				fn_getReply(boardId);
				
			}else{
				alert(data.message)
			}
		},
		error:function(xhr,error){
			
		}
	});		
}

function fn_modifyForm(id){
	$("#modifybox_"+id).toggle();
	$("#replybox_"+id).toggle();
}
$(document).ready(function(){

	//게시판(타임라인)글 삭제 by choiseongjun
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