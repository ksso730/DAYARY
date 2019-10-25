//모달창 띄우기  시작

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
function modal_write(plan,writer,id,parent) {
  modal.style.display = "block";
  $("#title").text(plan);
  $("#writer").text(writer);
  
  if(writer!=$("#Login").attr("data"))
	  $("#myTabContent").style.display="none";
  $("#toDoWriteListId").val(id);
  $("#toDoWriteId").val(parent);
	 
}

function modal_view(plan,writer,id){
	modal.style.display = "block";
	  $("#title").text(plan);
	  $("#writer").text(writer);
	$.ajax({
	    	url:'/moimDetail/moimTodoList/modalView/'+id,
	    	contentType: "application/json; charset=utf-8",
	        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        success:function(data){
	        	 if(data.code==1){
	               var c=data.modal;
	               $(".form-group").html(c.memo);
	               $(".btn-group").toggle();
	        	 }else{
	                 alert(data.message);
	             }
	        }, error:function(e){

	        }
});
	
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
//글 작성
function submit(){
	if($("#message").val()==''){
		alert("내용을 작성하세요");
		return;
	}
	var communityBoard={};
	communityBoard.title=$("#title")[0].textContent;
	communityBoard.memo=$("#message").val();
	
	let formData = new FormData();
	if($("#customFile").val()){
		var communityFile;
		communityFile.filename=$("#customFile").val();
		formData.append("file", $('#customFile')[0].files[0]);
	}

	formData.append('communityBoard', new Blob([JSON.stringify(communityBoard)], {
		type: "application/json; charset=UTF-8"
	}));

	$.ajax({
	      url:'/moimDetail/moimTodoList/modalWrite/'+$("#toDoWriteListId").val(),
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
	        		 modal.style.display = "none";
	        		 get_detail($("#toDoWriteId").val());
	             }else{
	                 alert(data.message);
	             }
	        }, error:function(e){

	        }
	    });

	
}
