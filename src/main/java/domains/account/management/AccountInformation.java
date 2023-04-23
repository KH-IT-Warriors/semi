package domains.account.management;

import domains.account.commons.Password;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AccountInformation {
    @NonNull
    private long accountId;
    @NonNull
    private long accountStatusId;
    @NonNull
    private long accountRoleId;
    @NonNull
    private String userName;
    @NonNull
    private Password userPw;
}