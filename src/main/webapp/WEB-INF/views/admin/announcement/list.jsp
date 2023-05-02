<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <title>Insert title here</title>
    </head>

    <body>
        <form action="/register" method="get">
            <div class="container">
                <div class="row header"></div>
                <div class="row body">
                    <div class="col-3 left-menu">
                        <strong align="center">상품 관리</strong>
                        <ul class="list-group">
                            <li class="list-group-item">상품 정보 관리</li>
                        </ul>
                        <strong align="center">회원 관리</strong>
                        <ul class="list-group">
                            <li class="list-group-item">회원 계정 관리</li>
                        </ul>
                        <strong align="center">관리자 관리</strong>
                        <ul class="list-group">
                            <li class="list-group-item">관리자 계정 관리</li>
                        </ul>
                        <strong align="center">주문 관리</strong>
                        <ul class="list-group">
                            <li class="list-group-item">주문 내역 관리</li>
                        </ul>
                        <strong align="center">고객 센터 관리</strong>
                        <ul class="list-group">
                            <li class="list-group-item">공지사항</li>
                            <li class="list-group-item">자주 묻는 질문</li>
                            <li class="list-group-item">1:1 문의 내역</li>
                        </ul>
                    </div>
                    <div class="col-7">
                        <div class="row">
                            <div class="col-10" align="center"> 공지 사항</div>
                            <div class="col-2" align="center"><input type="submit" value="등록"></div>
                            <div class="col-12 announcement-list">
                                <div class="row">
                                    <div class="col-2" align="center">No</div>
                                    <div class="col-10" align="center">제목</div>
                                    <c:forEach var="i" items="${announcements}">
                                        <div class="col-6">${i.id}</div>
                                        <div class="col-6">${i.title}</div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
                <div class="row footer"></div>
            </div>
        </form>
    </body>

    </html>