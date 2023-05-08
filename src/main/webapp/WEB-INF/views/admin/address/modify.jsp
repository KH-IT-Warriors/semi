<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>Document</title>
</head>
</script>
<meta charset="UTF-8">
<title>주소록 수정화면</title>
</head>
<style>
.btn-custom {
	padding: 8px 12px 8px 12px;
	margin: 0px 0px 0px 10px;
	font-size: 10pt;
	border-radius: 15px;
	background-color: #C6AD8A;
	border-radius: 0.5rem;
	border: none;
}

.input {
	width: 250px;
}
</style>

<body>
	<div class="container">
		<div class="row header">header</div>
		<div class="row body">
			<div class="col-2">side</div>
			<div class="col-8">
				<div class="row" align="center">
					<div class="col-12">
						<h3>배송지 수정</h3>
					</div>
					<div class="col-5 mt-2" align="right">이름</div>
					<div class="col-7 mt-2" align="left">
						<input type="text" class="input" value="${address.name}">
					</div>
					<div class="col-5 mt-2" align="right">우편번호</div>
					<div class="col-4 mt-2" align="left">
						<input type="text" class="input" id="sample4_postcode"
							placeholder="우편번호">
					</div>
					<div class="col-3" align="left">
						<input type="button" 
							class="btn btn-outline-dark btn-custom"
							onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
					</div>
					<div class="col-5 mt-2" align="right">도로명주소</div>
					<div class="col-7 mt-2" align="left">
						<input type="text" class="input" id="sample4_roadAddress"
							placeholder="도로명주소">
					</div>
					<div class="col-5 mt-2" align="right">지번주소</div>
					<div class="col-7 mt-2" align="left">
						<input type="text" class="input" id="sample4_jibunAddress"
							placeholder="지번주소">
					</div>
					<div class="col-5 mt-2" align="right">상세주소</div>
					<div class="col-7 mt-2" align="left">
						<input type="text" class="input" id="sample4_detailAddress"
							placeholder="상세주소"> <span id="guide"
							style="color: #999; display: none"></span>
						<div class="col-12 mt-2" align="center">
							<button button class="btn btn-outline-dark btn-custom">취소</button>
							<button button class="btn btn-outline-dark btn-custom">저장</button>
						</div>
					</div>
				</div>
				<div class="col-2">side</div>
			</div>
			<div class="row footer">footer</div>
		</div>
	</div>

	<script>
		function sample4_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							var roadAddr = data.roadAddress; // 도로명 주소 변수
							var extraRoadAddr = ''; // 참고 항목 변수

							document.getElementById('sample4_postcode').value = data.zonecode;
							document.getElementById("sample4_roadAddress").value = roadAddr;
							document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

							// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
							if (roadAddr !== '') {
								document.getElementById("sample4_extraAddress").value = extraRoadAddr;
							} else {
								document.getElementById("sample4_extraAddress").value = '';
							}
						}
					}).open();
		}
	</script>
</body>

</html>