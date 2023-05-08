<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.2.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.2.3/js/bootstrap.bundle.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
    crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<style type="text/css">
.left-side{
  border-right: 1px solid #ebebeb; 
}
  #contents{
   width:100%;
   min-height: 400px; 
   background-color: #ebebeb50;
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
</style>
<body>
	<form action="/admin/announcement/modify" method="post">
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
						<div class="col-12 announcement-item">
							<div class="row">
								<div class="col-2" align="center">
								  <input type="text" name="id" value="${announcement.id}" readonly>
								</div>
								<div class="col-8" align="center">
									<input type="text" name="title" value="${announcement.title}">
								</div>
								<hr>
							</div>
							<div class="col-12">
								<textarea type="text" name="contents" id="contents" rows="20">${announcement.contents}</textarea>
								<div class="col-12" align="right">
									<input type="submit" class="btn btn-outline-dark btn-custom" value="수정"> 
									<a href="/admin/announcement/list"> 
									<input type="button" class="btn btn-outline-dark btn-custom" value="목록으로"></a>
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
	</form>
</body>
</html>