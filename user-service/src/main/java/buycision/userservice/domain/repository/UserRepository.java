package buycision.userservice.domain.repository;

import buycision.multimodule.globalutils.exception.BaseException;
import buycision.userservice.domain.entity.User;
import buycision.userservice.domain.exception.UserExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    default User getUserById(Long id) {
        return findById(id).orElseThrow(() -> new BaseException(UserExceptionType.USER_NOT_FOUND));
    }

    Optional<User> findByEmail(String email);
}
