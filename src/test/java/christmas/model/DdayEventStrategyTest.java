package christmas.model;

import static org.assertj.core.api.Assertions.*;

import christmas.model.event.DdayEventStrategy;
import christmas.model.event.EventStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class DdayEventStrategyTest {

    private Integer startDay;
    private Integer endDay;
    private Integer price;
    private EventStrategy ddayEventStrategy;
    private EventStrategy endDayEventStrategy;

    @BeforeEach
    void setUp() {
        startDay = 1;
        endDay = 25;
        price = 13000;
    }

    @Test
    void 생성_테스트() {
        ddayEventStrategy = DdayEventStrategy.of(startDay, price);
        assertThat(ddayEventStrategy).isNotNull();
    }

    @Test
    void 할인_테스트() {
        ddayEventStrategy = DdayEventStrategy.of(startDay, price);
        endDayEventStrategy = DdayEventStrategy.of(endDay, price);

        assertThat(ddayEventStrategy.itemDiscount()).isEqualTo(1000);
        assertThat(endDayEventStrategy.itemDiscount()).isEqualTo(3400);
    }

    @DisplayName("D-Day에 따라 할인이 적용됩니다.")
    @Test
    void 이벤트_기간_테스트() {
        ddayEventStrategy = DdayEventStrategy.of(startDay, price);
        endDayEventStrategy = DdayEventStrategy.of(endDay, price);
        EventStrategy overDayEventStrategy = DdayEventStrategy.of(endDay + 1, price);

        assertThat(ddayEventStrategy.itemDiscount()).isEqualTo(1000);
        assertThat(endDayEventStrategy.itemDiscount()).isEqualTo(3400);
        assertThat(overDayEventStrategy.itemDiscount()).isEqualTo(0);
    }
}
