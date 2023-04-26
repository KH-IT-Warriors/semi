package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.*;
import kr.co.khacademy.semi.entity.Account;
import kr.co.khacademy.semi.entity.Password;
import kr.co.khacademy.semi.entity.Profile;
import kr.co.khacademy.semi.exception.login.sub.PasswordMissMatchException;
import kr.co.khacademy.semi.repository.AccountRepository;
import kr.co.khacademy.semi.repository.PasswordRepository;
import kr.co.khacademy.semi.common.encryption.BasicPasswordEncryptor;
import kr.co.khacademy.semi.common.encryption.PasswordEncryptionProvider;
import kr.co.khacademy.semi.repository.UserGradeRepository;
import kr.co.khacademy.semi.repository.ProfileRepository;

import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {

    private static final AccountServiceImpl instance = new AccountServiceImpl();

    private static final PasswordEncryptionProvider basicPasswordEncryptor = BasicPasswordEncryptor.getInstance();

    private static final AccountRepository accountRepository = AccountRepository.getInstance();
    private static final PasswordRepository passwordRepository = PasswordRepository.getInstance();
//    private static final RoleRepository roleRepository = RoleRepository.getInstance();
//    private static final GrantRepository grantRepository = GrantRepository.getInstance();
//    private static final PermissionRepository permissionRepository = PermissionRepository.getInstance();
    private static final ProfileRepository profileRepository = ProfileRepository.getInstance();
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
            boolean isJoin = true;
            Long createdAccountId = accountRepository.save(joinRequest).getId();

            String encryptedPassword = basicPasswordEncryptor.encryptPassword(joinRequest.getPlainPassword());
            Password password = Password.of(createdAccountId, encryptedPassword);
            passwordRepository.save(password, isJoin);

            Profile profile = Profile.builder()
                .accountId(createdAccountId)
                .name(joinRequest.getName())
                .phoneNumber(joinRequest.getPhoneNumber())
                .email(joinRequest.getEmail())
                .registeredTime(null)
                .recentConnection(null)
                .bonusPoint(0L)
                .build();
            profileRepository.save(profile);
            return true;
        } catch (SQLException sqlException) {
            throw new RuntimeException("회원가입에 실패하였습니다.");
        }
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        try {
            boolean isJoin = false;
            Long id = updatePasswordRequest.getId();
            String encryptedPassword = basicPasswordEncryptor.encryptPassword(updatePasswordRequest.getPlainPassword());
            Password password = Password.of(id, encryptedPassword);
            passwordRepository.save(password, isJoin);
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateInformation(UpdateProfileRequest updateProfileRequest) {
        try {
            Profile profile = Profile.builder()
                .accountId(updateProfileRequest.getAccountId())
                .name(updateProfileRequest.getName())
                .phoneNumber(updateProfileRequest.getPhoneNumber())
                .email(updateProfileRequest.getEmail())
                .build();
            profileRepository.save(profile);
        } catch (SQLException sqlException) {
            throw new RuntimeException("회원정보 수정에 실패하였습니다.");
        }
    }

    public void updateInformation(UpdateProfileAdminRequest updateProfileAdminRequest) {
        try {
            Profile profile = Profile.builder()
                .accountId(updateProfileAdminRequest.getAccountId())
                .name(updateProfileAdminRequest.getName())
                .phoneNumber(updateProfileAdminRequest.getPhoneNumber())
                .email(updateProfileAdminRequest.getEmail())
                .bonusPoint(updateProfileAdminRequest.getBonusPoint())
                .userGradeId(updateProfileAdminRequest.getGradeId())
                .build();
            profileRepository.save(profile);
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public Profile findUserInformation(Long accountId) {
        try {
            return profileRepository.findUserInformationById(accountId);
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAccount(Long accountId) {
        try {
            accountRepository.deleteById(accountId);
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }
}
