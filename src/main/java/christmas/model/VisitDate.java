package christmas.model;

import static christmas.util.validators.VisitDateValidator.isValidDate;
import static christmas.util.validators.VisitDateValidator.validateRange;


public class VisitDate {
    private final Integer date;

    private VisitDate(Integer date) {
        this.date = date;
    }

    public static VisitDate of(String date) {
        Integer parsedDate = isValidDate(date);
        validateRange(parsedDate);
        return new VisitDate(parsedDate);
    }

    public Integer getDate() {
        return date;
    }
}
