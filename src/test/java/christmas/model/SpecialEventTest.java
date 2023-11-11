package christmas.model;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialEventTest {
    private Integer sunday;
    private Integer christmas;
    private Integer weekday;
    private Integer price;
    private Event specialEvent;
    private Event christmasSpecialEvent;
    private Event weekdaySpecialEvent;

    @BeforeEach
    void setUp() {
        sunday = 24;
        christmas = 25;
        weekday = 26;
        price = 15000;
        specialEvent = SpecialEvent.of(sunday, price);
        christmasSpecialEvent = SpecialEvent.of(christmas, price);
        weekdaySpecialEvent = SpecialEvent.of(weekday, price);
    }

    @Test
    void 생성_테스트() {
        assertThat(specialEvent).isNotNull();
        assertThat(christmasSpecialEvent).isNotNull();
        assertThat(weekdaySpecialEvent).isNotNull();
    }

    @DisplayName("별이 적용된 요일에 따라 할인을 적용합니다.")
    @Test
    void 할인_테스트() {
        assertThat(specialEvent.itemDiscount()).isEqualTo(14000);
        assertThat(christmasSpecialEvent.itemDiscount()).isEqualTo(14000);
        assertThat(weekdaySpecialEvent.itemDiscount()).isEqualTo(15000);
    }
}