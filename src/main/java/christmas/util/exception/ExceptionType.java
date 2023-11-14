package christmas.util.exception;

public interface ExceptionType<T> {
    String getMessage();
    T getException();
}
