package kr.co.khacademy.semi.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Address {

    Long id;
    Long accountId;
    String name;
    String postAddress;
    String roadAddress;
    String lotAddress;
    String detailAddress;

    public static Address of(HttpServletRequest req) {
        
        String name = req.getParameter("name");
        String postAddress = Optional.of(req.getParameter("post-address"))
                .filter(Address::validatePostAddress)
                .orElseThrow(IllegalArgumentException::new);

        String roadAddress = Optional.of(req.getParameter("road-address"))
                .filter(Address::validateRoadAddress)
                .orElseThrow(IllegalArgumentException::new);
        
        String lotAddress = Optional.of(req.getParameter("lot-address"))
                .filter(Address::validateLotAddress)
                .orElseThrow(IllegalArgumentException::new);
        
        String detailAddress = Optional.of(req.getParameter("detail-address"))
                .filter(Address::validateDetailAddress)
                .orElseThrow(IllegalArgumentException::new);
        
        return Address.builder()
                .name(name)
                .postAddress(postAddress)
                .roadAddress(roadAddress)
                .lotAddress(lotAddress)
                .detailAddress(detailAddress)
                .build();
    }
    
    public static Address of(ResultSet resultSet) throws SQLException {
        return Address.builder()
                .id(resultSet.getLong("id"))
                .accountId(resultSet.getLong("account_id"))
                .name(resultSet.getString("name"))
                .postAddress(resultSet.getString("post_address"))
                .roadAddress(resultSet.getString("road_address"))
                .lotAddress(resultSet.getString("lot_address"))
                .detailAddress(resultSet.getString("detail_address"))
                .build();
    }

    private static Boolean validatePostAddress(String postAddress) {
        return postAddress.length() <= 300;
    }

    private static Boolean validateRoadAddress(String roadAddress) {
        return roadAddress.length() <= 300;
    }
    
    private static Boolean validateLotAddress(String lotAddress) {
        return lotAddress.length() <= 300;
    }
    
    private static Boolean validateDetailAddress(String detailAddress) {
        return detailAddress.length() <= 300;
    }
}
