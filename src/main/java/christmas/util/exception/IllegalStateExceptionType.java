package christmas.util.exception;

import static christmas.util.Constants.ERROR;

public enum IllegalStateExceptionType implements ExceptionType<IllegalStateException>{
    DUPLICATION_MENU_NAME(ERROR + "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ORDER_COUNT(ERROR + "주문 수량은 20개를 초과할 수 없습니다. 다시 입력해주세요."),
    INVALID_ORDER_ONLY_DRINK(ERROR + "음료만 주문할 수 없습니다. 다시 입력해 주세요.");

    private final String message;

    IllegalStateExceptionType(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public IllegalStateException getException() {
        return new IllegalStateException(message);
    }
}
