package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.*;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Password;
import kr.co.khacademy.semi.model.Profile;

public interface AccountService {

    Long login(LoginRequest loginRequest);

    Boolean join(Account account, Password password, Profile profile);

    void modifyPasswordById(PasswordPutRequest passwordPutRequest);

    void modifyProfileById(ProfilePutRequest profilePutRequest);

    Profile findProfileById(Long accountId);

    void deleteAccountById(Long accountId);

    String findUsernameByPhoneNumber(Profile profile);

    Password findPasswordByPhoneNumber(Account account, Profile profile);

    String findUsernameById(Long accountId);
}
