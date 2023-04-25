package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.entity.Permission;
import kr.co.khacademy.semi.exception.login.sub.PermissionIdNotFoundException;

public class PermissionRepository {

    private static final PermissionRepository instance = new PermissionRepository();

    private PermissionRepository() {
    }

    public static PermissionRepository getInstance() {
        return instance;
    }

    public Permission findByPermissionId(Long permissionId) {
        /*
         * TODO:
         *  1. permissionId 를 사용하여 Permission 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 Permission 객체를 반환하세요.
         *  3. 조회에 실패하면 PermissionIdNotFoundException 예외를 발생 시키세요.
         */
        throw new PermissionIdNotFoundException();
    }
}
