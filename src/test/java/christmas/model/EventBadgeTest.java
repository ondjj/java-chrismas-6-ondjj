package christmas.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EventBadgeTest {

    @Test
    void 이벤트_범위_미만일_때_뱃지는_없음() {
        Integer totalPrice = 4999;
        String badge = EventBadge.of(totalPrice).determineBadge();
        assertThat(badge).isEqualTo("없음");
    }

    @Test
    void 이벤트_범위와_같은_가격일_때_별_뱃지() {
        Integer totalPrice = 5000;
        String badge = EventBadge.of(totalPrice).determineBadge();
        assertThat(badge).isEqualTo("별");
    }

    @Test
    void 이벤트_범위를_넘은_가격일_때_트리_뱃지() {
        Integer totalPrice = 15000;
        String badge = EventBadge.of(totalPrice).determineBadge();
        assertThat(badge).isEqualTo("트리");
    }

    @Test
    void 이벤트_범위를_넘은_가격일_때_산타_뱃지() {
        Integer totalPrice = 20000;
        String badge = EventBadge.of(totalPrice).determineBadge();
        assertThat(badge).isEqualTo("산타");
    }
}