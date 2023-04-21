package domains.account.management;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class AccountAuthority {
    @NonNull
    private long accountAuthorityId;
}
