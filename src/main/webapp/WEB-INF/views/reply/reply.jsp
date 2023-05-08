
<%@ page contentType="text/html;charset=UTF-8" language="java" %>






<style>
  .profile {
    width: 29px;
    height: 29px;
    border-radius: 70%;
    overflow: hidden;

  }

  .img {
    width: 100%;
    height: 100%;
    object-fit: cover;

  }

  .txt {
    border-radius: 25px;
    border: 1px solid black;

  }

  .txt-dummy {
    outline: none;
  }

  .btn-box {
    overflow: hidden;
  }

  #reply-btn {

    font-size: 15px;
    overflow: hidden;
    background-color: rgba(0, 0, 0, 0);
    border: none;
    padding: 0;
  }

  .comment_txt {
    letter-spacing: -1px;
  }

  .main {

    word-break: break-all;
  }

  .main>span {
    font-size: 13px;

  }

  .user {
    font-weight: bold;
  }

  .comment_txt {
    font-weight: lighter;
    outline: none;
  }

  .sub {
    font-size: 4px;
  }

  .upload_time {
    color: rgb(34, 34, 34, .5);
  }

  .delete-Btn {
    text-decoration: none;
    color: rgb(34, 34, 34, .5);
    border: none;
    background-color: rgba(0, 0, 0, 0);
    padding: 0;
  }

  .delete:hover {
    color: rgb(34, 34, 34, .5);
  }

  .modify-Btn {
    text-decoration: none;
    color: rgb(34, 34, 34, .5);
    border: none;
    background-color: rgba(0, 0, 0, 0);
    padding: 0;
  }

  .modifyCancle-Btn{
    text-decoration: none;
    color: rgb(34, 34, 34, .5);
    border: none;
    background-color: rgba(0, 0, 0, 0);
    padding: 0;
  }
  .modifyComplet-Btn{
    text-decoration: none;
    color: rgb(34, 34, 34, .5);
    border: none;
    background-color: rgba(0, 0, 0, 0);
    padding: 0;
  }
</style>


<form action="/admin/reply/register" method="post">
  <div class="container-fluid p-0">
    <div class="row m-0">
      <div class="col-1 p-0 " align="center">
        <div class="profile" style="background: #BDBDBD;">
          <img class="img" src="/resources/icon/raccoon-ga5593909a_1920.jpg" alt="..">
        </div>
      </div>
      <div class="col-11 p-0">
        <div class="row m-0">
          <div class="col-12 p-0">
            <div class="txt row m-0">
              <div class="col-11 p-2 txt-dummy" contenteditable="true">

              </div>
              <div class="col-1 btn-box p-0" align="right" style="text-align: center;">
                <button id="reply-btn" class="btn-sm">등록</button>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

  </div>
</form>

<hr>
<form action="/admin/reply/modify">
  <div class="comment-box p-0 ">
    <div class="row m-0">
      <div class="col-1 p-0 " align="center">
        <div class="profile" style="background: #BDBDBD;">
          <img class="img" src="raccoon-ga5593909a_1920.jpg" alt="..">
        </div>
      </div>
      <div class="col-10 comment-detail p-0">
        <div class="row m-0">
          <div class="col-12 main p-0">
            <span class="user">mainlib990</span>
            <span class="comment_txt">
                                안녕하세요ddsds
                            </span>
          </div>
          <div class="col-12 sub p-0">
            <span class="upload_time">1초전</span>
            <input type="hidden" class="id" value="${replyId}">
            <input type="hidden" class="product-id" value="${productId}">
            <button type="button" class="delete-Btn">삭제</button>
            <button type="button" class="modify-Btn">수정</button>
            <button class="modifyComplet-Btn" style="display:none">수정완료</button>
            <button type="button" class="modifyCancle-Btn" style="display: none">취소</button>
          </div>
        </div>
      </div>
      <div class="col-1"></div>
    </div>
  </div>
  </div>
</form>


<script>
  $(".delete-Btn").click(function (){
    const form = $("<form action='/admin/reply/delete' method='post'>");
    const id = $(this).prev().prev().val();
    const pId = $(this).prev().val();
    const replyId = $("<input type='hidden' name=id value=" + id + ">");
    const productId = $("<input type='hidden' name=pId value=" + pId + ">");
    form.append(replyId,productId);
    $(this).append(form);
    form.submit();

  })

  $(".modify-Btn").click(function () {



    const deleteBtn = $(this).prev();
    const modifyBtn = $(this)
    const commentTxt =$(this).parent().prev().find(".comment_txt");

    deleteBtn.hide();
    $(this).hide();
    commentTxt.attr({"contenteditable":true});

    const modifyCompletBtn = $(this).next();
    const modifyCancleBtn = $(this).next().next();

    modifyCompletBtn.css({"display":"inline-block"});
    modifyCancleBtn.css({"display":"inline-block"});

    modifyCancleBtn.click(function(){
      location.reload();
    });
  });

</script>

