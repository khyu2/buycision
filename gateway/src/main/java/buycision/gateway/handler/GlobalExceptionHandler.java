package buycision.gateway.handler;

import buycision.gateway.exception.BaseExceptionType;
import buycision.multimodule.globalutils.exception.BaseException;
import buycision.multimodule.globalutils.exception.ExceptionType;
import buycision.multimodule.globalutils.response.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.*;

@Slf4j
@Order(-2)
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        ExceptionType exceptionType = getExceptionType(ex);
        log.error("예외 URI: {}", exchange.getRequest().getURI());
        log.error("예외 메시지: {}", exceptionType.getMessage());

        response.getHeaders().setContentType(APPLICATION_JSON);
        response.setStatusCode(HttpStatusCode.valueOf(exceptionType.getHttpStatus().getStatusCode()));

        ExceptionResponse exceptionResponse = new ExceptionResponse(exceptionType);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(exceptionResponse));
            } catch (Exception e) {
                log.error("Error writing response", e);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

    private ExceptionType getExceptionType(Throwable ex) {
        if (ex instanceof BaseException) {
            return ((BaseException) ex).exceptionType();
        }
        return BaseExceptionType.UNKNOWN_SERVER_ERROR;
    }
}
