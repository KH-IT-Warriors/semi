package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.*;
import kr.co.khacademy.semi.entity.Account;
import kr.co.khacademy.semi.entity.Password;
import kr.co.khacademy.semi.entity.Profile;

public interface AccountService {

    Long login(LoginRequest loginRequest);

    boolean join(JoinRequest joinRequest);

    void updatePassword(UpdatePasswordRequest updatePasswordRequest);

    void updateProfile(UpdateProfileRequest updateProfileRequest);

    Profile findProfileByAccountId(Long accountId);

    void deleteAccountByAccountId(Long accountId);

    String findUsernameByPhoneNumber(FindUsernameRequest findUsernameRequest);

    Password findPasswordByPhoneNumber(FindPasswordRequest findPasswordRequest);

    String findUsernameById(Long accountId);
}
