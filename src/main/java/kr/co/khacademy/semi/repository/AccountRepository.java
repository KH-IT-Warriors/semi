package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.entity.Account;
import kr.co.khacademy.semi.exception.UsernameNotFoundException;

public class AccountRepository {

    private static final AccountRepository instance = new AccountRepository();

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        return instance;
    }

    public Account findByUsername(String username) {
        /*
         * TODO:
         *  1. username 을 사용하여 Account 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 Account 객체를 반환하세요.
         *  3. 조회에 실패하면 UsernameMissMatchException 예외를 발생 시키세요.
         */
        throw new UsernameNotFoundException();
    }
}
