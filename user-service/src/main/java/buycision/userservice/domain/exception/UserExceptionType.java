package buycision.userservice.domain.exception;

import buycision.multimodule.globalutils.exception.ExceptionType;
import buycision.multimodule.globalutils.exception.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserExceptionType implements ExceptionType {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-001", "회원 정보를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "USER-002", "이미 존재하는 회원입니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "USER-003", "이미 존재하는 이메일입니다."),
    OAUTH_TYPE_NOT_VALID(HttpStatus.NOT_FOUND, "USER-004", "존재하지 않는 인증 타입입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;
}
