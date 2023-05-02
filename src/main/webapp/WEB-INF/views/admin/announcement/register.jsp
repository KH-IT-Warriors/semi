<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <title>Insert title here</title>
    </head>
    <style>
        div {
            border: 1px solid black;
        }

        #title {
            width: 100%;
        }
    </style>

    <body>
        <form action="/admin/announcement/register" method="post">
            <div class="container">
                <div class="row header"></div>
                <div class="row body">
                    <div class="col-3 left-menu"></div>
                    <div class="col-7">
                        <div class="row">
                            <div class="col-12" align="center">공지사항 제목</div>
                            <div class="col-12">
                                <input type="text" name="title" id="title" placeholder="제목을 입력해주세요.">
                            </div>
                            <br>
                            <textarea name="contents" id="summernote"></textarea>
                            <div class="col-12">
                                <div class="row">
                                    <div class="col-12" align="right">
                                        <input type="submit" value="등록">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-2"></div>

                </div>
                <div class="row footer"></div>
            </div>
        </form>

        <script>
            $(document).ready(function () {
                $('#summernote').summernote({
                    height: 300,                 // 에디터 높이
                    minHeight: null,             // 최소 높이
                    maxHeight: null,             // 최대 높이
                    focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
                    lang: "ko-KR",                    // 한글 설정
                    placeholder: '최대 2048자까지 쓸 수 있습니다'    //placeholder 설정

                });
            });
        </script>
    </body>

    </html>