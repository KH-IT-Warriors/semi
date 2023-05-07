<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>
	  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" rel="stylesheet">
<title>Insert title here</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100;0,200;0,500;1,100;1,200;1,300&family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap')
	;

* {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 14px;
}

/* div {
      border: 1px solid black;
    } */
    
#imgbox {
	width: 200px;
	height: 200px;
	line-height: 200px;
	border: 1px solid #ebebeb;
}

#detailbox {
	width: 200px;
	height: 500px;
	line-height: 500px;
	border: 1px solid #ebebeb;
}

#id {
display : none;
}


</style>

</head>
<body>
	<div class="container-md">
		<div class="row header">
			<jsp:include page="/WEB-INF/views/component/header.jsp"></jsp:include>
		</div>
		<div class="row body">
			<div class="col-md-2 left side">
				<jsp:include page="/WEB-INF/views/component/leftside.jsp"></jsp:include>
			</div>
			<div class="col-sm-12 col-md-8">
			<form action = "/admin/product/delete" method="post">
				<div class="row">
					<div class="col-3 justify-content-center text-center">
						<div class="w-100 mt-4" id="imgbox">이미지 준비중</div>
					</div>
					<div class="col-9" style="border-left: 1px solid #ebebeb;">
						<div class="mt-3" style="border-bottom: 1px solid #ebebeb;">
						상품 카테고리 : ${product.category} </div>
						<div class="mt-3" style="border-bottom: 1px solid #ebebeb;">
            상품 번호 : ${product.id} </div>
						<div class="mt-3" style="border-bottom: 1px solid #ebebeb;">
						상품 이름 : ${product.name} </div>
						<div class="mt-3" style="border-bottom: 1px solid #ebebeb;">
						상품 가격 : ${product.price}</div>
						<div class="mt-3" style="border-bottom: 1px solid #ebebeb;">
						상품 수량 : ${product.quantity}</div>
						<div class="w-100 mt-3 mb-2" style="height: 150px;">
						상품 요약 설명 : ${product.summary}</div>
						<input type = "text" value = "${product.id}" id = "id" name = "id">
					</div>
				</div>
				<div class="row" style="border-top: 1px solid #ebebeb;">
					<div class="col d-flex justify-content-center mt-3 mb-3">
						<div id="detailbox"
							class="w-100 mt-2 d-flex justify-content-center">이미지 준비중</div>
					</div>
				</div>
				<div class="row" style="border-top: 1px solid #ebebeb;">
					<div class="col d-flex justify-content-center"
						style="height: 200px; line-height: 200px;">
						<span>등록된 리뷰가 존재하지 않습니다.</span>
					</div>
				</div>
				<div class="row" style="border-top: 1px solid #ebebeb;">
					<div class="col text-end">
					<a href="/admin/product/modify?id=${product.id}"><button class="btn btn-dark btn-sm mt-2 mb-2" type = "button">수정</button></a>
						<button class="btn btn-dark btn-sm mt-2 mb-2" type ="submit">삭제</button>
					</div>
				</div>
				</form>
			</div>
			<div class="d-none d-md-block col-md-2">body side</div>
		</div>
		<div class="row footer">
			<jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>