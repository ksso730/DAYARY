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
        	 var code  = item[n].commCode;
             var name = item[n].commName;
            
             $('#categorybox').append('<option value="' + code + '">' + name + '</option>');
         }
         $('#categorybox').selectmenu('refresh');
      } 
   });
   
}