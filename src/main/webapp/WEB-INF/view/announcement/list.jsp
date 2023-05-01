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
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Insert title here</title>
<style>
div {
	border: 1px solid black;
}
</style>
</head>
<body>
	<form action="/item" method="get">
		<div class="container">
			<div class="row header"></div>
			<div class="row body">
				<div class="col-3 left-menu">
					<div class="row">
						<div class="col-12" align="center">고객센터</div>
						<div class="col-12">
							<ul class="list-group">
								<li class="list-group-item">공지사항</li>
								<li class="list-group-item">자주 묻는 질문</li>
								<li class="list-group-item">1:1 문의</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-7 center-announcement">
					<div class="row">
						<div class="col-12" align="center">공지사항</div>
						<div class="col-12 list-announcement">
						<c:forEach var="i" items="${announcements }">
						<div>${i.title}</div>
						</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-2 right"></div>
				<div class="col-12">
					<div class="row search" align="center">
						<div class="col-12 search-box">
							<select name="serch" id="serch">
								<option value="title">제목</option>
								<option value="contents">내용</option>
								<option value="writer">글쓴이</option>
							</select> <input type="text" name="announcement-search"
								placeholder="공지 검색" size="50px"> <input type="submit"
								value="검색">
						</div>
					</div>
				</div>
			</div>
			<div class="row footer"></div>
		</div>
	</form>
</body>
</html>