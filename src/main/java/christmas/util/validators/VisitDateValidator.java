package christmas.util.validators;


import christmas.util.exception.IllegalArgumentExceptionType;

public class VisitDateValidator {
    public static Integer isValidDate(String date) {
        try {
            return Integer.valueOf(date);
        } catch (NumberFormatException e) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }
    }

    public static void validateRange(Integer parsedDate) {
        if (parsedDate < 1 || parsedDate > 31) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }
    }
}
