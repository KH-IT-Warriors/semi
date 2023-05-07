<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="container-fluid">
  <div class="row">
    <div class="col-12 text-center">
      <h3>Log in</h3>
    </div>
  </div>
  <div class="row">
    <div class="col-12 d-flex justify-content-center">
      <form action="/login" method="post" class="account-form">
        <div class="form-floating">
          <input type="text" name="username" id="username" class="form-control input-forms"
                 placeholder="아이디를 입력해주세요.">
          <label for="username" class="input-forms-label">아이디를 입력해주세요.</label>
        </div>
        <div class="form-floating">
          <input type="password" name="password" id="password" class="form-control input-forms"
                 placeholder="비밀번호를 입력해주세요.">
          <label for="password" class="input-forms-label">비밀번호를 입력해주세요.</label>
        </div>
      </form>
    </div>
    <div class="col-12">
      <div class="link-bundle hstack gap-2 justify-content-end">
        <a href="/account/find_username" class="links">아이디 찾기</a>
        <div class="vr"></div>
        <a href="/account/find_password" class="links">비밀번호 찾기</a>
        <div class="vr"></div>
        <a href="/account/register" class="links">회원가입</a>
      </div>
    </div>
    <div class="col-12">
      <div class="login-button d-flex justify-content-between">
        <div class="form-check form-switch">
          <input class="form-check-input" type="checkbox" role="switch" id="switch">
          <label class="form-check-label" for="switch">스위치</label>
        </div>
        <button class="btn btn-outline-dark btn-custom" id="do-login">
          로그인
        </button>
      </div>
    </div>
  </div>
</div>