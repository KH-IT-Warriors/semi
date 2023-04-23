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
    private String name;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String email;
    @NonNull
    private Timestamp registeredTime;
    @NonNull
    private Timestamp recentConnection;
    @NonNull
    private long userGradeId;
}
