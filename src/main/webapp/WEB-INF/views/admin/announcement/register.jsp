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
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Insert title here</title>
</head>
<style>
#title {
	width: 100%;
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
	<form action="/admin/announcement/register" method="post">
	 <input type="hidden" name="id" value="0" readonly>
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
						<div class="col-12" align="center">공지사항 제목</div>
						<div class="col-12">
							<input type="text" name="title" id="title"
								placeholder="제목을 입력해주세요.">
						</div>
						<br>
						 <textarea name="contents" id="summernote"></textarea>
						<div class="col-12">
							<div class="row">
								<div class="col-12" align="right">
									<input type="submit" class="btn btn-outline-dark btn-custom" value="등록"> 
									<a href="/admin/announcement/list">
									<input type="button" class="btn btn-outline-dark btn-custom" value="취소"></a>
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

	<script type="text/javascript">
	$(document).ready(function() {
		   $('#summernote').summernote({
		        height: 300,                 // 에디터 높이
		        minHeight: null,             // 최소 높이
		        maxHeight: null,             // 최대 높이
		        focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		        lang: "ko-KR",               // 한글 설정
		        placeholder: '최대 2048자까지 쓸 수 있습니다'   //placeholder 설정
		   });
		});
	
	</script>
</body>

</html>