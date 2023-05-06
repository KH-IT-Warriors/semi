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
    <div class="col-md-2 left side">
      <jsp:include page="/WEB-INF/views/component/leftside.jsp"></jsp:include>
    </div>
    <div class="col-sm-12 col-md-8">
      <jsp:include page="/WEB-INF/views/admin/user/list-1.jsp"></jsp:include>
    </div>
    <div class="d-none d-md-block col-md-2">body side</div>
  </div>
  <hr class="mt-3 mb-3">
  <div class="row footer">
    <jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>
  </div>
</div>
<script src='${pageContext.request.contextPath}/resources/js/register.js'></script>
</body>
</html>