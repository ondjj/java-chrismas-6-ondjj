package christmas.model;


public interface EventCalendar {
    Integer itemDiscount();

    default boolean isEventDate() {
        return false;
    }
}
