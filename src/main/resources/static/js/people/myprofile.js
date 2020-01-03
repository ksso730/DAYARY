function getNoti(){
	
	console.log('알림...');
	var peopleId = $('#peopleId').attr("data-peopleId");
	$.ajax({
	      url:'/getMyNotiList',
	        contentType: "application/json; charset=utf-8",
	        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        success:function(data){
	        	if(data.code==1){
		        	var notilist=data.notiList;
		        	console.log(notilist);
		        	var html=""
	        		for(var i in notilist){
	    				html += '<tbody>'
	    				html += '<tr>'
    					html += 	'<td>'
	    				html += 	'<span class="float-right font-weight-bold">'+notilist[i].createDate+'</span>'
						html += 	'</td>'
	    				html += 	'<td>'
	    				html += 	'<a href="moimlistView/moimdetailView/'+notilist[i].moim.id+'">'+notilist[i].memo+""+'</a>'
						html += 	'</td>'
		   				html += '</tr>'
	    				html += '</tbody>'
	    			}
	        		$("#notiList").html(html);
	        	}
	        }, error:function(e){
				alert(e)
	        }
	    });
}

function myMoimList(){
	console.log("로드");
	$.ajax({
	      url:'/myprofilemoimView',
	        contentType: "application/json; charset=utf-8",
	        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        success:function(data){
	        	console.log(data);
	        	if(data.code==1){
	        		var joinedMoimListNo=data.joinedMoimListNo;
	        		var joinedMoimList=data.joinedMoimList;
	        		var html=""
        			for(var i in joinedMoimList){
        				html += '<tr>'
        				html += 	'<td>'
        				html += 	'<a href="moimlistView/moimdetailView/'+joinedMoimListNo[i]+'">'+joinedMoimList[i]+""+'</a>'
    					html += 	'</td>'
		   				html += '</tr>'
        			}
	        		$("#list").html(html);
	        	}
	        }, error:function(e){
				alert(e)
	        }
	    });
	$.ajax({
	      url:'/myprofilemademoimView',
	        contentType: "application/json; charset=utf-8",
	        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        success:function(data){
	        	if(data.code==1){
	        		var madeMoimListNo=data.madeMoimListNo;
	        		var madeMoimListtitle=data.madeMoimListtitle;
	        		var html=""
      			for(var i in madeMoimListNo){
      				html += '<tbody>'
      				html += '<tr>'
      				html += 	'<td>'
      				html += 	'<a href="moimlistView/moimdetailView/'+madeMoimListNo[i]+'">'+madeMoimListtitle[i]+""+'</a>'
  					html += 	'</td>'
		   				html += '</tr>'
      				html += '</tbody>'
      			}
	        		$("#mademoimlist").html(html);
	        	}
	        }, error:function(e){
				alert(e)
	        }
	    });
}


$(document).ready(function(){//회원 탈퇴 by choiseongjun 2019-10-01
	$.ajax({
	      url:'/getMyNotiListCount',
	        contentType: "application/json; charset=utf-8",
	        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        success:function(data){
	        	console.log(data.getMyNotiCount);
	        	$("#badge").append(data.getMyNotiCount);
	        }, error:function(e){
				//'alert(e)
	        }
	    });  
	
	
	$("#people_delete_btn").click(function(){
		  var peopleId = $('#peopleId').attr("data-peopleId");
		  
			$.ajax({
		        url:'/myprofileView/deletePeople/'+peopleId,
		        type:'DELETE',
				contentType: 'application/json; charset=UTF-8',
		        dataType:'json',
		        success:function(data){
					if(data.code==1){
						alert(data.message);
						location.href='/logout';
					}else{
						alert(data.message)
					}
				},
				error:function(xhr,error){
					
				}
		    });
	  });
	});


function toMoim(){
	 var joinedMoimId = $('#joinedMoimId').attr("data-joinedMoimId");
	console.log('onclick'+joinedMoimId);
}
$('[name="joinedMoimId"]').on('click', function () {
	
//	console.log($(this).val());
	
});