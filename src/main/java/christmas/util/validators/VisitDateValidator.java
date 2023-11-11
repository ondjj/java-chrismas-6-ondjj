package christmas.util.validators;

import static christmas.util.Constants.ERROR;
import static christmas.util.enums.ErrorMessage.INVALID_DATE;

public class VisitDateValidator {
    public static Integer isValidDate(String date) {
        return Integer.valueOf(date);
    }

    public static void validateRange(Integer parsedDate) {
        if (parsedDate < 1 || parsedDate > 31) {
            throw new IllegalArgumentException(ERROR + INVALID_DATE.getMessage());
        }
    }
}
