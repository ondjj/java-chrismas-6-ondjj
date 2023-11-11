package christmas.model;

public class PresentEvent implements Event {
    private static final int PRESENT_DISCOUNT = 25000;
    private static final int PRESENT_RANGE = 120000;

    private final Integer price;

    private PresentEvent(final Integer price) {
        this.price = price;
    }

    public static PresentEvent of(final Integer price) {
        return new PresentEvent(price);
    }

    @Override
    public Integer itemDiscount() {
        if (isPresentRange()) {
            return price - PRESENT_DISCOUNT;
        }
        return price;
    }

    private boolean isPresentRange() {
        return price >= PRESENT_RANGE;
    }
}
