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
<style type="text/css">
  #contents{
   min-height: 400px; 
  }
</style>
</head>
<body>
	<form action="/admin/announcement/delete" method="post">
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
						<div class="col-12 announcement-item">
							<div class="row">
								<div class="col-2" align="center">${announcement.id}</div>
								<input type="text" name="id" value="${announcement.id}" style="display: none;">
								<div class="col-8" align="center">${announcement.title}</div>
							</div>
							<div class="col-12">
								<div id="contents">${announcement.contents}</div>
								<div class="col-12" align="right">
									<a href="/admin/announcement/modify?id=${announcement.id}"><input type="button" value="수정"> </a> 
									<input type="submit" value="삭제">
									<a href="/admin/announcement/list">
									<input type="button" value="목록으로"></a>
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