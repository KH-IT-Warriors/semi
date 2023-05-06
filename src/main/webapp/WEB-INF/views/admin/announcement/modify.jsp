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
<title>Insert title here</title>
</head>
<body>
	<form action="/admin/announcement/modify" method="post">
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
						<div class="col-12 announcement-item" align="center">
							<div class="row">
								<div class="col-2">
								  <input type="text" name="id" value="${announcement.id}" readonly>
								</div>
								<div class="col-8">
									<input type="text" name="title" value="${announcement.title}">
								</div>
							</div>
							<div class="col-12">
								<textarea name="contents" id="contents" cols="100" rows="20">${announcement.contents}</textarea>
								<div class="col-12" align="right">
									<input type="submit" value="수정"> 
									<a href="/admin/announcement/list"> <input type="button"
										value="목록으로"></a>
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
	</form>
</body>
</html>