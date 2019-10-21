//모달창 띄우기  시작

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
function modal_view(plan,writer,id) {
  modal.style.display = "block";
  $("#title").text(plan);
  $("#writer").text(writer);
  
  if(writer!=$("#Login").attr("data"))
	  $("#myTabContent").style.display="none";
  $("#toDoWriteListId").val(id);
	 
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
	var toDoWriteList;
	toDoWriteList.id=$("#toDoWriteListId").val();
	
}
