package domains.account.users;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
@Builder
public class UserInformation {
    @NonNull
    private long accountId;
    @NonNull
    private String userName;
    @NonNull
    private String userPhoneNumber;
    @NonNull
    private String userEmail;
    @NonNull
    private Timestamp userRegisteredTime;
    @NonNull
    private Timestamp userRecentConnectedTime;
    @NonNull
    private long userGradeId;
}
