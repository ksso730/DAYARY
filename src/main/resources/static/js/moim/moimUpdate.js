$('#moimUpdate_btn').off().on('click', function () {

    var obj = document.getElementById('categorybox');

    let moim = {};
    moim.title = $('#title').val();
    moim.peopleLimit = $('#peopleLimit').val();
    moim.intro = $('#intro').val();

    let category = {};
    category.commName = obj.options[obj.selectedIndex].text;
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