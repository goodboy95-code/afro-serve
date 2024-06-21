package ink.afro.exception.type;

import lombok.Getter;

import java.io.Serial;

/**
 * 业务异常
 *
 * @author goodboy95-code
 */
@Getter
@SuppressWarnings("unused")
public final class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String errorMessage;

    /**
     * 错误明细，内部调试错误
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }

    public ServiceException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ServiceException(String errorMessage, Integer code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}