package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.entity.Password;
import kr.co.khacademy.semi.exception.AccountIdNotFoundException;

public class PasswordRepository {

    private static final PasswordRepository instance = new PasswordRepository();

    private PasswordRepository() {
    }

    public static PasswordRepository getInstance() {
        return instance;
    }

    public Password findByAccountId(Long accountId) {
        /*
         * TODO:
         *  1. accountId 를 사용하여 Password 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 Password 객체를 반환하세요.
         *  3. 조회에 실패하면 AccountIdNotFoundException 예외를 발생 시키세요.
         */
        throw new AccountIdNotFoundException();
    }
}
