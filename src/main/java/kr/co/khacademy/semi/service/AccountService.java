package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.dto.LoginRequest;
import kr.co.khacademy.semi.dto.UpdateInformationRequest;
import kr.co.khacademy.semi.dto.UpdatePasswordRequest;
import kr.co.khacademy.semi.entity.Password;
import kr.co.khacademy.semi.entity.UserInformation;

public interface AccountService {

    Long login(LoginRequest loginRequest);

    boolean join(JoinRequest joinRequest);

    void updatePassword(UpdatePasswordRequest updatePasswordRequest);

    void updateInformation(UpdateInformationRequest updateInformationRequest);

    UserInformation printUserInformation(Long accountId);
}
