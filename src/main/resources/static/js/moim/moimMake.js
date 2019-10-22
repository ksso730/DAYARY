$(document).ready(function(){
	//로딩화면 스타트
	isLoading.start();
	$("#moim_image_file").on("change", handleImgFileSelect);
	$('#preview_img').hide();
});

function handleImgFileSelect(e){
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	
	filesArr.forEach(function(f) {
		if(!f.type.match("image.*")){
			alert('파일을 확인해주세요.');
			return;
		}
		
		sel_file = f;
		
		var reader = new FileReader();
		reader.onload = function(e){
			$('#preview_img').attr("src", e.target.result);
		}
		reader.readAsDataURL(f);
		$('.img-box.upload-club-img label').attr('style',"background-image: none");
		$('#preview_img').show();
	});
}

$(function () {

	$('.club_camera').click(function (e) {

		e.preventDefault();
	
		$('#moim_image_file').click();

	});

});

function deletePreImage(){
	$('#preview_img').hide();
	$('#moim_image_file').val("");
	$('.img-box.upload-club-img label').attr('style',"url(images/camera_image.jpg)");
}


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
        // $('#categorybox').selectmenu('refresh');
         
      }
   });
 //로딩화면 스탑
   isLoading.stop();
}

$('#moimMake_btn').off().on('click', function () {

    var cate = document.getElementById('categorybox');
    var si = document.getElementById('sido_code');
    var goon = document.getElementById('sigoon_code');
    
    let moim = {};
    moim.title = $('#title').val();
    moim.peopleLimit = $('#peopleLimit').val();
    moim.intro = $('#intro').val();
    moim.sidocode = si.options[si.selectedIndex].value;
    moim.sigooncode = goon.options[goon.selectedIndex].value;
    moim.sidoname = si.options[si.selectedIndex].text;
    moim.sigoonname = goon.options[si.selectedIndex].text;
    moim.joinCondition = $(":input:radio[name=chk_info]:checked").val();
    
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
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});

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


