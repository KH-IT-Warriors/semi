<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" rel="stylesheet">
<title>Insert title here</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Jost:ital,wght@0,100;0,200;0,500;1,100;1,200;1,300&family=Noto+Sans+KR:wght@100;300;400;500;700&display=swap')
	;

* {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 14px;
}


#imgbox {
	width: 200px;
	height: 200px;
	line-height: 200px;
	border: 1px solid #ebebeb;
}

#detailbox {
	width: 200px;
	height: 500px;
	line-height: 500px;
	border: 1px solid #ebebeb;
}

#id {
display : none;
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

</head>

<body>

	<div class="container-md">
		<div class="row header">
			<jsp:include page="/WEB-INF/views/component/header.jsp"></jsp:include>
		</div>
		<div class="row body">
			<div class="col-md-2 left-side" style = "border-right:1px solid #ebebeb">
				<jsp:include page="/WEB-INF/views/component/leftside.jsp"></jsp:include>
			</div>
			<div class="col-sm-12 col-md-8">
				<form action="/admin/product/modify" method="post">
					<div class="row">
						<div class="col-3 justify-content-center text-center">
							<div class="w-100 mt-3" id="imgbox">
								<img src="" id="thumtarget" class="w-100 h-100">
							</div>
							<div class="mb-3 mt-3">
								<input class="form-control form-control-sm" id="thumformFileSm"
									type="file">
							</div>
						</div>
						<div class="col-9">
							<select class="mt-3" name = "category">
								<option selected>상품 카테고리</option>
								<option>여성</option>
								<option>남성</option>
								<option>라이프</option>
							</select> <br> 
							<input type="text" placeholder="상품 이름" class="mt-2" name = "name">
							<br> <input type="text" placeholder="상품 가격" class="mt-2" name="price">
							<br> <input type="text" placeholder="상품 수량" class="mt-2" name = "quantity">
							<br> <input type="text" placeholder="상품 수량" class="mt-2" name = "id" value = "${id}" id = "id">
							<textarea placeholder="상품 요약 설명" class="w-100 mt-2" rows="5" name = "summary"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col justify-content-center text-center">
							<div id="detailbox" class="w-100 mt-2">
								<img src="" id="detailtarget" class="w-100 h-100">
							</div>
							<div class="mb-3 mt-3" style="width: 250px;">
								<input class="form-control form-control-sm"
									id="detailformFileSm" type="file">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col text-end">
							<button class="btn btn-outline-dark btn-custom btn-sm mt-2 mb-2" type = "submit">수정완료</button>
						</div>
					</div>
				</form>
			</div>
			
			<div class="d-none d-md-block col-md-2"></div>
		</div>

    <hr class="mt-5 mb-5">
		<div class="row footer">
			<jsp:include page="/WEB-INF/views/component/footer.jsp"></jsp:include>
		</div>
	</div>



 <script>

    var thumbnail = document.querySelector('#thumformFileSm');
    var result;

    thumbnail.addEventListener('change', function () {
      reader.readAsDataURL(thumbnail.files[0]);
      reader.onload = function () {
        document.querySelector('#thumtarget').src = reader.result;
      }
    })

    var reader = new FileReader(thumbnail);
    reader.onload = function () {
      result = reader.result;
    }

    var detail = document.querySelector('#detailformFileSm');
    var result;

    detail.addEventListener('change', function () {
      reader.readAsDataURL(detail.files[0]);
      reader.onload = function () {
        document.querySelector('#detailtarget').src = reader.result;
      }

    })

    var reader = new FileReader(thumbnail);
    reader.onload = function () {
      result = reader.result;
    }

  </script>





</body>
</html>