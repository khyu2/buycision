package buycision.multimodule.globalutils.exception;

public interface ExceptionType {

    HttpStatus getHttpStatus();
    String getErrorCode();
    String getMessage();
}
