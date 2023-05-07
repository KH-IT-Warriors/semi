<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="container-fluid p-0">
  <div class="row">
    <div class="col-12">
      <h3 class="title">Modify</h3>
    </div>
  </div>
  <div class="row">
    <div
      class="col-lg-2 col-12 d-flex flex-lg-column flex-row justify-content-lg-start justify-content-between">
      <C:if test="${requestScope.user.account.roleId == 1}">
        <img src="/resources/icon/woman-head.png" alt="프로필 사진" class="profile-img mb-lg-2 mb-0">
      </C:if>
      <C:if test="${requestScope.user.account.roleId != 1}">
        <img src="/resources/icon/man-head.png" alt="프로필 사진" class="profile-img mb-lg-2 mb-0">
      </C:if>
    </div>
    <div class="col-lg-10 col-12 p-sm-0 p-1">
      <form action="/admin/user/modify" method="post" id="modify-form">
        <input type="hidden" name="account-id" value="${requestScope.user.account.id}">
        <input type="hidden" name="status-id" value="${requestScope.user.account.statusId}">
        <ul>
          <li>
            <label>
              회원 아이디
            </label>
            <input type="text" name="username" id="username"
                   value="${requestScope.user.account.username}" readonly>
            <div class="blank-div">
            </div>
          </li>
          <li>
            <label>
              회원 비밀번호
            </label>
            <input type="password" name="dummy" id="password" class="modify-input" readonly>
            <div class="blank-div">
              <button type="button" id="pw-modify" class="btn btn-outline-dark btn-sm-custom">Modify</button>
            </div>
          </li>
          <li>
            <label>
              회원 이름
            </label>
            <input type="text" name="name" id="name" value="${requestScope.user.profile.name}"  class="modify-input">
            <div class="blank-div">
            </div>
          </li>
          <li>
            <label>
              회원 연락처
            </label>
            <input type="text" name="phone-number" id="phone-number"
                   value="${requestScope.user.profile.phoneNumber}" class="modify-input">
            <div class="blank-div">
            </div>
          </li>
          <li>
            <label>
              회원 이메일
            </label>
            <input type="text" name="email" id="email" value="${requestScope.user.profile.email}" class="modify-input">
            <div class="blank-div">
            </div>
          </li>
          <li>
            <label>
              회원 가입일
            </label>
            <input type="text" name="created" id="created" value="${requestScope.user.profile.created}" readonly>
            <div class="blank-div">
            </div>
          </li>
          <li>
            <label>
              마일리지
            </label>
            <input type="text" name="mileage" id="mileage" value="${requestScope.user.profile.mileage}" class="modify-input">
            <div class="blank-div">
            </div>
          </li>
          <li>
            <label>
              최근 접속시간
            </label>
            <input type="text" name="last-login" id="last-login"
                   value="${requestScope.user.profile.lastLogin}" readonly>
            <div class="blank-div">
            </div>
          </li>
          <li>
            <label>
              회원 유형
            </label>
            <input type="text" id="role-id" name="role-id" value="${requestScope.user.role.name}" readonly class="modify-input">
            <select id="role">
              <option value="1">일반 사용자</option>
              <option value="2">관리 팀장</option>
              <option value="3">선임 관리자</option>
              <option value="4">일반 관리자</option>
            </select>
          </li>
          <li>
            <label>
              회원 등급
            </label>
            <input type="text" id="grade-id" name="grade-id" value="${requestScope.grade.name}" readonly class="modify-input">
            <select id="grade">
              <option value="1">실버</option>
              <option value="2">골드</option>
              <option value="3">VIP</option>
              <option value="4">VVIP</option>
            </select>
          </li>
        </ul>
      </form>
    </div>
  </div>
  <div class="modify-button-bar d-flex justify-content-end pe-4">
    <a href="/admin/user/item?id=${requestScope.user.account.id}">
      <button type="button" class="btn btn-outline-secondary btn-sm-custom cancel me-2">
        Cancel
      </button>
    </a>
    <button type="button" class="btn btn-outline-dark btn-sm-custom btn-mod align-self-end">
      Confirm
    </button>
  </div>
  </form>
</div>
<form action="/admin/user/uploadImage" method="post" enctype="multipart/form-data" id="profile-img-form">
  <input type="hidden" name="target-id" value="${requestScope.user.account.id}">
  <input type="file" class="hidden" accept="image/*" id="profile-img">
</form>