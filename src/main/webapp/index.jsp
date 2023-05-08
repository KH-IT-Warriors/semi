<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
  rel="stylesheet">
<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
<link
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
  rel="stylesheet">
<title>JSP - Hello World</title>

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
  width : 177px;
  
}

#bannertext {
  font-style: italic;
  font-size: x-large;
  font-weight: bold;
  border-bottom: 1px solid #ebebeb;
}

#imgbox {
  background-color: white;
  width: 200px;
  height: 200px;
  float: left;
  transition: all 0.2s linear;
}

#imgbox:hover {
  transform: scale(1.1);
  cursor: pointer;
}

.c-ip-circle-text__svg {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border-radius: 50%;
  animation: c-ip-circle-text-rotation 10s linear infinite;
  animation-duration: 10s;
  animation-timing-function: linear;
  animation-delay: 0s;
  animation-iteration-count: infinite;
  animation-direction: normal;
  animation-fill-mode: none;
  animation-play-state: running;
  animation-name: c-ip-circle-text-rotation;
}

.elementor-widget-container {
  z-index: 30;
}

.flow-text {
  display: flex;
  flex: 0 0 auto;
  white-space: nowrap;
  overflow: hidden;
  transition: 0.3s;
  font-size: 2.5rem;
  font-weight: 300;
  color: #000000;
}

.flow-text:hover {
  color: #000;
}

.flow-text:hover .flow-wrap {
  animation-play-state: paused;
  cursor: pointer;
}

.flow-wrap {
  animation: textLoop 10s linear infinite;
  padding-right: 1.4881vw;
  font-weight: lighter;
}

#title {
  width: 100%;
}

.term_list {
  list-style-type: none;
  padding: 0px;
  margin: 0px;
  float: left;
}

.term_item {
  float: left;
}

.faq {
  background-color: black;
  color: white;
}

.small-font {
  font-size: 10px;
}

.small-font2 {
  font-size: 12px;
}

.service_area>ul>li {
  color: rgba(34, 34, 34, .5);
  float: none;
}

.footli {
  float: none;
  font-family: 'Noto Sans KR', sans-serif;
  font-size: 14px;
}


  @keyframes c-ip-circle-text-rotation {
      0% {
        transform: rotate(0deg);
      }

      100% {
        transform: rotate(-1turn);
      }
    }

    @keyframes textLoop {

      0% {
        -webkit-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
      }

      100% {
        -webkit-transform: translate3d(-100%, 0, 0);
        transform: translate3d(-100%, 0, 0);
      }
    }

    @keyframes textLoop {

      0% {
        -webkit-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
      }

      100% {
        -webkit-transform: translate3d(-100%, 0, 0);
        transform: translate3d(-100%, 0, 0);
      }
    }

