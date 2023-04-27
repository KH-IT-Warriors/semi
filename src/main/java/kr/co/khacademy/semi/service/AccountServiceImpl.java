package kr.co.khacademy.semi.service;

import kr.co.khacademy.semi.dto.*;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Password;
import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.exception.login.sub.PasswordMissMatchException;
import kr.co.khacademy.semi.repository.AccountRepository;
import kr.co.khacademy.semi.repository.PasswordRepository;
import kr.co.khacademy.semi.common.encryption.BasicPasswordEncryptor;
import kr.co.khacademy.semi.common.encryption.PasswordEncryptionProvider;
import kr.co.khacademy.semi.repository.UserGradeRepository;
import kr.co.khacademy.semi.repository.ProfileRepository;
import lombok.SneakyThrows;

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
    @SneakyThrows
    public Long login(LoginRequest loginRequest) {
        Account account = accountRepository.findByUsername(loginRequest.getUsername());
        Password password = passwordRepository.findByAccountId(account.getId());

        if (basicPasswordEncryptor.checkPassword(
            loginRequest.getPlainPassword(),
            password.getEncryptedPassword())) {
            return account.getId();
        } else {
            throw new PasswordMissMatchException("비밀번호를 다시 입력해주세요.");
        }
//        Role role = roleRepository.findByRoleId(account.getRoleId());
//        Set<Grant> grants = grantRepository.findAllByRoleId(role.getId());
//        Set<Permission> permissions = grants.stream()
//            .map(grant -> permissionRepository.findByPermissionId(grant.getPermissionId()))
//            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    @SneakyThrows
    public Boolean join(Account account, Password password, Profile profile) {
        boolean isJoin = true;
        Long id = accountRepository.save(account).getId();
        Password pw = Password.of(id, password.getEncryptedPassword());
        Profile pf = Profile.builder()
            .accountId(id)
            .name(profile.getName())
            .phoneNumber(profile.getPhoneNumber())
            .email(profile.getEmail())
            .build();
        passwordRepository.save(pw, isJoin);
        profileRepository.save(pf);
        return Boolean.TRUE;
    }

    @Override
    @SneakyThrows
    public void modifyPasswordById(PasswordPutRequest passwordPutRequest) {
        boolean isJoin = false;
        Long id = passwordPutRequest.getId();
        String encryptedPassword = basicPasswordEncryptor.encryptPassword(passwordPutRequest.getPlainPassword());
        Password password = Password.of(id, encryptedPassword);
        passwordRepository.save(password, isJoin);
    }

    @Override
    @SneakyThrows
    public void modifyProfileById(ProfilePutRequest profilePutRequest) {
        Profile profile = Profile.builder()
            .accountId(profilePutRequest.getAccountId())
            .name(profilePutRequest.getName())
            .phoneNumber(profilePutRequest.getPhoneNumber())
            .email(profilePutRequest.getEmail())
            .build();
        profileRepository.save(profile);
    }

    @SneakyThrows
    public void modifyProfileById(ProfileByAdminPutRequest profileByAdminPutRequest) {
        Profile profile = Profile.builder()
            .accountId(profileByAdminPutRequest.getAccountId())
            .name(profileByAdminPutRequest.getName())
            .phoneNumber(profileByAdminPutRequest.getPhoneNumber())
            .email(profileByAdminPutRequest.getEmail())
            .bonusPoint(profileByAdminPutRequest.getBonusPoint())
            .userGradeId(profileByAdminPutRequest.getGradeId())
            .build();
        profileRepository.save(profile);
    }

    @Override
    @SneakyThrows
    public Profile findProfileById(Long accountId) {
        return profileRepository.findProfileById(accountId);
    }

    @Override
    @SneakyThrows
    public void deleteAccountById(Long accountId) {
        accountRepository.delete(accountId);
    }

    @Override
    @SneakyThrows
    public String findUsernameByPhoneNumber(Profile profile) {
        return accountRepository.findByPhoneNumber(profile).getUsername();
    }

    @Override
    @SneakyThrows
    public Password findPasswordByPhoneNumber(Account account, Profile profile) {
        return passwordRepository.findByPhoneNumber(account, profile);
    }

    @Override
    @SneakyThrows
    public String findUsernameById(Long accountId) {
        return accountRepository.findById(accountId).getUsername();
    }
}
