package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @Nested
    @DisplayName("생성 성공 테스트")
    class SuccessTest {
        @DisplayName("유효한 금액 문자열로 객체를 생성할 수 있다.")
        @Test
        void create_with_valid_amount() {
            // given
            String validInput = "8000";

            // when & then
            assertThatCode(() -> PurchaseAmount.of(validInput))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("예외 발생 테스트")
    class ExceptionTest {

        @DisplayName("입력값이 null이거나 빈 문자열이면 예외가 발생한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "  ", "\t", "\n"})
        void create_with_null_or_blank_input(String invalidInput) {
            // when & then
            assertThatThrownBy(() -> PurchaseAmount.of(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.EMPTY_AMOUNT_INPUT.getMessage());
        }

        @DisplayName("입력값이 숫자가 아니면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"abc", "1000a", " 1000", "1,000"})
        void create_with_non_numeric_input(String invalidInput) {
            // when & then
            assertThatThrownBy(() -> PurchaseAmount.of(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_AMOUNT_NUMBER_FORMAT.getMessage());
        }

        @DisplayName("입력 금액이 1,000원 단위가 아니면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"1001", "2500", "999"})
        void create_with_invalid_unit(String invalidInput) {
            // when & then
            assertThatThrownBy(() -> PurchaseAmount.of(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_PURCHASE_UNIT.getMessage());
        }
    }


}