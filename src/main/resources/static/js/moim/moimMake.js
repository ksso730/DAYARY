function initMoimMake(opt){
   
   $.ajax({
      
      url:'/getMoimCategory',
        type:'post',
        enctype: 'multipart/form-data',
        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
        contentType: false,
        dataType:'json',
        cache: false,
        mimeType:"multipart/form-data",
        //data: formData,
        success:function(data){
         
          $('#categorybox').empty(); // 초기화
         
         var item = data._category;
         
         for (var n=0; n<item.length; n++){
        	 var code  = item[n].commCode;
            var name = item[n].commName;
            
            $('#categorybox').append('<option value="' + code + '">' + name + '</option>');
         }
         $('#categorybox').selectmenu('refresh');
      }
   });
   
}



$('#moimMake_btn').off().on('click', function () {

    var obj = document.getElementById('categorybox');

    let moim = {};
    moim.title = $('#title').val();
    moim.peopleLimit = $('#peopleLimit').val();
    moim.intro = $('#intro').val();

    let category = {};
    category.subject = obj.options[obj.selectedIndex].text;
    moim.category = category;

    let formData = new FormData();
    formData.append("file", $('#moim_image_file')[0].files[0]);
    formData.append('moim', new Blob([JSON.stringify(moim)], {
        type: "application/json; charset=UTF-8"
    }));

    $.ajax({
        url:'/moimMake',
        type:'post',
        enctype: 'multipart/form-data',
        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
        contentType: false,
        dataType:'json',
        cache: false,
        mimeType:"multipart/form-data",
        data: formData,
        success:function(data){
            if(data.code==1){
                alert(data.message);
                location.href='/';
            }else{
                alert(data.message);
            }
        },
        error:function(e){

        }
    });

});

$('#peopleLimit').keyup(function () {
    numberKey();
});

function numberKey() {

    if (check_key() != 1) {
        event.returnValue = false;
        alert("숫자만 입력할 수 있습니다.");
        document.getElementById('peopleLimit').value = '';
        return;
    }
}

function check_key() {
    var char_ASCII = event.keyCode;

    //숫자
    if (char_ASCII >= 48 && char_ASCII <= 57)
        return 1;
    //영어
    else if ((char_ASCII >= 65 && char_ASCII <= 90) || (char_ASCII >= 97 && char_ASCII <= 122))
        return 2;
    //특수기호
    else if ((char_ASCII >= 33 && char_ASCII <= 47) || (char_ASCII >= 58 && char_ASCII <= 64)
        || (char_ASCII >= 91 && char_ASCII <= 96) || (char_ASCII >= 123 && char_ASCII <= 126))
        return 4;
    //한글
    else if ((char_ASCII >= 12592) || (char_ASCII <= 12687))
        return 3;
    else
        return 0;
}
