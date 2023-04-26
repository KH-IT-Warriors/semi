package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.dto.LoginRequest;
import kr.co.khacademy.semi.dto.UpdateProfileRequest;
import kr.co.khacademy.semi.dto.UpdatePasswordRequest;
import kr.co.khacademy.semi.entity.Profile;

public interface AccountService {

    Long login(LoginRequest loginRequest);

    boolean join(JoinRequest joinRequest);

    void updatePassword(UpdatePasswordRequest updatePasswordRequest);

    void updateInformation(UpdateProfileRequest updateProfileRequest);

    Profile findUserInformation(Long accountId);

    void deleteAccount(Long accountId);
}
