<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>


ul {
	margin: 0px;
	padding: 0px;
	list-style-type: none;
}

</style>

<div class="d-none d-md-block">
	<div class="w-100 mb-3">
		<strong>상품관리</strong>
		<ul class="fw-lighter">
			<li><a href="/admin/product/list" class="text-reset text-decoration-none">상품 정보 관리</a>
		</ul>
	</div>
	<div class="w-100 mb-3">
		<strong>회원 관리</strong>
		<ul class="fw-lighter">
			<li><a href="/admin/user/list" class="text-reset text-decoration-none">회원 계정 관리</a>
		</ul>
	</div>
	 <div class="w-100 mb-3">
    <strong>주소록 관리</strong>
    <ul class="fw-lighter">
      <li><a href="/admin/address/list" class="text-reset text-decoration-none">회원 주소 관리</a>
    </ul>
  </div>
	<div class="w-100 mb-3">
		<strong>주문 관리</strong>
		<ul class="fw-lighter">
				<li><a href="/" class="text-reset text-decoration-none">주문 내역</a>
		</ul>
	</div>
	<div class="w-100 mb-3">
		<strong>고객 센터 관리</strong>
		<ul class="fw-lighter">	
			<li><a href="/admin/announcement/list" class="text-reset text-decoration-none">공지사항</a>
			<li><a href="/admin/announcement/wait.jsp" class="text-reset text-decoration-none">자주 묻는 질문</a>
			<li><a href="/admin/announcement/wait.jsp" class="text-reset text-decoration-none">문의 내역</a>
		</ul>
	</div>
</div>


<div class="d-block d-md-none col-sm-2 w-100">
    <div class="btn-group w-100">
      <button type="button" class="btn btn-outline-dark dropdown-toggle"
      data-bs-toggle="dropdown" aria-expanded="false" style="width: 25rem;">Menu</button>
        <div class="dropdown-menu w-100" style="width: 25rem;">
            <div class="list-group justify-content-center"
              style="text-align: center;">
            <a href="/admin/product/list" class="list-group-item list-group-item-action">
            상품 정보 관리</a> 
            <a href="/admin/user/list" class="list-group-item list-group-item-action">
            회원 계정 관리</a> 
            <a href="/admin/address/list" class="list-group-item list-group-item-action">
            회원 주소록 관리</a> 
            <a href="#" class="list-group-item list-group-item-action">
            회원 주문 내역</a> 
            <a href="/admin/announcement/list" class="list-group-item list-group-item-action">
            공지 사항</a>
           <a href="/admin/announcement/wait.jsp" class="list-group-item list-group-item-action">
           자주 묻는 질문</a> 
           <a href="/admin/announcement/wait.jsp" class="list-group-item list-group-item-action">
           문의 내역</a>
      </div>
    </div>
  </div>
</div>