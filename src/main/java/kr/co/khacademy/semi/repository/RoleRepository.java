package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.entity.Role;
import kr.co.khacademy.semi.exception.RoleIdNotFoundException;

public class RoleRepository {

    private static final RoleRepository instance = new RoleRepository();

    private RoleRepository() {
    }

    public static RoleRepository getInstance() {
        return instance;
    }

    public Role findByRoleId(Long roleId) {
        /*
         * TODO:
         *  1. roleId 를 사용하여 Role 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 Role 객체를 반환하세요.
         *  3. 조회에 실패하면 RoleIdNotFoundException 예외를 발생 시키세요.
         */
        throw new RoleIdNotFoundException();
    }
}
