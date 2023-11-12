package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialEventTestStrategy {
    private Integer sunday;
    private Integer christmas;
    private Integer weekday;
    private Integer price;
    private EventStrategy specialEventStrategy;
    private EventStrategy christmasSpecialEventStrategy;
    private EventStrategy weekdaySpecialEventStrategy;

    @BeforeEach
    void setUp() {
        sunday = 24;
        christmas = 25;
        weekday = 26;
        price = 15000;
        specialEventStrategy = SpecialEventStrategy.of(sunday);
        christmasSpecialEventStrategy = SpecialEventStrategy.of(christmas);
        weekdaySpecialEventStrategy = SpecialEventStrategy.of(weekday);
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