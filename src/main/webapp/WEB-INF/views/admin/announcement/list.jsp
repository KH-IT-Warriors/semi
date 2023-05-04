<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Insert title here</title>
</head>
<style>
  .ann-btn{
    background-color:white;
    color: black; 
  }
</style>
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
					<div class="col-12 announcement-list" align="center">
						<h3>공지사항</h3>
						<div class="row">
							<div class="col-2" align="center">No</div>
							<div class="col-8" align="center">제목</div>
							<div class="col-2">
								<button class="ann-btn">
									<a href="/admin/announcement/register" class="text-reset">공지 등록</a>
								</button>
							</div>
							<c:forEach var="announcements" items="${announcements}">
								<div class="col-2">${announcements.id}</div>
								<div class="col-10"><a href="/admin/announcement/item?id=${announcements.id}">${announcements.title}</a></div>
							</c:forEach>

							<div>
								<c:forEach var="item" items="${pageNavi}" varStatus="status">
									<c:choose>
										<c:when test="${search == null}">
											<c:choose>
												<c:when test="${item eq '<'}">
													<a
														href="/admin/announcement/list?page-number=${pageNavi[status.index+1]-1}">${item}</a>
												</c:when>
												<c:when test="${item eq '>'}">
													<a
														href="/admin/announcement/list?page-number=${pageNavi[status.index-1]+1}">${item}</a>
												</c:when>
												<c:otherwise>
													<a href="/admin/announcement/list?page-number=${item}">${item}</a>
												</c:otherwise>
											</c:choose>
										</c:when>
									</c:choose>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="d-none d-md-block col-md-2">body side</div>
		</div>
		<div class="row footer">
			<jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>
		</div>
	</div>
</body>

</html>