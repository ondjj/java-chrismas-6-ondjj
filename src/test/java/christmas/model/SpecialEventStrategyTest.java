package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.event.EventStrategy;
import christmas.model.event.SpecialEventStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialEventStrategyTest {
    private EventStrategy specialEventStrategy;
    private EventStrategy christmasSpecialEventStrategy;
    private EventStrategy weekdaySpecialEventStrategy;

    @BeforeEach
    void setUp() {
        Integer sunday = 24;
        Integer christmas = 25;
        Integer weekday = 26;
        specialEventStrategy = SpecialEventStrategy.of(sunday, 10000);
        christmasSpecialEventStrategy = SpecialEventStrategy.of(christmas, 10000);
        weekdaySpecialEventStrategy = SpecialEventStrategy.of(weekday, 10000);
    }

    @Test
    void 생성_테스트() {
        assertThat(specialEventStrategy).isNotNull();
        assertThat(christmasSpecialEventStrategy).isNotNull();
        assertThat(weekdaySpecialEventStrategy).isNotNull();
    }

    @DisplayName("별이 적용된 요일에 따라 할인을 적용합니다.")
    @Test
    void 할인_테스트() {
        assertThat(specialEventStrategy.itemDiscount()).isEqualTo(1000);
        assertThat(christmasSpecialEventStrategy.itemDiscount()).isEqualTo(1000);
        assertThat(weekdaySpecialEventStrategy.itemDiscount()).isEqualTo(0);
    }
}