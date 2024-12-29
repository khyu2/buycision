package buycision.userservice.domain.service.impl;

import buycision.userservice.domain.dto.request.LoginRequest;
import buycision.userservice.domain.dto.response.LoginResponse;
import buycision.userservice.domain.repository.UserRepository;
import buycision.userservice.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;


    @Override
    public LoginResponse login(LoginRequest request, String oAuthType, String deviceType) {
        return null;
    }
}
