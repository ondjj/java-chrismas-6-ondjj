package christmas.util.enums;

public enum EventType {
    NONE("없음", "없음"),
    D_DAY("크리스마스 디데이 할인", "없음"),
    PRESENT("증정 이벤트", "샴페인 1개", "없음"),
    SPECIAL("특별 할인", "없음"),
    WEEKDAY("평일 할인", "없음"),
    WEEKEND("주말 할인", "없음");

    private final String description;
    private final String none;
    private String presentDescription;

    EventType(String description, String none) {
        this.description = description;
        this.none = none;
    }

    EventType(String description, String presentDescription, String none) {
        this.description = description;
        this.presentDescription = presentDescription;
        this.none = none;
    }

    public String getDescription() {
        return description;
    }

    public String getPresentDescription() {
        return presentDescription;
    }

    public String getNone() {
        return none;
    }
}
