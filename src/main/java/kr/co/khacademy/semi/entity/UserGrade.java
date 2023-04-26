package kr.co.khacademy.semi.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGrade {
    Long id;
    String gradeName;
    Double accumulateRate;
    Long AchievementCondition;
}
