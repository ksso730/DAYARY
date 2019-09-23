function initMoimCategoryList(opt){
   
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
            var no         = item[n].no;
            var name = item[n].commName;
            
            $('#categorybox').append('<button class="btn active">' + name + '</button>');
         }
         $('#categorybox').selectmenu('refresh');
      } 
   });
   
}