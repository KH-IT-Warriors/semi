package kr.co.khacademy.semi.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserGrade {
    Long id;
    String gradeName;
    Double accumulateRate;
    Long achievementCondition;
}
