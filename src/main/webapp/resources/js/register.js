const to_next = function (boolean) {
  if (boolean) {
    $('.register-container').toggleClass('hidden');
  } else {
    alert('약관에 모두 동의해주세요.');
  }
}

const username_regex = /(?=.*[a-zA-Z])(?=.*[0-9]).{6,10}/;
const password_downcase_regex = /.*[a-z].*?$/;
const password_uppercase_regex = /.*[A-Z].*?$/;
const password_special_regex = /.*[!@#$%^&*()\-=_+\[\]{};':\"\\|,.<>/?].*?$/;
const password_number_regex = /.*[0-9].*?$/;
const password_length_regex = /.{8,16}/;
const email_regex = /^[a-z0-9]+@.+(\.com)$/;
const phone_regex = /^01\d\d{8}$/;

$('.chk').on('change', function () {
  const term1 = $('#term1').prop('checked');
  const term2 = $('#term2').prop('checked');
  if (term1 && term2) {
    $('#check-all').prop('checked', true);
  } else {
    $('#check-all').prop('checked', false);
  }
});
$('#check-all').on('change', function () {
  const check_all = $('#check-all').prop('checked');
  if (check_all) {
    $('input[type=checkbox]').prop('checked', true);
  } else {
    $('input[type=checkbox]').prop('checked', false);
  }
});
// $('button:nth-type(2)').on('click', function () {
//     $.ajax({
//         url: '/account/term-appect',
//         method: 'GET',
//         data: {
//             term: $('term').prop('checked')
//         }
//     }).done((result) => {
//         to_next(result);
//     }).fail(() => {
//         alert('알 수 없는 오류가 발생했습니다.');
//         throw new Error('알 수 없는 오류가 발생했습니다.');
//     });
// });
$('#to-next').on('click', function () {
  const term1 = $('#term1').prop('checked');
  const term2 = $('#term2').prop('checked');
  to_next(term1 && term2);
})

$('input').on('keydown', function (e) {
  if (e.key === 'Enter') {
    e.preventDefault();
    $(this).parent().next().find('input').focus();
  }
})

$('#username').on('focus', function () {
  $('label[for=username]').text('아이디: 영문, 숫자 조합으로 6~10자');
});
$('#username').on('focusout', function () {
  $('label[for=username]').text('ID');
});
// $('#username').on('keyup', function () {
//     $.ajax({
//         url: '/account/duplicateCheck',
//         method: 'POST',
//         data: {
//             username: $('#username').val()
//         },
//         dataType: 'json'
//     }).done((result) => {
//         if (result.result) {
//             $('#username-accordion-btn').click();
//         }
//     }).fail();
// });
$('#username').on('keyup', function (e) {
  const usernameBtn = $('#username-accordion-btn');
  const accordionUsername = $('#validateUsername');
  if (username_regex.test($('#username').val())) {
    usernameBtn.removeClass('collapsed');
    usernameBtn.attr('aria-expanded', true);
    accordionUsername.addClass('show');
    $('#username').addClass('valid');
    $('#username').removeClass('invalid');
  } else {
    usernameBtn.addClass('collapsed');
    usernameBtn.attr('aria-expanded', false);
    accordionUsername.removeClass('show');
    $('#username').removeClass('valid');
    $('#username').addClass('invalid');
  }
});

$('#password').on('focus', function () {
  $('label[for=password]').text('비밀번호: 영문(대,소문자), 숫자, 특수문자 조합으로 8~15자');
});
$('#password').on('focusout', function () {
  $('label[for=password]').text('PASSWORD');
});
$('#password').on('keyup', function () {
  const password_val = $('#password').val().trim();
  let cnt = 0;
  if (password_downcase_regex.test(password_val)) {
    $('#down').css('color', 'green');
    cnt++;
  } else {
    $('#down').css('color', 'gray');
  }
  if (password_uppercase_regex.test(password_val)) {
    $('#upper').css('color', 'green');
    cnt++;
  } else {
    $('#upper').css('color', 'gray');
  }
  if (password_number_regex.test(password_val)) {
    $('#number').css('color', 'green');
    cnt++
  } else {
    $('#number').css('color', 'gray');
  }
  if (password_special_regex.test(password_val)) {
    $('#special').css('color', 'green');
    cnt++;
  } else {
    $('#special').css('color', 'gray');
  }
  if (password_length_regex.test(password_val)) {
    $('#length').css('color', 'green');
    cnt++;
  } else {
    $('#length').css('color', 'gray');
  }
  if (cnt === 5) {
    $('#password').addClass('valid');
    $('#password').removeClass('invalid');
  } else {
    $('#password').removeClass('valid');
    $('#password').addClass('invalid');
  }
})


$('#password-check').on('focus', function () {
  $('label[for=password-check]').text('비밀번호를 다시 입력해주세요.');
});
$('#password-check').on('focusout', function () {
  $('label[for=password-check]').text('RE-CHECK PASSWORD');
});
$('#password-check').on('keyup', function () {
  const password_val = $('#password').val().trim();
  const password_check_val = $('#password-check').val().trim();
  if (password_val === password_check_val) {
    $('#password-check').addClass('valid');
    $('#password-check').removeClass('invalid');
  } else {
    $('#password-check').removeClass('valid');
    $('#password-check').addClass('invalid');
  }
})


$('#email').on('focus', function () {
  $('label[for=email]').text('이메일: example@example.com');
});
$('#email').on('focusout', function () {
  $('label[for=email]').text('EMAIL');
});
$('#email').on('keyup', function () {
  const email_val = $('#email').val().trim();
  if (email_regex.test(email_val)) {
    $('#email').addClass('valid');
    $('#email').removeClass('invalid');
  } else {
    $('#email').removeClass('valid');
    $('#email').addClass('invalid');
  }
})


$('#phone-number').on('focus', function () {
  $('label[for=phone-number]').text('전화번호: -는 생략하고 입력해주세요.');
});
$('#phone-number').on('focusout', function () {
  $('label[for=phone-number]').text('CONTACT');
});
$('#phone-number').on('keyup', function () {
  const phone_val = $('#phone-number').val().trim();
  if (phone_regex.test(phone_val)) {
    $('#phone-number').addClass('valid');
    $('#phone-number').removeClass('invalid');
  } else {
    $('#phone-number').removeClass('valid');
    $('#phone-number').addClass('invalid');
  }
})


$('#name').on('focus', function () {
  $('label[for=name]').text('이름: 홍길동');
});
$('#name').on('focusout', function () {
  $('label[for=name]').text('NAME');
});
$('#name').on('keyup', function () {
  const name_val = $('#name').val().trim();
  if (name_val.length > 1) {
    $('#name').addClass('valid');
    $('#name').removeClass('invalid');
  } else {
    $('#name').removeClass('valid');
    $('#name').addClass('invalid');
  }
});

$('.account-form').on('submit', function() {
  const id = $('#username').val().trim();
  if (id !== '') {
    if (!username_regex.test(id)) {
      alert('아이디는 6~10자 이내의 영문과 숫자로 이루어져야 합니다.');
      return false;
    }
  } else {
    alert('아이디는 필수 입력사항입니다.');
    return false;
  }
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
  } else {
    alert('비밀번호는 필수 입력사항입니다.');
    return false;
  }
  const email = $('#email').val().trim();
  if (email !== '') {
    if (!email_regex.test(email)) {
      alert('이메일 형식에 맞게 입력해주세요.');
      return false;
    }
  } else {
    alert('이메일은 필수 입력사항입니다.');
    return false;
  }
  const phone_number = $('phone-number').val().trim();
  if (phone_number !== '') {
    if (!phone_regex.test(phone_number)) {
      alert('전화번호 형식에 맞게 입력해주세요.');
      return false;
    }
  } else {
    alert('전화번호는 필수 입력사항입니다.');
    return false;
  }
});


$('#add-account').on('click', function () {
  location.href = '/admin/user/register';
})
$('#switch-user-type').on('click', function () {
  if ($(this).hasClass('to-normal')) {
    location.href = '/admin/user/list?type=normal';
  } else {
    location.href = '/admin/user/list?type=admin';
  }
})

$('#do-login').on('click', function () {
  $(this).closest('.row').find('form').submit();
});