<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>Document</title>
</head>
<style>
div {
	
}

#address-font {
	font-size: 15px;
}

.btn-custom {
	padding: 8px 12px 8px 12px;
	margin: 0px 0px 0px 10px;
	font-size: 10pt;
	border-radius: 15px;
	background-color: #C6AD8A;
	border-radius: 0.5rem;
	border: none;
}
</style>

<body>
	<div class="container">
		<div class="row header">
			header
			<jsp:include page="/WEB-INF/views/component/header.jsp"></jsp:include>
		</div>
		<div class="row body">
			<div class="col-md-2 left side">
				side
				<jsp:include page="/WEB-INF/views/component/leftside.jsp"></jsp:include>
			</div>
			<div class="col-sm-12 col-md-8">
				<div class="row">
					<div class="col-10">
						<h3>주소록</h3>
						<hr>
					</div>
					<div class="col-2">
						<button class="btn btn-outline-dark btn-custom">+배송지추가</button>
					</div>
					<c:forEach items="${address} " var="i">
						<div class="row">
							<div class="col-8" id="address-font">
								${i.address} 기본배송지 <br> ${i.postaddress } 우편주소<br>
								${i.roadaddress } 도로명주소<br> ${i.lotaddress } 지번주소<br>
								${i.detailaddress } 상세주소
							</div>
							<div class="col-4" align="center" style="text-align: center;">
								<button class="btn btn-outline-dark btn-custom">수정</button>
								<button class="btn btn-outline-dark btn-custom">삭제</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="d-none d-md-block col-md-2"></div>
		</div>
		<div class="row footer">
			footer
			<jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>

		</div>
	</div>
</body>

</html>