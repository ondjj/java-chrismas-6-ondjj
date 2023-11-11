package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayEventTest {
    private Integer monday;
    private Integer friday;
    private Integer saturday;
    private Integer sunday;

    WeekdayEvent weekdayMonday;
    WeekdayEvent weekdayFriday;
    WeekdayEvent weekdaySaturday;
    WeekdayEvent weekdaySunday;

    @BeforeEach
    void setUp() {
        monday = 25;
        friday = 22;
        saturday = 23;
        sunday = 24;

        weekdayMonday = WeekdayEvent.of(monday);
        weekdayFriday = WeekdayEvent.of(friday);
        weekdaySaturday = WeekdayEvent.of(saturday);
        weekdaySunday = WeekdayEvent.of(sunday);
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
        assertThat(weekdayMonday.itemDiscount()).isEqualTo(2023);
        assertThat(weekdayFriday.itemDiscount()).isEqualTo(0);
        assertThat(weekdaySaturday.itemDiscount()).isEqualTo(0);
        assertThat(weekdaySunday.itemDiscount()).isEqualTo(2023);
    }
}