<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
	rel="stylesheet">
<title>Insert title here</title>
</head>

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,200;0,300;0,400;0,500;1,200;1,400&display=swap')
	;

@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap')
	;

* {
	font-family: 'Jost', sans-serif;
}

input {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 14px;
}

#shop {
	color: #eb1062;
}

ul {
	margin: 0px;
	padding: 0px;
	list-style-type: none;
}

li {
	float: left;
}

#search {
	border: none;
	border-bottom: solid #aaaaaa 1px;
}

.category:hover {
	color: black;
	cursor: pointer;
	transition-duration: 0.5s;
}

.thumbnail {
	width: 250px;
	height: 250px;
	border-radius: 20%;
	border: 1px solid black;
	background-color: azure;
}

.name {
	font-weight: 700;
}

.summary {
	font-weight: 200;
}

.price {
	font-weight: 700;
	font-size: larger;
}

.fa-heart:hover {
	color: #eb1062;
	cursor: pointer;
}

.fa-cart-shopping:hover {
	cursor: pointer;
}

.fa-cart-shopping {
	margin-left: 5px;
}

.footli {
	float: none;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 14px;
}
</style>


<body>

	<div class="container-fluid">
		<div class="row header">
			<div class="row sticky-top header"
				style="height: 120px; background-color: white;">
				<div class="d-none d-md-block col-2">
					<div style="height: 120px;">
						<img src="/로고.png" class="w-100 h-70 mt-4">
					</div>
				</div>
				<div class="d-block d-md-none col-4">
					<div style="height: 100px;" class="mt-3">
						<img src="/로고.png" class="w-100 h-70 mt-4">
					</div>
				</div>
				<div class="d-none d-md-block col-8">
					<nav class="navbar navbar-expand-lg mt-5">
						<div class="container-fluid">
							<a class="navbar-brand" href="#">Macaroon</a>
							<button class="navbar-toggler" type="button"
								data-bs-toggle="collapse" data-bs-target="#navbarNav"
								aria-controls="navbarNav" aria-expanded="false"
								aria-label="Toggle navigation">
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="collapse navbar-collapse" id="navbarNav">
								<ul class="navbar-nav">
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="#">Home</a></li>
									<li class="nav-item"><a class="nav-link" href="#"
										id="shop">Shop</a></li>
									<li class="nav-item"><a class="nav-link" href="#">Event</a>
									</li>
									<li class="nav-item"><a class="nav-link disabled" href="#"
										tabindex="-1" aria-disabled="true">LookBook</a></li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
				<div class="d-none d-md-block col-2">
					<ul class="m-4 w-100">
						<li style="float: left;" class="text-center mt-4"><a href="/"
							class="text-reset text-decoration-none"> <i
								class="fa-solid fa-user"></i></a>
							<div style="font-size: xx-small; text-align: center;">Login</div>
						</li>
						<li style="float: left;" class="text-center mt-4 ms-4"><a
							href="/" class="text-reset text-decoration-none"> <i
								class="fa-solid fa-house-user"></i></a>
							<div style="font-size: xx-small; text-align: center;">My
								Page</div></li>
						<li style="float: left;" class="text-center mt-4 ms-4"><a
							href="/" class="text-reset text-decoration-none"> <i
								class="fa-solid fa-hand-holding-heart" style="color: #04a9ad;"></i></a>
							<div style="font-size: xx-small; text-align: center;">Help</div>
						</li>
					</ul>
				</div>
				<div class="d-block d-md-none col-8 d-flex justify-content-center">
					<ul class="mt-5">
						<li class="mt-2"><input type="text" id="search"
							placeholder="Macaroon.com"> <i
							class="fa-solid fa-magnifying-glass" style="color: #aaaaaa;"></i>
						</li>
						<li class="mt-2 ms-3"><i class="fa-solid fa-user"
							style="color: #aaaaaa;"></i></li>
						<li class="mt-2 ms-3"><i class="fa-solid fa-cart-shopping"
							style="color: #aaaaaa;"></i></li>
						<li class="mt-2 ms-3"><i class="fa-solid fa-heart"
							style="color: #eb1062;"></i></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row body">
			<div class="col-12 ">
				<div class="d-flex justify-content-center">
					<span style="font-size: 32px;" class="m-3">Shop All</span>
				</div>
				<div class="d-flex justify-content-center">
					<ul style="color: #aaaaaa;">
						<li class="ms-2 category">Men</li>
						<li class="ms-5 category">Women</li>
						<li class="ms-5 category">Life</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row body">
			<div class="col-12">
				<div class="d-flex justify-content-center mt-5">
					<div>
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
							</p>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center mt-5">
					<div>
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
							</p>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
				</div>
				<div class="d-flex justify-content-center mt-5">
					<div>
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
							</p>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
					<div class="ms-3">
						<div class="ms-2 thumbnail"></div>
						<div class="mt-2 ms-3">
							<p style="margin-bottom: 0px;" class="name">Product Name</p>
							<p style="margin-bottom: 3px;" class="summary">Summary</p>
							<p style="margin-bottom: 0px;" class="price">Price</p>
							<p style="margin-bottom: 0px; margin-top: 15px;" class="like">
								<i class="fa-solid fa-heart" style="font-size: 12px;"></i> <i
									class="fa-solid fa-cart-shopping" style="font-size: 12px;"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr class="mt-5 mb-5">
		<div class="row footer">
			<div class="d-none d-md-block col-12 service_area mb-4">
				<div class="row">
					<div class="col-3">
						<ul>
							<strong>이용안내</strong>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">검수기준</a></li>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">이용정책</a></li>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">패널티 정책</a></li>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">커뮤니티 가이드라인</a></li>
						</ul>
					</div>
					<div class="col-4">
						<ul>
							<strong>고객지원</strong>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">공지사항</a></li>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">서비스 소개</a></li>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">쇼룸 안내</a></li>
							<li class="mt-2 footli"><a href=""
								class="text-reset text-decoration-none">판매자 방문접수</a></li>
						</ul>
					</div>
					<div class="col-2"></div>
					<div class="col-3">
						<strong>고객센터 <a href="">1599-4666</a></strong>
						<p>운영시간 평일 11:00 ~ 18:00 (토.일 공휴일 휴무) 점심시간 평일 13:00 - 14:00</p>
						<button class="btn btn-light faq">자주 묻는 질문</button>
					</div>
				</div>
			</div>
			<div class="col-12 corporation_area">
				<div class="row">
					<div class="col-12 mb-3">
						<ul class="term_list">
							<li class="term_item me-2 mt-2 mb-2"><a href=""
								class="text-reset text-decoration-none">회사소개</a></li>
							<li class="term_item m-2"><a href=""
								class="text-reset text-decoration-none">인재채용</a></li>
							<li class="term_item m-2"><a href=""
								class="text-reset text-decoration-none">제휴제안</a></li>
							<li class="term_item m-2"><a href=""
								class="text-reset text-decoration-none">이용약관</a></li>
							<li class="term_item m-2"><a href=""
								class="text-reset text-decoration-none"><strong>개인정보처리방침</strong></a>
							</li>
						</ul>
					</div>
					<div class="col-12 mb-4">
						<p class="small-font">
							마카롱 주식회사 · 대표 장길웅<br> 사업자등록번호 : 620-01-203284 사업자정보확인통신판매업 :
							제 2021-성남분당<br> 사업장소재지 : 경기도 성남시 분당구 분당내곡로<br> 호스팅 서비스
							: 네이버 클라우드 ㈜
						</p>
					</div>
					<div class="col-12">
						<strong class="small-font2">신한은행 채무지급보증 안내</strong>
						<p class="small-font">
							당사는 고객님의 현금 결제 금액에 대해 신한은행과 채무지급보증 계약을 체결하여 안전거래를 보장하고 있습니다. <a
								href="" class="text-reset">서비스가입 사실 확인</a>
						</p>
						<p class="small-font">마카롱(주)는 통신판매 중개자로서 통신판매의 당사자가 아닙니다. 본
							상품은 개별판매자가 등록한 상품으로 상품, 상품정보, 거래에 관한 의무와 책임은 각 판매자에게 있습니다. 단,
							이용약관 및 정책, 기타 거래 체결 과정에서 고지하는 내용 등에 따라 검수하고 보증하는 내용에 대한 책임은
							마카롱(주)에 있습니다.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>



</body>
</html>