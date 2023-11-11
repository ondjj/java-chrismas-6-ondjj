package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VisitDateTest {
    VisitDate visitDate;

    @BeforeEach
    void setUp() {
        visitDate = VisitDate.of("12");
    }

    @DisplayName("정적 팩토리 메서드를 통한 생성 테스트")
    @Test
    void 생성_테스트() {
        assertThat(12).isEqualTo(visitDate.getDate());
    }

    @DisplayName("문자 입력 예외 테스트")
    @Test
    void 예외_문자입력() {
        Throwable throwable = catchThrowable(() -> VisitDate.of("a"));

        assertThat(throwable)
               .isInstanceOf(NumberFormatException.class);
    }

    @DisplayName("범위를 벗어난 입력 값 테스트")
    @Test
    void 예외_범위를_벗어난_입력() {
        Throwable throwableOverRange = catchThrowable(() -> VisitDate.of("40"));
        Throwable throwableUnderRange = catchThrowable(() -> VisitDate.of("0"));

        assertThat(throwableOverRange)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        assertThat(throwableUnderRange)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}