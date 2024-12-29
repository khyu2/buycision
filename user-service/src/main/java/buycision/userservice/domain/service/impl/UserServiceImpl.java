package buycision.userservice.domain.service.impl;

import buycision.userservice.domain.dto.request.UserRenewPasswordRequest;
import buycision.userservice.domain.dto.request.UserSignUpRequest;
import buycision.userservice.domain.dto.request.UserUpdateRequest;
import buycision.userservice.domain.dto.response.UserInfoResponse;
import buycision.userservice.domain.dto.response.UserSignUpResponse;
import buycision.userservice.domain.repository.UserRepository;
import buycision.userservice.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserSignUpResponse register(UserSignUpRequest request) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(Long userId) {
        return null;
    }

    @Override
    @Transactional
    public void updateUserNickname(Long userId, UserUpdateRequest request) {

    }

    @Override
    @Transactional
    public void updateUserPassword(Long userId, UserRenewPasswordRequest request) {

    }

    @Override
    @Transactional
    public void withdrawUser(Long userId) {

    }
}
