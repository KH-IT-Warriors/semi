package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.dto.LoginRequest;

public interface AccountService {

    Long login(LoginRequest loginRequest);

    boolean join(JoinRequest joinRequest);
}
