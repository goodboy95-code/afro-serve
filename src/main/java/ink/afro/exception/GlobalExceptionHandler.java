package ink.afro.exception;

import ink.afro.exception.type.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

/**
 * 全局异常处理器
 *
 * @author goodboy95-code
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("没有访问权限");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> handleServiceException(ServiceException e) {
        log.error(e.getMessage(), e.getCode());
        Integer code = e.getCode();
        return ObjectUtils.isEmpty(code) ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()) : ResponseEntity.status(code).body(e.getMessage());
    }

    /**
     * 任务被线程池拒绝执行异常
     */
    @ExceptionHandler(RejectedExecutionException.class)
    public ResponseEntity<String> handleServiceException(RejectedExecutionException e) {
        log.error(e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("任务执行失败，稍后重试");
    }
}
