

// 출발지 주소 검색 버튼
document.getElementById('search-origin').addEventListener('click', function() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('origin').value = data.address;
        }
    }).open();
});

// 도착지 주소 검색 버튼
document.getElementById('search-destination').addEventListener('click', function() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById('destination').value = data.address;
        }
    }).open();
});


// 사용자가 길 찾기 버튼을 눌렀을 때의 동작
document.getElementById('search-form').addEventListener('submit', function(e) {
    e.preventDefault(); // 기본 submit 동작을 막습니다.

    var origin = document.getElementById('origin').value;
    var destination = document.getElementById('destination').value;

    // TODO: 여기서 origin과 destination 주소 검증 로직이 필요합니다.

    fetch('/route?origin=' + origin + '&destination=' + destination)
        .then(response => response.json())
        .then(data => {
            // data는 KakaoRouteAllResponseDto 객체

            // 경로 정보(routes)의 각 섹션(section)별로 반복하여 처리합니다.
            for (let route of data.routes) {
                for (let section of route.sections) {

                    // 각 섹션의 경계 상자(bound) 정보를 가져옵니다.
                    let bound = section.bound;

                    // TODO: bound 정보 등을 이용하여 카카오 지도에 섹션을 표시합니다.
                }
            }
        });
});

// 카카오 지도 초기화
var mapContainer = document.getElementById('map'),
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 초기 지도 중심 좌표
        level: 3 // 지도 확대 레벨
    };
var map = new kakao.maps.Map(mapContainer, mapOption);