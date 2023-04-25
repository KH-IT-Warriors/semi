package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.entity.Grant;
import kr.co.khacademy.semi.exception.login.sub.RoleIdNotFoundException;

import java.util.Set;

public class GrantRepository {

    private static final GrantRepository instance = new GrantRepository();

    private GrantRepository() {
    }

    public static GrantRepository getInstance() {
        return instance;
    }

    public Set<Grant> findAllByRoleId(Long roleId) {
        /*
         * TODO:
         *  1. roleId 을 사용하여 Grant 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 불변 객체(Collections.unmodifiableSet)의 Set<Grant> 객체를 반환하세요.
         *  3. 조회에 실패하면 RoleIdNotFoundException 예외를 발생 시키세요.
         */
        throw new RoleIdNotFoundException();
    }
}
