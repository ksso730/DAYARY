
$('#moimMake_btn').off().on('click',function(){

	let moim = {};
	moim.title=$('#moimTitle').val();
	moim.peopleLimit=$('#peopleLimit').val();
	moim.categoryNo=$('#categoryNo').val();
	moim.intro=$('#moimIntro').val();

	$.ajax({
		url:'/moimMake',
		type:'POST',
		contentType: 'application/json; charset=UTF-8',
		dataType:'json',
		data: JSON.stringify(moim),
		success:function(data){
//			if(data.code==1){
//				alert(data.message);
//				location.href='/signinView';
//			}else{
//				alert(data.message);
//			}
		},
		error:function(){

		}

	});
});