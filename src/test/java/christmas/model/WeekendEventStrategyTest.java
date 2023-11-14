package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendEventStrategyTest {

    WeekendEventStrategy weekendMonday;
    WeekendEventStrategy weekendFriday;
    WeekendEventStrategy weekendSaturday;
    WeekendEventStrategy weekendSunday;

    @BeforeEach
    void setUp() {
        Integer monday = 25;
        Integer friday = 22;
        Integer saturday = 23;
        Integer sunday = 24;
        Integer quantity = 2;

        weekendMonday = WeekendEventStrategy.of(monday, quantity);
        weekendFriday = WeekendEventStrategy.of(friday, quantity);
        weekendSaturday = WeekendEventStrategy.of(saturday, quantity);
        weekendSunday = WeekendEventStrategy.of(sunday, quantity);
    }

    @Test
    void 생성_테스트() {
        assertThat(weekendMonday).isNotNull();
        assertThat(weekendFriday).isNotNull();
        assertThat(weekendSaturday).isNotNull();
    }

    @DisplayName("금-토(주말) 할인 적용, 일-월(평일) 할인 미적용")
    @Test
    void itemDiscount() {
        assertThat(weekendMonday.itemDiscount()).isEqualTo(0);
        assertThat(weekendFriday.itemDiscount()).isEqualTo(4046);
        assertThat(weekendSaturday.itemDiscount()).isEqualTo(4046);
        assertThat(weekendSunday.itemDiscount()).isEqualTo(0);
    }
}