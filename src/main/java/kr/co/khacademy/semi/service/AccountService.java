package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.dto.LoginRequest;
import kr.co.khacademy.semi.dto.UpdateInformationRequest;

public interface AccountService {

    Long login(LoginRequest loginRequest);

    boolean join(JoinRequest joinRequest);

    void updateInformation(UpdateInformationRequest updateInformationRequest);
}
