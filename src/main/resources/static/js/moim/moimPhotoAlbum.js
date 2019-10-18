var totalNumber = 0;
var imagePath = {};



function addImamge(path){
    console.log(path.value);
    imagePath[totalNumber] = path;
    var image = document.getElementById("imgTest");
    image.src = path.value
    
}

function imageTransfer(){
    $.ajax({
    	url : '미정',
    	type : "post",   
    	processData: false, //데이터를 쿼리 문자열로 변환하는 jQuery 형식 방지
        contentType: false,
    	contentType: 'application/json; charset=UTF-8',
    	dataType   : 'json',  
    	data	   : imagePath,
        success:function(data){
            alert('success.');
        },
        error:function(e){
            alert('fail');
        }
    });
}