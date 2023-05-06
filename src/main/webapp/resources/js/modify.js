const password_downcase_regex = /.*[a-z].*?$/;
const password_uppercase_regex = /.*[A-Z].*?$/;
const password_special_regex = /.*[!@#$%^&*()\-=_+\[\]{};':\"\\|,.<>/?].*?$/;
const password_number_regex = /.*[0-9].*?$/;
const password_length_regex = /.{8,16}/;
const email_regex = /^[a-z0-9]+@.+(\.com)$/;
const phone_regex = /^01\d\d{8}$/;

$('input').on('keydown', function (e) {
  if (e.key === 'Enter') {
    e.preventDefault();
    $(this).parent().next().find('input').focus();
  }
});


$('.btn-mod').on('click', function () {
  const target = $(this);
  target.siblings('input').prop('readonly', false);
  target.siblings('input').css({
    'border-bottom': '1px solid black',
  });
  target.siblings('select').toggleClass('hidden').prop('disabled', false);
  if (target.hasClass('confirm')) {
    $('#modify-form').submit();
    return;
  }
  target.toggleClass('confirm');
  target.before($('<button type="button" class="btn btn-outline-secondary btn-sm-custom cancel" style="margin-right: 0.1rem;">').text('Cancel'));
});

$('li').on('click', '.cancel', function () {
  location.reload();
});

$('#modify-form').on('submit', function (e) {
  const pw = $('#password').val().trim();
  if (pw !== '') {
    if (!password_downcase_regex.test(pw)) {
      alert('비밀번호는 영어 소문자를 포함해야합니다.');
      return false;
    }
    if (!password_uppercase_regex.test(pw)) {
      alert('비밀번호는 영어 대문자를 포함해야합니다.');
      return false;
    }
    if (!password_number_regex.test(pw)) {
      alert('비밀번호는 숫자를 포함해야합니다.');
      return false;
    }
    if (!password_special_regex.test(pw)) {
      alert('비밀번호는 특수문자를 포함해야합니다.');
      return false;
    }
    if (!password_length_regex.test(pw)) {
      alert('비밀번호는 8~15자 사이여야 합니다.');
      return false;
    }
  }
  const phone_number = $('#phone-number').val().trim();
  if (!phone_regex.test(phone_number)) {
    alert('전화번호는 -를 제외하고 형식에 맞게 작성해주세요.');
    return false;
  }
  const email = $('#email').val().trim();
  if (!email_regex.test(email)) {
    alert('이메일을 형식에 맞게 작성해주세요.');
    return false;
  }
  const mileage = Number.parseInt($('#mileage').val().trim());
  if (mileage < 0) {
    alert('마일리지에는 음수를 입력할 수 없습니다.');
    return false;
  }
  const role = $('#role option:selected').val();
  if (!$('#role-id').prop('disabled')) {
    $('#role-id').val(role);
  }
  const grade = $('#grade option:selected').val();
  if (!$('#grade-id').prop('disabled')) {
    $('#grade-id').val(grade);
  }
  return false;
});

$('.btn-del').on('click', function () {
  if (confirm('정말로 삭제하시겠습니까?')) {
    $('#delete-form').submit();
  }
});

$('#profile-img').on('change', function () {
  const selected_file = document.querySelector('#profile-img').files[0];
  const fileReader = new FileReader();
  fileReader.readAsDataURL(selected_file);
  fileReader.onload = function () {
    $('.profile-img').attr('src', fileReader.result);
  }
})

$('.profile-img').on('click', function () {
  const profile_img = $('#profile-img');
  profile_img.click();
});