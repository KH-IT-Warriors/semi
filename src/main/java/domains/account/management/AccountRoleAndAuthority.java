package domains.account.management;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AccountRoleAndAuthority {
    @NonNull
    private Long accountRoleId;
    @NonNull
    private Long accountAuthorityId;
}
