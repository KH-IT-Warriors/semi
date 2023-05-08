<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.2.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.2.3/js/bootstrap.bundle.js"></script>
  <script src="${pageContext.request.contextPath}/webjars/jquery/3.6.4/jquery.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
    crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<style>
.title {
	margin: 1rem 0px 3rem 0px;
	border-bottom: 2px solid black;
	padding-bottom: 0.7rem;
	text-indent: 1rem;
}

#line-box {
	min-height: 40px;
	line-height: 40px;
}

.btn-custom {
	padding: 8px 12px 8px 12px;
	margin: 0px 0px 0px 10px;
	font-size: 10pt;
	border-radius: 15px;
	background-color: #C6AD8A;
	border-radius: 0.5rem;
	border: none;
}

.left-side {
	border-right: 1px solid #ebebeb;
}
</style>
<body>
	<div class="container-md">
		<div class="row header">
			<jsp:include page="/WEB-INF/views/component/header.jsp"></jsp:include>
		</div>
		<div class="row body">
			<div class="col-md-2 left-side">
				<jsp:include page="/WEB-INF/views/component/leftside.jsp"></jsp:include>
			</div>
			<div class="col-sm-12 col-md-8">
				<div class="row">
					<div class="col-12 announcement-list">
						<h3 class="title">공지사항</h3>
						<div class="row">
							<form class="d-flex">
								<div class="col-11 mb-2">
									<input class="form-control me-3" name="keyword" type="search" placeholder="상품 검색" aria-label="Search" id="search">
								</div>
								<div class="col-1 mb-2" align="center">
									<button class="btn btn-light btn-sm" type="submit">Search</button>
								</div>
							</form>
						</div>
						<div class="row">
							<div class="col-2" id="line-box" align="center">No</div>
							<div class="col-8" id="line-box" align="center">제목</div>
							<div class="col-2" align="center">
								<button class="btn btn-outline-dark btn-custom">
									<a href="/admin/announcement/register" class="text-reset">공지
										등록</a>
								</button>
							</div>
							<c:forEach var="announcements" items="${announcements}">
								<div class="col-2" id="line-box"
									style="border: 1px solid #ebebeb">${announcements.id}</div>
								<div class="col-10" id="line-box"
									style="border: 1px solid #ebebeb">
									<a class="text-reset"
										href="/admin/announcement/item?id=${announcements.id}">${announcements.title}</a>
								</div>
							</c:forEach>

							<div class="text-center">
								<c:forEach var="item" items="${pageNavi}" varStatus="status">
									<c:choose>
										<c:when test="${keyword == null}">
											<c:choose>
												<c:when test="${item eq '<'}">
													<a class="text-reset"
														href="/admin/announcement/list?page-number=${pageNavi[status.index+1]-1}">${item}</a>
												</c:when>
												<c:when test="${item eq '>'}">
													<a class="text-reset"
														href="/admin/announcement/list?page-number=${pageNavi[status.index-1]+1}">${item}</a>
												</c:when>
												<c:otherwise>
													<a class="text-reset"
														href="/admin/announcement/list?page-number=${item}">${item}</a>
												</c:otherwise>
											</c:choose>
										</c:when>
									</c:choose>

									<c:choose>
										<c:when test="${keyword != null}">
											<c:choose>
												<c:when test="${item eq '<'}">
													<a class="text-reset"
														href="/admin/announcement/list?page-number=${pageNavi[status.index+1]-1}&search=${search}">${item}</a>
												</c:when>
												<c:when test="${item eq '>'}">
													<a class="text-reset"
														href="/admin/announcement/list?page-number=${pageNavi[status.index-1]+1}&search=${search}">${item}</a>
												</c:when>
												<c:otherwise>
													<a class="text-reset"
														href="/admin/announcement/list?page-number=${item}&search=${search}">${item}</a>
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
			<div class="d-none d-md-block col-md-2"></div>
		</div>
		<hr class="my-3">
		<div class="row footer">
			<jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>
		</div>
	</div>
</body>

</html>