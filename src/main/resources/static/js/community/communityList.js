$('#community_write_btn').off().on('click', function () {
	var board_group_no = $('#board_group_no').attr("data-boardGroupNo");
	
	let communityBoard = {};
	communityBoard.memo=$('#memo').val();
	
	 console.log('on click'+board_group_no);
	$.ajax({
        url:'/community/communityList/'+board_group_no+'/communityWrite',
        type:'post',
        enctype: 'multipart/form-data',
        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
		contentType: 'application/json; charset=UTF-8',
        dataType:'json',
        cache: false,
        data: JSON.stringify(communityBoard),
        success:function(data){
            if(data.code==1){
                alert(data.message);
                console.log('데이터 테스트트트');
                location.href='/';
            }else{
                alert(data.message);
            }
        },
        error:function(e){

        }
    });
	
});