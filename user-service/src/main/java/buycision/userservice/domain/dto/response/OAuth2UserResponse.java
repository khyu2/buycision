package buycision.userservice.domain.dto.response;

import buycision.userservice.domain.entity.User;

public interface OAuth2UserResponse {

    String getEmail();

    User toUser();
}
