package buycision.userservice.domain.service;

import buycision.userservice.domain.dto.request.UserRenewPasswordRequest;
import buycision.userservice.domain.dto.request.UserSignUpRequest;
import buycision.userservice.domain.dto.request.UserUpdateRequest;
import buycision.userservice.domain.dto.response.UserInfoResponse;
import buycision.userservice.domain.dto.response.UserSignUpResponse;

public interface UserService {

    UserSignUpResponse register(UserSignUpRequest request);

    UserInfoResponse getUserInfo(Long userId);

    void updateUserNickname(Long userId, UserUpdateRequest request);

    void updateUserPassword(Long userId, UserRenewPasswordRequest request);

    void withdrawUser(Long userId);
}
