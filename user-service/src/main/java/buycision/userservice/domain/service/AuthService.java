package buycision.userservice.domain.service;

import buycision.userservice.domain.dto.request.LoginRequest;
import buycision.userservice.domain.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request, String oAuthType, String deviceType);
}
