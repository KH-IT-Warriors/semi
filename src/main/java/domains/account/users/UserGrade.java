package domains.account.users;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UserGrade {
    @NonNull
    private long userGradeId;
    @NonNull
    private String userGradeName;
    @NonNull
    private double earningRate;
    @NonNull
    private int userGradeQualification;
}
