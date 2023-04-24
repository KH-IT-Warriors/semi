package domains.account.users;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UserGrade {
    @NonNull
    private Long userGradeId;
    @NonNull
    private String userGradeName;
    @NonNull
    private Double earningRate;
    @NonNull
    private Integer userGradeQualification;
}
