
$('#signup_btn').off().on('click',function(){

	let people = {};
	people.id=$('#id').val();
	people.name=$('#name').val();
	people.password=$('#password').val();
	people.sex=$('#sex').val();
	people.birth=$('#birth').val();

	$.ajax({
		url:'/signup',
		type:'POST',
		contentType: 'application/json; charset=UTF-8',
		dataType:'json',
		data: JSON.stringify(people),
		success:function(data){
			if(data.code==1){
				alert(data.message);
				location.href='/signinView';
			}else{
				alert(data.message);
			}
		},
		error:function(){

		}

	});
});