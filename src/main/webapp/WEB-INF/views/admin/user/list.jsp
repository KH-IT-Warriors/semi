<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" rel="stylesheet">
  <link href='${pageContext.request.contextPath}/resources/css/register.css' rel='stylesheet'>
  <title>Register</title>

</head>

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
      <jsp:include page="/WEB-INF/views/admin/user/list-1.jsp"></jsp:include>
    </div>
    <div class="d-none d-md-block col-md-2">body side</div>
  </div>
  <hr class="my-3">
  <div class="row footer">
    <jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>
  </div>
</div>
<script src='${pageContext.request.contextPath}/resources/js/register.js'></script>
</body>
</html>