package christmas.model;


public interface Event {
    Integer itemDiscount();

    default boolean isEventDate() {
        return false;
    }
}
