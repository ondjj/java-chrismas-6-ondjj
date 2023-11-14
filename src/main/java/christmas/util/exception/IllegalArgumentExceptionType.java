package christmas.util.exception;

import static christmas.util.Constants.ERROR;

public enum IllegalArgumentExceptionType implements ExceptionType<IllegalArgumentException>{
    INVALID_DATE(ERROR + "유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NUMBER_FORMAT_EXCEPTION(ERROR + "유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    IllegalArgumentExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public IllegalArgumentException getException() {
        return new IllegalArgumentException(message);
    }
}
