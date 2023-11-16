package christmas.util.enums;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badgeName;
    private final Integer badgeAmount;

    Badge(String badgeName, Integer badgeAmount) {
        this.badgeName = badgeName;
        this.badgeAmount = badgeAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public Integer getBadgeAmount() {
        return badgeAmount;
    }

    public static Badge getBadgeByAmount(Integer amount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> amount >= badge.getBadgeAmount())
                .max(Comparator.comparing(Badge::getBadgeAmount))
                .orElse(null);
    }
}
