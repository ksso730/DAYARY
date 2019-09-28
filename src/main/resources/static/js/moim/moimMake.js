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

    var cate = document.getElementById('categorybox');
    var si = document.getElementById('sido_code');
    var goon = document.getElementById('sigoon_code');
    
    let moim = {};
    moim.title = $('#title').val();
    moim.peopleLimit = $('#peopleLimit').val();
    moim.intro = $('#intro').val();
    moim.sido_code = si.options[si.selectedIndex].text;
    moim.sigoon_code = goon.options[goon.selectedIndex].text;
    
    let category = {};
    category.commName = cate.options[cate.selectedIndex].text;
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
        	alert(data.message);
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

/**
 * 
 * @시/도/구 카테고리 조회 API
 */
$(function(){
	
	$.ajax({
		type: "get",
		url: "http://openapi.nsdi.go.kr/nsdi/eios/service/rest/AdmService/admCodeList.json",
		data : {authkey : $('#sido_key').val()},
		async: false,
		dataType: 'json',
		success: function(data) {
			var html = "<option>선택</option>";
			
			for(var i=0;i<data.admVOList.admVOList.length;i++){ 
				html +="<option value='"+data.admVOList.admVOList[i].admCode+"'>"+data.admVOList.admVOList[i].lowestAdmCodeNm+"</option>"
			}
			
            $('#sido_code').html(html);
			
		},
		error: function(xhr, stat, err) {}
	});
	
	
	$(document).on("change","#sido_code",function(){
		var thisVal = $(this).val();		
		
		$.ajax({
			type: "get",
			url: "http://openapi.nsdi.go.kr/nsdi/eios/service/rest/AdmService/admSiList.json",
			data : {admCode : thisVal, authkey : $('#sigoon_key').val()},
			async: false,
			dataType: 'json',
			success: function(data) {
				var html = "<option>선택</option>";
				
				for(var i=0;i<data.admVOList.admVOList.length;i++){ 
					html +="<option value='"+data.admVOList.admVOList[i].admCode+"'>"+data.admVOList.admVOList[i].lowestAdmCodeNm+"</option>"
				}
				
	            $('#sigoon_code').html(html);
				
			},
			error: function(xhr, stat, err) {}
		});
	});
})


