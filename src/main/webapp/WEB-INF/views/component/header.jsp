<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<style>
  /*@font-face {*/
  /*  font-family: "intervogue";*/
  /*  src: url(/resources/font/intervogue-light.otf);*/
  /*  font-weight: normal;*/
  /*  font-style: normal;*/
  /*}*/

  .header-box{
    border-bottom: 1px solid #ebebeb;
    margin-bottom: 12px;
  }

  .row {
    margin: 0px;
  }

  .logo {
    width: 180px;
    height: 51px;
    background-image: url(/resources/image/macaronLOGO.png);
    background-size: 100%;
  }

  a {
    text-decoration: none;
  }

  .header-btn {
    padding: 6px;
    background-color: #ffffff00;
    --bs-btn-hover-bg: #00000015;
    border: none;
    min-width: 90px;
    margin-right: 0.7rem;
  }

  i {
    color: black;
  }

  i > span {
    /*font-family: "intervogue";*/
  }

  #head-left {
    display: inline-block;
    padding: 0.5rem;
    background-color: #00000005;
    border: 1px solid #00000002;
    border-radius: 15px;
  }

  .logo-text {
    display: flex;
    justify-content: center;
    padding: 0px;
    margin: 0px;
  }
</style>
<div class="container-fluid p-0 header-box">
  <div class="row p-0">
    <div class="col-4 d-none d-md-flex justify-content-center align-items-center p-0">
      <span id="head-left">관리자 페이지</span>
    </div>
    <div class="col-md-4 col-10 d-flex justify-content-center align-items-center p-0">
      <a href="/admin/index">
        <div class="logo">
        </div>
      </a>
    </div>
    <div class="col-md-4 col-2 d-flex justify-content-center align-content-start flex-md-row flex-column p-0">
      <button class="btn btn-light align-self-center header-btn">
        <a href="/admin/logIn">
          <i class="fa-solid fa-key">
            <span>Log in</span>
          </i>
        </a>
      </button>
      <button class="btn btn-light align-self-center header-btn">
        <a href="/admin/logOut">
          <i class="fa-solid fa-right-from-bracket">
            <span>Log out</span>
          </i>
        </a>
      </button>
    </div>
    <div class="col-10 col-md-12 logo-text mb-2">
      Macaron
    </div>
    <div class="col-2 d-md-none"></div>
  </div>
</div>