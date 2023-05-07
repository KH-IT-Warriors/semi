package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.model.Role;
import kr.co.khacademy.semi.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

class UserDaoTest {

    @DisplayName("Create")
    @ParameterizedTest(name = "{index} : {arguments}")
    @CsvSource({
        "0, 1, 1, 'myawesomeid2', 'aqK2@@1qQ', '김정우', '01014489341', 'kimjw@email.com', 0, 1, '일반 사용자'"
    })
    public void givenUser_whenSuccess_CreateTest(
        Long id,
        Long statusId,
        Long roleId,
        String username,
        String password,
        String name,
        String phoneNumber,
        String email,
        Long mileage,
        Long gradeId,
        String roleName
    ) {
        Account account = Account.of(id, statusId, roleId, username, password);
        Profile profile = Profile.of(id, name, phoneNumber, email, mileage, gradeId, null, null, "");
        Role role = Role.of(roleId, roleName);
        User user = User.of(account, profile, role);
        try {
            UserDao.getInstance().create(user);
            System.out.println("=create=");
            System.out.println(user);
            System.out.println("=end=");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThat(user).isNotNull();
    }

    @DisplayName("Update")
    @ParameterizedTest(name = "{index} : {arguments}")
    @CsvSource({
        "5, 2, 3, 'modified2', 'Modified!!2', '수정함', '01098764321', 'mody0771@emailzz.com', 20350, 3, '선임 관리자'",
        "6, 2, 4, 'modified3', 'moDified!#6', '변화됨', '01012367343', 'mody3022@emailzz.com', 8990, 4, '일반 관리자'"
    })
    public void givenUser_whenSuccess_UpdateTest(
        Long id,
        Long statusId,
        Long roleId,
        String username,
        String password,
        String name,
        String phoneNumber,
        String email,
        Long mileage,
        Long gradeId,
        String roleName
    ) {
        Account account = Account.of(id, statusId, roleId, username, password);
        Profile profile = Profile.of(id, name, phoneNumber, email, mileage, gradeId, null, null, "");
        Role role = Role.of(roleId, roleName);
        User user = User.of(account, profile, role);
        try {
            UserDao.getInstance().update(user);
            System.out.println("=update=");
            System.out.println(UserDao.getInstance().read(id));
            System.out.println("=end=");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void readTest(){
        try {
            List<User> users = UserDao.getInstance().readAdminUser(1L, 10L);
            users.forEach(System.out::println);
            System.out.println("============================");
            users = UserDao.getInstance().readNormalUser(1L, 10L);
            users.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}