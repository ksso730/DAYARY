//모달창 띄우기  시작

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
function modal_view(plan,writer,id,parent,email){
	modal.style.display = "block";
	  $("#title").text(plan);
	  $("#writer").text(writer);
	 
	  $("#toDoWriteListId").val(id);
	  $("#toDoWriteId").val(parent);
	
	$.ajax({
	    	url:'/moimDetail/moimTodoList/modalView/'+id,
	    	contentType: "application/json; charset=utf-8",
	        processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
	        success:function(data){
	        	 if(data.code==1){
	               var m=data.modal;
	               var mfile=data.modalfile;
	               console.log(m)
	               var html="<div class='container'><div class='row'><ul class='cbp_tmtimeline' style='background-color : white; width:1200px'>";
	               for(var i=0;i<m.length;i++){
	            	   html+="<li><time class='cbp_tmtime' datetime="+m[i].createdAt+" ><span>"+m[i].createdAt.slice(0,10)+" "+m[i].createdAt.slice(11,20)+"</span></time> "
	            	   html+=' <div class="cbp_tmicon bg-info"><i class="zmdi zmdi-label"></i></div><div class="cbp_tmlabel">'
	            	   html+=' <blockquote><p class="blockquote blockquote-primary">'+m[i].memo+"</p></blockquote></li>"
	            	   console.log(i)
	            	   if(typeof  m[i].moimBoardfile[0]!= 'undefined' && m[i].moimBoardfile[0].real_name != 'undefined'){
	            		  for(var j in m[i].moimBoardfile){
	            			  html+="<div class='img' onclick='expand(this)'>"
	            			  html+="<img  src='/getMoimImage/"+m[i].moimBoardfile[j].real_name+"' height='150px' width='250px'>";
	            			  html+="<span>+</span>"
	            			  html+="</div>"
	            		  }
	            	   }
	               }
	               html+="</ul></div></div>";
	            	   
	              /* $(".form-group").html(c.memo);
	               $(".btn-group").toggle();*/
	               $("#modal_content").html(html);
	               if($("#Login").attr("data")!=email){
	         		  $("#modal_write").hide();
	         	  }else
	         		 $("#modal_write").show();
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
	
	if($("#message").val()==''&&array.length==0){
		alert("내용을 작성하세요");
		return;
	}
	var MoimBoard={};
	MoimBoard.title=$("#title")[0].textContent;
	MoimBoard.memo=$("#message").val();
	
	let formData = new FormData();
	if(array.length>0){
		for(var i in array){
			formData.append("File", array[i][0]);
		}
	}

	formData.append('MoimBoard', new Blob([JSON.stringify(MoimBoard)], {
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
	        		 $("#message").val("");
	        		 $("#file").val("");
	        		 $("#imgList").html("");
	        		 array=[];
	             }else{
	                 alert(data.message);
	             }
	        }, error:function(e){

	        }
	    });

	
}
$("#file").on("change",handleImgFileSelect);
var count=0;
var array=[];
var span = document.createElement("SPAN");
var txt = document.createTextNode("\u00D7"); 
var span2 = document.createElement("SPAN");
var txt2 = document.createTextNode("+"); 
span.appendChild(txt);
span2.appendChild(txt2)
	let imgList = document.getElementById('imgList'); // 미리보기 div 아이디
function handleImgFileSelect(e){
	let inputId = e.target.id; // 이벤트가 발생된곳 ID
	let imgId = 'img'+inputId; // 추가되는 이미지 ID
	var files = e.target.files; // 추가되는 파일
	var filesArr = Array.prototype.slice.call(files); // ?
	var fileName=e.target.files[0].name;
	array.push(e.target.files);
	/*let newFileButtonId = 'file'+ (count+1);
	let formId = document.getElementById('file');
	count++; // 숫자 추가
*/	filesArr.forEach(function(f) {
	
		var reader = new FileReader();
		reader.onload = function(e){
			
			imgList.appendChild(span);
			imgList.innerHTML += '<img src="'+e.target.result+'"name="'+fileName+'" id="'+imgId+'" style = "width:200px;height:100px; margin=2px;"/>'; // div에 미리보기 추가
		}
		reader.readAsDataURL(f);
	});
}
$("#imgList").on("click","span",function(e) {
	  var target=e.target.nextElementSibling;
	   target.remove();
	   e.target.remove();
	  for(var i=0;i<array.length;i++){
		 if(array[i][0].name==target.name){
			 array.splice(i,1);
		 }
	  }
});


function expand(div){
	e=div.firstElementChild;
	if(e. height=="600"){
		e.height="150"
		e.width="250"
	}else{
		e.height="600"
		e.width= "1000"
	}
}