</style>
</head>
<body>
  <div class="container-fluid">
    <div class="row sticky-top header"
      style="height: 120px; background-color: white;">
      <div class="d-none d-md-block col-2">
        <div style="height: 120px;">
          <img src="/resources/image/macaronLOGO.png" class="w-100 h-70 mt-4 ms-2">
        </div>
      </div>
      <div class="d-block d-md-none col-4">
        <div style="height: 100px;" class="mt-3">
          <img src="/resources/image/macaronLOGO.png" class="w-100 h-70 mt-4">
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
                <li class="nav-item"><a class="nav-link" href="/user/product/list" id="shop">Shop</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#">Event</a>
                </li>
                <li class="nav-item"><a class="nav-link disabled" href="#"
                  tabindex="-1" aria-disabled="true">LookBook</a></li>
              </ul>
            </div>
          </div>
          <div style="width: 170px;">
            <input type="text" id="search" placeholder="상품검색">
          </div>
          <div style="float: left; width: 50px;" class="d-none d-md-block">
            <i class="fa-solid fa-magnifying-glass" style="color: #aaaaaa;"></i>
          </div>
        </nav>
      </div>
      <div class="d-none d-md-block col-2">
        <ul class="m-4 w-100">
          <li style="float: left;" class="text-center mt-4"><a
            href="/admin/logIn"
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
            href="/admin/announcement/list" class="text-reset text-decoration-none"> <i
              class="fa-solid fa-hand-holding-heart" style="color: #04a9ad;"></i></a>
            <div style="font-size: xx-small; text-align: center;">Help</div></li>
        </ul>
      </div>
      <div class="d-block d-md-none col-8 d-flex justify-content-center">
        <ul class="mt-5">
          <li class="mt-2"><input type="text" id="search"
            placeholder="Macaroon.com"> <i
            class="fa-solid fa-magnifying-glass" style="color: #aaaaaa;"></i>
          </li>
          <li class="mt-2 ms-3"><a
            href="/WEB-INF/views/admin/user/login.jsp"
            class="text-reset text-decoration-none"> <i
              class="fa-solid fa-user" style="color: #aaaaaa;"></i>
          </a></li>
          <li class="mt-2 ms-3"><i class="fa-solid fa-cart-shopping"
            style="color: #aaaaaa;"></i></li>
          <li class="mt-2 ms-3"><i class="fa-solid fa-heart"
            style="color: #eb1062;"></i></li>
        </ul>
      </div>
    </div>
    <div class="row body">
      <!-- <div class="d-none d-md-block col-2"></div> -->
      <div class="col-12 col-md-12 text-center mb-3 mt-2">
        <div class="d-none d-md-block col-12 text-center mb-3"
          style="height: 500px;">
          <div id="carouselExampleControls" class="carousel slide h-100"
            data-bs-ride="carousel">
            <div class="carousel-inner h-100">
              <div class="carousel-item active" class="w-100 h-100">
                <img src="/resources/image/bannerimg2.gif" class="d-block w-100 h-100" alt="...">
              </div>
              <div class="carousel-item ">
                <img src="/resources/image/bannerimg3_1.png" class="d-block w-100 h-50" alt="...">
              </div>
              <div class="carousel-item">
                <img src="/resources/image/bannerimg1.jpg" class="d-block w-100 h-100" alt="...">
              </div>
            </div>
            <button class="carousel-control-prev" type="button"
              data-bs-target="#carouselExampleControls" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button"
              data-bs-target="#carouselExampleControls" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>
        <div>
          <span id="bannertext" class="text-start">New item</span>
        </div>
        <div class="mt-3 w-100 d-flex justify-content-center">
          <div id="imgbox" class="d-none d-md-block m-1">
            <img src="/resources/image/product1.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-block d-md-none "
            style="border-radius: 50%; border: none;">
            <img src="/resources/image/product1.jpg" class="w-100 h-100"
              style="border-radius: 50%;">
          </div>
          <div id="imgbox" class="d-none d-md-block m-1">
            <div class="elementor-widget-container d-none d-md-block"
              style="position: absolute; margin-top: 38px; margin-left: 29px;">
              <div class="c-ip-circle-text">
                <svg viewBox="0 0 140 140" width="140" height="140"
                  class="c-ip-circle-text__svg">
                  <defs>
                    <path id="circle-f0ef3aa"
                    d=" M 70, 70 m -59.8, 0 a 59.8,59.8 0 1,1 119.6,0 a 59.8,59.8 0 1,1 -119.6,0"></path>
                  </defs> <text font-size="12" class="c-ip-circle-text__title"
                    style="fill:#cf1338">
                    <textPath xlink:href="#circle-f0ef3aa"> MACAROON ・ HIGH QUALITY ・ TIMELESS DESIGN ・ FOR YOU ・
                    </textPath>
                  </text>
                </svg>
              </div>
            </div>
            <img src="/resources/image/product2.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-block d-md-none "
            style="border-radius: 50%; border: none; background-color: #ffc410">
            <img src="/resources/image/product2.jpg" class="w-100 h-100"
              style="border-radius: 50%;">
          </div>
          <div id="imgbox" class="d-none d-md-block m-1">
            <img src="/resources/image/product3.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-block d-md-none "
            style="border-radius: 50%; border: none;">
            <img src="/resources/image/product3.jpg" class="w-100 h-100"
              style="border-radius: 50%;">
          </div>
          <div id="imgbox" class="d-none d-md-block m-1">
            <img src="/resources/image/product4.jpg" class="w-100 h-100">
          </div>
        </div>
      </div>
      <!-- <div class="d-none d-md-block col-2"> </div> -->
    </div>

    <div class="row body">
      <div class="col-12 mt-4">
        <a href="/"><img src="/resources/image/bannerimg4.png" class="w-100 h-100"></a>
      </div>
    </div>

    <div class="row d-none d-md-block body"
      style="border-top: 1px solid black; border-bottom: 1px solid;">
      <div class="col-12 mt-3 mb-3">
        <div class="flow-container">
          <div class="flow-text">
            <div class="flow-wrap">・ © Macaroon.Kr</div>
            <div class="flow-wrap">・ © Macaroon.Kr</div>
            <div class="flow-wrap">・ © Macaroon.Kr</div>
            <div class="flow-wrap">・ © Macaroon.Kr</div>
            <div class="flow-wrap">・ © Macaroon.Kr</div>
            <div class="flow-wrap">・ © Macaroon.Kr</div>
            <div class="flow-wrap">・ © Macaroon.Kr</div>
            <div class="flow-wrap">・ © Macaroon.Kr</div>
          </div>
        </div>
      </div>
    </div>
    <div class="row body">
      <div class="col-12  mt-4 mb-3 w-100">
        <div class="d-none d-md-block mb-2">
          <span class="ms-4"
            style="font-weight: bold; border-bottom: 1px solid #ebebeb;">Most
            Popular </span>
        </div>
        <div class="d-block d-md-none mb-3 d-flex justify-content-center">
          <span class="ms-4"
            style="font-weight: bold; font-size: 24px; font-style: italic; border-bottom: 1px solid #ebebeb;">Most
            Popular </span>
        </div>
        <div class="w-100 d-flex justify-content-center">
          <div id="imgbox" class="d-none d-md-block ms-4">
            <img src="/resources/image/product5.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-block d-md-none ms-4"
            style="border-radius: 50%;">
            <img src="/resources/image/product5.jpg" class="w-100 h-100"
              style="border-radius: 50%;">
          </div>
          <div id="imgbox" class="d-none d-md-block ms-3">
            <img src="/resources/image/product6.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-none d-md-block ms-3">
            <img src="/resources/image/product7.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-none d-md-block ms-3">
            <img src="/resources/image/product8.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-none d-md-block ms-3">
            <img src="/resources/image/product9.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-block d-md-none ms-4"
            style="border-radius: 50%; background-color: #04a9ad;">
            <img src="/resources/image/product10.jpg" class="w-100 h-100"
              style="border-radius: 50%;">
          </div>
          <div id="imgbox" class="d-none d-md-block ms-3">
            <img src="/resources/image/product10.jpg" class="w-100 h-100">
          </div>
          <div id="imgbox" class="d-block d-md-none ms-4"
            style="border-radius: 50%;">
            <img src="/resources/image/product7.jpg" class="w-100 h-100"
              style="border-radius: 50%;">
          </div>
        </div>
      </div>
    </div>
    <div class="row body">
      <div class="col-12 mt-3">
        <div class="w-100 h-100">
          <img src="/resources/image/bottombanner.gif" class="w-100 h-100">
        </div>
      </div>
    </div>
    <hr class="mt-5">
    <div class="row footer mt-5">
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
</body>
</html>