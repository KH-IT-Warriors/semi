<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="container-fluid p-0">
  <div class="row">
    <div class="col-12">
      <h3 class="title w-75">Profile</h3>
    </div>
  </div>
  <div class="row">
    <div class="col-md-2 col-12 d-flex flex-md-column flex-row justify-content-md-start justify-content-between">
      <img src="" alt="프로필 사진 업로드" class="profile-img mb-md-2 mb-0">
    </div>
    <div class="col-md-10 col-12 p-sm-0 p-1">
      <ul>
        <li>
          <label>
            회원 아이디
          </label>
          <input type="text" name="username" id="username" readonly value="${requestScope.user.account.username}">
        </li>
        <li>
          <label>
            회원 비밀번호
          </label>
          <input type="password" name="password" id="password" readonly>
        </li>
        <li>
          <label>
            회원 이름
          </label>
          <input type="text" name="name" id="name" readonly value="${requestScope.user.profile.name}">
        </li>
        <li>
          <label>
            회원 연락처
          </label>
          <input type="text" name="phone-number" id="phone-number" readonly
                 value="${requestScope.user.profile.phoneNumber}">
        </li>
        <li>
          <label>
            회원 이메일
          </label>
          <input type="text" name="email" id="email" readonly value="${requestScope.user.profile.email}">
        </li>
        <li>
          <label>
            회원 가입일
          </label>
          <input type="text" name="created" id="created" readonly value="${requestScope.user.profile.created}">
        </li>
        <li>
          <label>
            마일리지
          </label>
          <input type="text" name="mileage" id="mileage" readonly value="${requestScope.user.profile.mileage}">
        </li>
        <li>
          <label>
            최근 접속시간
          </label>
          <input type="text" name="last-login" id="last-login" readonly
                 value="${requestScope.user.profile.lastLogin}">
        </li>
        <li>
          <label>
            회원 유형
          </label>
          <input type="text" id="role-id" name="role-id" readonly value="${requestScope.user.role.name}">
        </li>
        <li>
          <label>
            회원 등급
          </label>
          <input type="text" id="grade-id" name="grade-id" readonly value="${requestScope.grade.name}">
        </li>
      </ul>
    </div>
  </div>
  <div class="d-flex justify-content-end pe-4">
    <a href="/admin/user/list?page-number=${requestScope.criteria.page-number}&type=${requestScope.criteria.type}">
      <button type="button" class="btn btn-outline-dark btn-sm-custom me-2">
        Return
      </button>
    </a>
    <form action="/admin/user/delete" method="post" class="d-flex justify-content-md-start justify-content-end"
          id="delete-form">
      <input type="hidden" name="target-id" value="${requestScope.user.account.id}">
      <button type="button" class="btn btn-outline-dark btn-sm-custom btn-del align-self-end me-2">
        Delete
      </button>
    </form>
    <a href="/admin/user/modify?id=${requestScope.user.account.id}">
      <button type="button" class="btn btn-outline-dark btn-sm-custom">
        Modify
      </button>
    </a>
  </div>
</div>