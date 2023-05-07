<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class='container-fluid'>
  <div class='row'>
    <div class="col-12">
      <h3 class="title">Account</h3>
    </div>
  </div>
  <div class='col-12 d-flex justify-content-start'>
    <form action="/admin/user/list" method="get" id="search-form">
      <select name="amount">
        <option value="10">10</option>
        <option value="15">15</option>
        <option value="20">20</option>
      </select>
      <select name="type">
        <option value="admin">관리자</option>
        <option value="normal">일반 사용자</option>
      </select>
      <span class='d-flex justify-content-center'>
        <button class='btn btn-light align-self-center' id='search-button'>🔍</button>
        <input class='form-control input-forms mb-4' type='text' name='keyword' id='search'>
      </span>
    </form>
  </div>
  <div class='col-12 d-flex justify-content-between'>
    <C:choose>
      <C:when test="${requestScope.criteria.type == 'admin'}">
        <button class='btn btn-outline-dark btn-sm-custom mt-2 mb-5 to-normal' id='switch-user-type'>일반
          사용자
        </button>
      </C:when>
      <C:otherwise>
        <button class='btn btn-outline-dark btn-sm-custom mt-2 mb-5 to-admin' id='switch-user-type'>관리자</button>
      </C:otherwise>
    </C:choose>
    <button class='btn btn-outline-dark btn-sm-custom mt-2 mb-5' id='add-account'>추가</button>
  </div>
  <div class='row'>
    <div class='col-12'>
      <p>Account List</p>
      <ul>
        <C:forEach items='${requestScope.users}' var='i'>
          <li>
            <div class='d-flex'>
              <div>
                <img class='profile-img' src='' alt='프사'>
              </div>
              <div>
                <C:choose>
                  <C:when test="${requestScope.criteria.type == 'admin'}">
                    <p>Admin User ID : <a
                      href="/admin/user/item?id=${i.account.id}">${i.account.username}</a>
                    </p>
                  </C:when>
                  <C:otherwise>
                    <p>Normal User ID : <a
                      href="/admin/user/item?id=${i.account.id}">${i.account.username}</a>
                    </p>
                  </C:otherwise>
                </C:choose>
                <p>${i.role.name}</p>
              </div>
            </div>
          </li>
        </C:forEach>
      </ul>
    </div>
  </div>
  <div class='row'>
    <div class='col-12'>
      <C:choose>
        <C:when test="${requestScope.criteria.keyword == ''}">
          <C:forEach items="${requestScope.navi}" var="i" varStatus="status">
            <C:if test="${i == '<'}">
              <a
                href="/admin/user/list?page-number=${requestScope.navi[1]-1}&type=${requestScope.criteria.type}">
                &lt;
              </a>
            </C:if>
            <a href="/admin/user/list?page-number=${i}&type=${requestScope.criteria.type}">${i}</a>
            <C:if test="${i == '>'}">
              <a
                href="/admin/user/list?page-number=${requestScope.navi[status.index - 1] + 1}&type=${requestScope.criteria.type}">
                &gt;
              </a>
            </C:if>
          </C:forEach>
        </C:when>
        <C:otherwise>
          <C:forEach items="${requestScope.navi}" var="i" varStatus="status">
            <C:if test="${i == '<'}">
              <a
                href="/admin/user/list?page-number=${requestScope.navi[1]-1}&keyword=${requestScope.criteria.keyword}&type=${requestScope.criteria.type}">
                &lt;
              </a>
            </C:if>
            <a
              href="/admin/user/list?page-number=${i}&keyword=${requestScope.criteria.keyword}&type=${requestScope.criteria.type}">${i}</a>
            <C:if test="${i == '>'}">
              <a
                href="/admin/user/list?page-number=${requestScope.navi[status.index - 1] + 1}&keyword=${requestScope.criteria.keyword}&type=${requestScope.criteria.type}">
                &gt;
              </a>
            </C:if>
          </C:forEach>
        </C:otherwise>
      </C:choose>
    </div>
  </div>
</div>