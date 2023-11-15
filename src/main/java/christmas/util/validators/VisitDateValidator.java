package christmas.util.validators;


import christmas.util.exception.IllegalArgumentExceptionType;

public class VisitDateValidator {
    private static final int MINIMUM_DATE = 1;
    private static final int MAXIMUM_DATE = 31;

    public static Integer isValidDate(String date) {
        try {
            return Integer.valueOf(date);
        } catch (NumberFormatException e) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }
    }

    public static void validateRange(Integer parsedDate) {
        if (parsedDate < MINIMUM_DATE || parsedDate > MAXIMUM_DATE) {
            throw IllegalArgumentExceptionType.INVALID_DATE.getException();
        }
    }
}
