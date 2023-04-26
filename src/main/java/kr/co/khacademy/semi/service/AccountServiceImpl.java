package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.*;
import kr.co.khacademy.semi.entity.Account;
import kr.co.khacademy.semi.entity.Password;
import kr.co.khacademy.semi.entity.UserGrade;
import kr.co.khacademy.semi.entity.UserInformation;
import kr.co.khacademy.semi.exception.login.sub.PasswordMissMatchException;
import kr.co.khacademy.semi.repository.AccountRepository;
import kr.co.khacademy.semi.repository.PasswordRepository;
import kr.co.khacademy.semi.common.encryption.BasicPasswordEncryptor;
import kr.co.khacademy.semi.common.encryption.PasswordEncryptionProvider;
import kr.co.khacademy.semi.repository.UserGradeRepository;
import kr.co.khacademy.semi.repository.UserInformationRepository;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {

    private static final AccountServiceImpl instance = new AccountServiceImpl();

    private static final PasswordEncryptionProvider basicPasswordEncryptor = BasicPasswordEncryptor.getInstance();

    private static final AccountRepository accountRepository = AccountRepository.getInstance();
    private static final PasswordRepository passwordRepository = PasswordRepository.getInstance();
//    private static final RoleRepository roleRepository = RoleRepository.getInstance();
//    private static final GrantRepository grantRepository = GrantRepository.getInstance();
//    private static final PermissionRepository permissionRepository = PermissionRepository.getInstance();
    private static final UserInformationRepository userInformationRepository = UserInformationRepository.getInstance();
    private static final UserGradeRepository userGradeRepository = UserGradeRepository.getInstance();

    private AccountServiceImpl() {
    }

    public static AccountServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Long login(LoginRequest loginRequest) {
        try {
            Account account = accountRepository.findByUsername(loginRequest.getUsername());
            Password password = passwordRepository.findByAccountId(account.getId());

            if (basicPasswordEncryptor.checkPassword(
                loginRequest.getPlainPassword(),
                password.getEncryptedPassword())
            ) {
                return account.getId();
            }
            throw new PasswordMissMatchException("비밀번호를 다시 입력해주세요.");
//        Role role = roleRepository.findByRoleId(account.getRoleId());
//        Set<Grant> grants = grantRepository.findAllByRoleId(role.getId());
//        Set<Permission> permissions = grants.stream()
//            .map(grant -> permissionRepository.findByPermissionId(grant.getPermissionId()))
//            .collect(Collectors.toUnmodifiableSet());
        } catch (SQLException sqlException) {
            throw new RuntimeException("로그인에 실패하였습니다.");
        }
    }
    @Override
    public boolean join(JoinRequest joinRequest) {
        try {
            Long createdAccountId = accountRepository.insertNewAccount(joinRequest);

            String encryptedPassword = basicPasswordEncryptor.encryptPassword(joinRequest.getPlainPassword());
            Password password = Password.of(createdAccountId, encryptedPassword);
            passwordRepository.insertNewPassword(password);

            UserInformation userInformation = UserInformation.builder()
                .accountId(createdAccountId)
                .name(joinRequest.getName())
                .phoneNumber(joinRequest.getPhoneNumber())
                .email(joinRequest.getEmail())
                .build();
            userInformationRepository.insertNewUserInformation(userInformation);
            return true;
        } catch (SQLException sqlException) {
            throw new RuntimeException("회원가입에 실패하였습니다.");
        }
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        try {
            Long id = updatePasswordRequest.getId();
            String encryptedPassword = basicPasswordEncryptor.encryptPassword(updatePasswordRequest.getPlainPassword());
            Password password = Password.of(id, encryptedPassword);
            passwordRepository.updatePassword(password);
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateInformation(UpdateInformationRequest updateInformationRequest) {
        try {
            UserInformation userInformation = UserInformation.builder()
                .accountId(updateInformationRequest.getAccountId())
                .name(updateInformationRequest.getName())
                .phoneNumber(updateInformationRequest.getPhoneNumber())
                .email(updateInformationRequest.getEmail())
                .build();
            userInformationRepository.updateInformation(userInformation);
        } catch (SQLException sqlException) {
            throw new RuntimeException("회원정보 수정에 실패하였습니다.");
        }
    }

    public void updateInformation(ChangeUserInformationAdmin changeUserInformationAdmin) {
        try {
            Long accountId = changeUserInformationAdmin.getAccountId();
            Long gradeId = userGradeRepository.findUserGradeIdByName(changeUserInformationAdmin.getGrade());
            userInformationRepository.updateGrade(accountId, gradeId);
            // TODO: 마일리지 수정 기능 추가 >> 사용자 테이블에 합쳐도 되지 않나요?
            UserInformation userInformation = UserInformation.builder()
                .accountId(changeUserInformationAdmin.getAccountId())
                .name(changeUserInformationAdmin.getName())
                .phoneNumber(changeUserInformationAdmin.getPhoneNumber())
                .email(changeUserInformationAdmin.getEmail())
                .build();
            userInformationRepository.updateInformation(userInformation);
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public UserInformation findUserInformation(Long accountId) {
        try {
            return userInformationRepository.findUserInformationById(accountId);
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }
}
