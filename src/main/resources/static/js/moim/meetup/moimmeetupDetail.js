$('[name="offmoimWithdraw_btn"]').on('click', function () {//가입승인 대기중인사람 승인하기 by choiseongjun 2019-10-07
	 var moimNo = $('#moimNo').attr("data-moimNo");
	 var meetupNo=$(this).val();
    let tempdata = {};
    tempdata.no1= $(this).val();
    tempdata.no2= $('#moimNo').attr("data-moimNo");
   

   $.ajax({
         url : '/moimlistView/moimdetailView/moimmeetupDetailView/offmoimWithdraw/'+moimNo+'/'+meetupNo, 
         type : "delete",   
         contentType: 'application/json; charset=UTF-8',
 		  dataType:'json',
 		  data: JSON.stringify(tempdata),
		  success:function(data){
			if(data.code==1){
				alert(data.message);
					
				location.href='/moimlistView/moimdetailView/moimmeetupDetailView/'+moimNo+'/'+meetupNo;
			}else{
				alert(data.message);
			}
		},
          error:function(e){

          }
      });
});

$('[name="offmoimJoin_btn"]').on('click', function () {//가입승인 대기중인사람 승인하기 by choiseongjun 2019-10-07
	 var moimNo = $('#moimNo').attr("data-moimNo");
	 var meetupNo=$(this).val();
    let tempdata = {};
    tempdata.no1= $(this).val();
    tempdata.no2= $('#moimNo').attr("data-moimNo");
   

   $.ajax({
         url : '/moimmeetupJoin', 
         type : "post",   
         contentType: 'application/json; charset=UTF-8',
 		  dataType:'json',
 		  data: JSON.stringify(tempdata),
		  success:function(data){
			if(data.code==1){
				alert(data.message);
				  location.href='/moimlistView/moimdetailView/moimmeetupDetailView/'+moimNo+'/'+meetupNo;
			}else{
				alert(data.message);
			}
		},
          error:function(e){

          }
      });
});