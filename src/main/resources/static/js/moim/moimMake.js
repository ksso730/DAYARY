$('#moimMake_btn').off().on('click',function(){

   var obj = document.getElementById('categorybox');

   let moim = {};
   moim.title=$('#title').val();
   moim.peopleLimit=$('#peopleLimit').val();
   moim.intro=$('#intro').val();
   
   let category = {};
   category.subject = obj.options[obj.selectedIndex].text;
   moim.category = category;

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

$('#peopleLimit').keyup(function () {
	numberKey();
	
});

function numberKey() {

 if(check_key() != 1 ) {
  event.returnValue = false;   
  alert("숫자만 입력할 수 있습니다.");
  document.getElementById('peopleLimit').value = '';
  return;
 }
}

function check_key() {
	 var char_ASCII = event.keyCode;
	                
	  //숫자
	 if (char_ASCII >= 48 && char_ASCII <= 57 )
	   return 1;
	 //영어
	 else if ((char_ASCII>=65 && char_ASCII<=90) || (char_ASCII>=97 && char_ASCII<=122))
	    return 2;
	 //특수기호
	 else if ((char_ASCII>=33 && char_ASCII<=47) || (char_ASCII>=58 && char_ASCII<=64) 
	   || (char_ASCII>=91 && char_ASCII<=96) || (char_ASCII>=123 && char_ASCII<=126))
	    return 4;
	 //한글
	 else if ((char_ASCII >= 12592) || (char_ASCII <= 12687))
	    return 3;
	 else 
	    return 0;
	}


