  var category;

$('#moimMake_btn').off().on('click',function(){

   let moim = {};
   moim.title=$('#title').val();
   moim.peopleLimit=$('#peopleLimit').val();
   moim.categoryNo=$('#categoryNo').val();
   moim.intro=$('#intro').val();
   
   $.ajax({
      url:'/moimMake',
      type:'POST',
      contentType: 'application/json; charset=UTF-8',
      dataType:'json',
      data: JSON.stringify(moim),
      success:function(data){
    	  if(data.code==1){
				alert(data.message);
				location.href='/';
			}else{
				alert(data.message);
			}
      },
      error:function(){
    	  alert('저장 실패');
      }

   });
});


