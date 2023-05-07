<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
	rel="stylesheet">
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
	border: 1px solid #ebebeb;
	line-height: 200px;
	border-radius: 20%;
}

#info {
	width: 200px;
}

p {
	border-bottom: 1px solid #ebebeb;
}

a {
	margin: 0px;
	padding: 0px;
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

#search {
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 1px solid #ebebeb;
	box-shadow: none;
}

#search:focus {
	border-bottom: 2px solid #000000;
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
				<div class="row">
					<div class="d-none d-md-block col-8 mt-3">
						<h3>상품 관리</h3>
					</div>
					<div class="col-12 d-md-none d-flex justify-content-center">
						<h3 class="mt-3">상품 관리</h3>
					</div>
					<div class="d-none d-md-block col-4">
						<nav class="navbar">
							<div class="container-fluid flex-d justify-content-end">
								<form class="d-flex">
									<input class="form-control me-3 " type="search"
										placeholder="상품 검색" aria-label="Search" id="search">
									<button class="btn btn-light btn-sm" type="submit">Search</button>
								</form>
							</div>
						</nav>
					</div>
					<div class="col-12 d-md-none">
						<nav class="navbar">
							<div class="container-fluid flex-d justify-content-center">
								<form class="d-flex">
									<input class="form-control me-3 " type="search" id="search"
										placeholder="상품 검색" aria-label="Search">
									<button class="btn btn-light btn-sm" type="submit">
										<i class="fa-solid fa-magnifying-glass"></i>
									</button>
								</form>
							</div>
						</nav>
					</div>
				</div>
				<div class="row" style="border-bottom: 1px solid #ebebeb;">
					<div class="d-none d-md-block col-8">
						<ul class="mt-2">
							<li>등록 상품 내역</li>
						</ul>
					</div>
					<div class="col-12 col-md-4 text-end">
						<a href="/admin/product/register"><button
								class="btn btn-outline-dark btn-custom mt-2 mb-2 me-2">상품
								등록</button></a>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-md-8">
						<c:forEach var="product" items="${products}">
							<a href="/admin/product/item?id=${product.id}"
								class="text-reset text-decoration-none">
								<div class="row">
									<div class="col-4 m-3 d-flex justify-content-center"
										id="imgbox">이미지</div>
									<div class="col-8 mt-3 mb-3" id="info">
										<p class="mt-4">상품 번호 : ${product.id}</p>
										<p>상품 이름 : ${product.name}</p>
										<p>상품 가격 : ${product.price}</p>
										<p>상품 수량 : ${product.quantity}</p>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
				</div>
				<div class="d-none d-md-block col-4"></div>
			</div>
			</div>
			<hr class="mt-5 mb-5">
			<div class="row footer">
				<jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>
			</div>
		</div>
</body>
</html>