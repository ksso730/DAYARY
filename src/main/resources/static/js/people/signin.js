
$('#signin_btn').off().on('click',function(){

	let people = {};
	people.id=$('#id').val();
	people.password=$('#password').val();

	$.ajax({
		url:'/signin',
		type:'POST',
		contentType: 'application/json; charset=UTF-8',
		dataType:'json',
		data: JSON.stringify(people),
		success:function(data){
			if(data.code==1){
				location.href='/';
			}else{
				alert(data.message);
			}
		},
		error:function(){

		}

	});
});