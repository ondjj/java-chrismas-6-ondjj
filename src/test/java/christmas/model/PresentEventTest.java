package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PresentEventTest {
    private PresentEvent overPresentEvent;
    private PresentEvent underPresentEvent;
    private Integer underPrice;
    private Integer overPrice;

    @BeforeEach
    void setUp() {
        underPrice = 11999;
        overPrice = 120000;
        underPresentEvent = PresentEvent.of(underPrice);
        overPresentEvent = PresentEvent.of(overPrice);
    }

    @Test
    void 생성_테스트() {
        overPresentEvent = PresentEvent.of(underPrice);
        assertThat(overPresentEvent).isNotNull();
    }

    @Test
    void 할인_금액_테스트() {
        assertThat(overPresentEvent.itemDiscount()).isEqualTo(overPrice - 25000);
    }

    @DisplayName("가격에 따라 증정 아이템 가격을 계산합니다.")
    @Test
    void 증정_범위_테스트() {
        assertThat(underPresentEvent.itemDiscount()).isEqualTo(11999);
        assertThat(overPresentEvent.itemDiscount()).isEqualTo(95000);
    }
}