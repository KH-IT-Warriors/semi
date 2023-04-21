package domains.account.management;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AccountRole {
    @NonNull
    private long accountRoleId;
}
