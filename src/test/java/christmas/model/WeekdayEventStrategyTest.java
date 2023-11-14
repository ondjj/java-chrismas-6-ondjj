package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayEventStrategyTest {

    WeekdayEventStrategy weekdayMonday;
    WeekdayEventStrategy weekdayFriday;
    WeekdayEventStrategy weekdaySaturday;
    WeekdayEventStrategy weekdaySunday;

    @BeforeEach
    void setUp() {
        Integer monday = 25;
        Integer friday = 22;
        Integer saturday = 23;
        Integer sunday = 24;
        Integer quantity = 3;

        weekdayMonday = WeekdayEventStrategy.of(monday, quantity);
        weekdayFriday = WeekdayEventStrategy.of(friday, quantity);
        weekdaySaturday = WeekdayEventStrategy.of(saturday, quantity);
        weekdaySunday = WeekdayEventStrategy.of(sunday, quantity);
    }

    @Test
    void 생성_테스트() {
        assertThat(weekdayMonday).isNotNull();
        assertThat(weekdayFriday).isNotNull();
        assertThat(weekdaySaturday).isNotNull();
        assertThat(weekdaySunday).isNotNull();
    }

    @DisplayName("일-월(평일) 할인 적용, 금-토(주말) 할인 적용")
    @Test
    void itemDiscount() {
        assertThat(weekdayMonday.itemDiscount()).isEqualTo(6069);
        assertThat(weekdayFriday.itemDiscount()).isEqualTo(0);
        assertThat(weekdaySaturday.itemDiscount()).isEqualTo(0);
        assertThat(weekdaySunday.itemDiscount()).isEqualTo(6069);
    }
}