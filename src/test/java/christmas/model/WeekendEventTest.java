package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendEventTest {

    private Integer monday;
    private Integer friday;
    private Integer saturday;
    private Integer sunday;

    WeekendEvent weekendMonday;
    WeekendEvent weekendFriday;
    WeekendEvent weekendSaturday;
    WeekendEvent weekendSunday;

    @BeforeEach
    void setUp() {
        monday = 25;
        friday = 22;
        saturday = 23;
        sunday = 24;

        weekendMonday = WeekendEvent.of(monday);
        weekendFriday = WeekendEvent.of(friday);
        weekendSaturday = WeekendEvent.of(saturday);
        weekendSunday = WeekendEvent.of(sunday);
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
        assertThat(weekendFriday.itemDiscount()).isEqualTo(2023);
        assertThat(weekendSaturday.itemDiscount()).isEqualTo(2023);
        assertThat(weekendSunday.itemDiscount()).isEqualTo(0);
    }
}