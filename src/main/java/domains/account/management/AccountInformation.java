package domains.account.management;

import domains.account.commons.Password;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AccountInformation {
    @NonNull
    private Long accountId;
    @NonNull
    private Long accountStatusId;
    @NonNull
    private Long accountRoleId;
    @NonNull
    private String userName;
    @NonNull
    private Password userPw;
}
