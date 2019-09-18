
$('#signin_btn').off().on('click',function(){

	let people = {};
	people.email=$('#email').val();
	people.password=$('#password').val();

	$.ajax({
		url:'/signin',
		type:'POST',
		contentType: 'application/json; charset=UTF-8',
		dataType:'json',
		data: JSON.stringify(people),
		success:function(data){
			if(data.code==1){
				alert(data.message);
				location.href='/';
			}
		},
		error:function(xhr,error){
			if(xhr.status==401){
				alert('아이디나 비밀번호가  틀렸습니다');
			}
		}

	});
});

//Login Enter key 적용 by choiseongjun
var input = document.getElementById("password");
input.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
   event.preventDefault();
   document.getElementById("signin_btn").click();
  }
});
//Login Enter key 적용