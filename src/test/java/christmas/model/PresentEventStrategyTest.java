package christmas.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PresentEventStrategyTest {
    private PresentEventStrategy overPresentEvent;
    private PresentEventStrategy underPresentEvent;

    @BeforeEach
    void setUp() {
        Integer underPrice = 11999;
        Integer overPrice = 120000;
        underPresentEvent = PresentEventStrategy.of(underPrice);
        overPresentEvent = PresentEventStrategy.of(overPrice);
    }

    @Test
    void 생성_테스트() {
        assertThat(underPresentEvent).isNotNull();
        assertThat(overPresentEvent).isNotNull();
    }

    @Test
    void 할인_금액_테스트() {
        assertThat(overPresentEvent.itemDiscount()).isEqualTo(25000);
    }

    @DisplayName("가격에 따라 증정 아이템 가격을 계산합니다.")
    @Test
    void 증정_범위_테스트() {
        // 증정 범위 내의 경우에는 할인 금액이 가격과 같아야 합니다.
        assertThat(underPresentEvent.itemDiscount()).isEqualTo(0);

        // 증정 범위를 넘어간 경우에는 할인 금액이 PRESENT_DISCOUNT와 같아야 합니다.
        assertThat(overPresentEvent.itemDiscount()).isEqualTo(25000);
    }
}
