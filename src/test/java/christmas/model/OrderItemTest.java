package christmas.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void 생성_테스트() {
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        OrderItem orderItem = OrderItem.of(input);

        assertEquals(1, orderItem.getCount("티본스테이크"));
        assertEquals(1, orderItem.getCount("바비큐립"));
        assertEquals(2, orderItem.getCount("초코케이크"));
        assertEquals(1, orderItem.getCount("제로콜라"));
    }

    @Test
    void 예외_없는_메뉴_주문() {
        assertThrows(IllegalStateException.class,
                () -> OrderItem.of("삼겹살-1,바비큐립-1,초코케이크-2,초코케이크-2,제로콜라-1"));
    }

    @Test
    void 예외_입력형식_불일치() {
        assertThrows(IllegalArgumentException.class,
                () -> OrderItem.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-k"));
    }

    @Test
    void 예외_입력패턴_불일치() {
        assertThrows(IllegalArgumentException.class,
                () -> OrderItem.of("티본스테이크-1 바비큐립-1 초코케이크-2 제로콜라-1"));
        assertThrows(IllegalArgumentException.class,
                () -> OrderItem.of("티본스테이크-1|바비큐립-1|초코케이크-2|제로콜라-1"));
    }

    @Test
    void 예외_중복_메뉴() {
        assertThrows(IllegalStateException.class,
                () -> OrderItem.of("티본스테이크-1,바비큐립-1,초코케이크-2,초코케이크-2,제로콜라-1"));
    }

    @Test
    void 예외_주문_개수_초과() {
        assertThrows(IllegalStateException.class,
                ()-> OrderItem.of("티본스테이크-1,바비큐립-1,초코케이크-17,초코케이크-1,제로콜라-1"));
    }
}