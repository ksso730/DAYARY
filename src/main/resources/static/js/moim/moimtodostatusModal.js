//모달창 띄우기  시작

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

//체크저장기능
function post_send() {
	var temp = document.getElementById("myUL").getElementsByTagName("LI");
	var list="";
	if(temp.length!=0){
	for (var i in temp) {
		if(temp[i].className=="checked"){
			list+=temp[i].value+",";
		}
	}
	var param={
			"list":list
	}
	   $.ajax({
	      url:'/moimDetail/moimTodoList/moimtodostatus/moimtodostatusDetail',
	        type:'post',
	        data: JSON.stringify(param) ,
	        contentType: "application/json; charset=utf-8",
	        success:function(data){
	        	 if(data.code==1){
	                 location.href='/moimDetail/moimTodoList/'+ $('#moimNo').attr("data-moimNo");
	             }else{
	                 alert(data.message);
	             }
	        }, error:function(e){

	        }
	    });
	}else
		alert("변경사항이 없습니다.")
}
