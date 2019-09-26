// Create a "close" button and append it to each list item
var myNodelist = document.getElementById("myUL").getElementsByTagName("LI");
var i;
for (i = 0; i < myNodelist.length; i++) {
  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  myNodelist[i].appendChild(span);
}

// Click on a close button to hide the current list item
var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
  close[i].onclick = function() {
    var div = this.parentElement;
    div.style.display = "none";
  }
}

/* Add a "checked" symbol when clicking on a list item
var list = document.querySelector('#myUL1');
list.addEventListener('click', function(ev) {
  if (ev.target.tagName === 'LI') {
    ev.target.classList.toggle('checked');
  }
}, false);
*/
// Create a new list item when clicking on the "Add" button
function newElement() {
  var li = document.createElement("li");
  var inputValue = document.getElementById("myInput1").value;
  var t = document.createTextNode(inputValue);
  li.setAttribute("class","ui-state-default")
  li.appendChild(t);
  if (inputValue === '') {
    alert("You must write something!");
  } else {
    document.getElementById("myUL").appendChild(li);
  }
  document.getElementById("myInput1").value = "";

  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  li.appendChild(span);

  for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
      var div = this.parentElement;
      div.style.display = "none";
    }
  }
}

 $( "#from_date" ).datepicker({
	    dateFormat: 'yymmdd',
 });
 $( "#to_date" ).datepicker({
	 dateFormat: 'yymmdd'
});
 
function check(){
	let d=new Date();
	var from =$( "#from_date" ).val();
	var to=$( "#to_date" ).val();
 if(from>to){
	 alert("잘못된 날짜 범위입니다.");
	 $( "#to_date" ).val("");
 }
}

 function post_send() {
	var temp = document.getElementById("myUL").getElementsByTagName("LI");
	var list="";
	for (var i in temp) {
		var x=temp[i].innerText;
		if(x!=null){
			list+=temp[i].innerText.slice(0,-1)+",";
		}
	}
	let toDoWrite={};
	let moim={};
	toDoWrite.plan_title=$("#title").val();
	toDoWrite.from_date=$("#from_date").val();
	toDoWrite.to_date=$("#to_date").val();
	moim.id= $('#moimNo').attr("data-moimNo");
	let toDoWriteList={};
	toDoWriteList.toDoWrite=toDoWrite;
	toDoWriteList.moim=moim;
	toDoWriteList.plan_list=list;
	  alert( JSON.stringify(toDoWriteList) );
	   $.ajax({
	      url:'/moimDetail/moimTodoList/moimTodowrite',
	        type:'post',
	        data: JSON.stringify(toDoWriteList) ,
	        contentType: "application/json; charset=utf-8",
	        success:function(data){
	        	 if(data.code==1){
	                 alert(data.message);
	                 location.href='moimlistView/moimdetailView'+ $('#moimNo').attr("data-moimNo");
	             }else{
	                 alert(data.message);
	             }
	        }, error:function(e){

	        }
	    });

 }
 