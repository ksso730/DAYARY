

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });

    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용
                        $('#x_location').val(result.x);
                        $('#y_location').val(result.y);
                        
                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
    
 // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
  searchAddrFromCoords(map.getCenter(), displayCenterInfo);

 // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
 kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
     searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
         if (status === kakao.maps.services.Status.OK) {

             // 마커를 클릭한 위치에 표시합니다 
             marker.setPosition(mouseEvent.latLng);
             marker.setMap(map);
             
             document.getElementById("address").value = result[0].address.address_name;
             document.getElementById("x_location").value = mouseEvent.latLng.Ga;
             document.getElementById("y_location").value = mouseEvent.latLng.Ha;
         }   
     });
 });

 // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
 kakao.maps.event.addListener(map, 'idle', function() {
     searchAddrFromCoords(map.getCenter(), displayCenterInfo);
 });

 function searchAddrFromCoords(coords, callback) {
     // 좌표로 행정동 주소 정보를 요청합니다
     geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
 }

 function searchDetailAddrFromCoords(coords, callback) {
     // 좌표로 법정동 상세 주소 정보를 요청합니다
     geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
 }

 // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
 function displayCenterInfo(result, status) {
     if (status === kakao.maps.services.Status.OK) {
         var infoDiv = document.getElementById('centerAddr');

         for(var i = 0; i < result.length; i++) {
             // 행정동의 region_type 값은 'H' 이므로
             if (result[i].region_type === 'H') {
                 infoDiv.innerHTML = result[i].address_name;
                 break;
             }
         }
     }    
 }
 $('#offmoimMake_btn').off().on('click', function () {//오프라인 모임만들기 by choiseongjun 2019-10-02

	    var moimNo = $('#moimNo').attr("data-No");
	  
	    let meetUp = {};
	    meetUp.title = $('#title').val();
	    meetUp.detailAddress=$('#address').val();
	    meetUp.locationX=$('#x_location').val();
	    meetUp.locationY=$('#y_location').val();
	    meetUp.peopleLimit=$('#peopleLimit').val(); 
	    meetUp.intro=$('#intro').val();
	    meetUp.meetDate=$('#from_date').val();
	    meetUp.money=$('#money').val();
	    $.ajax({
	          url : '/moimoffMake/'+moimNo, 
	          type : "post",   
	          contentType: 'application/json; charset=UTF-8',
	  		  dataType:'json',
	  		  data: JSON.stringify(meetUp),
	 		  success:function(data){
	 			if(data.code==1){
	 				alert(data.message);
	 				
	 				close();
	 				opener.parent.location.reload();
	 			}else{
	 				alert(data.message);
	 			}
	 		},
	           error:function(e){

	           }
	       });
	});

 $( "#from_date" ).datepicker({
	    dateFormat: 'yymmdd',
});