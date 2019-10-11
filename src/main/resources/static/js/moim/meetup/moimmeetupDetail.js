$('[name="meetuppeopleList_btn"]').on('click', function () {//회원 강퇴하기 by choiseongjun 2019-10-01
   var moimNo = $('#moimNo').attr("data-moimNo");

     let tempdata = {};
     tempdata.no1= $(this).val();
     tempdata.no2= $('#moimNo').attr("data-moimNo");

     console.log($(this).val());
     console.log(moimNo);

    $.ajax({
          url : '/meetuppeopleList', 
          type : "post",   
          contentType: 'application/json; charset=UTF-8',
  		  dataType:'json',
  		  data: JSON.stringify(tempdata),
 		  success:function(data){
 			if(data.code==1){
 				alert(data.message);
 				 // location.href='/moimlistView/moimdetailView/'+moimNo;
 			}else{
 				alert(data.message);
 			}
 		},
           error:function(e){

           }
       });
});