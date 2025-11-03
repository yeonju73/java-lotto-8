package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @Nested
    @DisplayName("생성 성공 테스트")
    class SuccessTest {
        @ParameterizedTest
        @ValueSource(strings = {"1", "7", "45"})
        @DisplayName("유효한 숫자 형식과 범위의 문자열로 객체를 생성할 수 있다.")
        void create_with_valid_string(String validInput) {
            assertThatCode(() -> BonusNumber.of(validInput))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("예외 발생 테스트")
    class ExceptionTest {
        // BonusNumber의 중복 검증 테스트는 여기서 제거됩니다!

        @ParameterizedTest
        @ValueSource(strings = {"a", " ", "1a", "!"})
        @DisplayName("입력값이 숫자가 아니면 예외가 발생한다.")
        void create_with_non_numeric_input(String invalidInput) {
            assertThatThrownBy(() -> BonusNumber.of(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBER_FORMAT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "46", "-1"})
        @DisplayName("입력값이 로또 번호 범위(1~45)를 벗어나면 예외가 발생한다.")
        void create_with_out_of_range_number(String invalidInput) {
            assertThatThrownBy(() -> BonusNumber.of(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}