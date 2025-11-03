package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumbersParserTest {
    
    @Nested
    @DisplayName("파싱 성공 테스트")
    class SuccessTest {

        @Test
        @DisplayName("쉼표로 구분된 올바른 숫자 문자열을 파싱해야 한다.")
        void parse_with_valid_numbers() {
            // given
            String input = "1,2,3,4,5,6";

            // when
            List<Integer> result = LottoNumbersParser.parse(input);

            // then
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @Test
        @DisplayName("숫자 앞뒤에 공백이 있어도 정상적으로 파싱해야 한다.")
        void parse_with_spaces() {
            // given
            String input = " 1,  2,3 , 4,5, 6 ";

            // when
            List<Integer> result = LottoNumbersParser.parse(input);

            // then
            assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
        }
    }

    @Nested
    @DisplayName("예외 발생 테스트")
    class ExceptionTest {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "  ", "\t", "\n"})
        @DisplayName("입력값이 null이거나 비어있으면 예외가 발생한다.")
        void parse_with_null_or_blank_input(String invalidInput) {
            // when & then
            assertThatThrownBy(() -> LottoNumbersParser.parse(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.EMPTY_LOTTO_INPUT.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "1,a,3,4,5,6",      // 문자가 포함된 경우
                "1,2, 3.5,4,5,6",    // 실수가 포함된 경우
                "1,2,3,4,5,",       // 마지막에 쉼표만 있는 경우
                ",1,2,3,4,5",       // 처음에 쉼표만 있는 경우
                "1,2,,3,4,5"        // 중간에 쉼표가 연속된 경우
        })
        @DisplayName("숫자로 변환할 수 없는 값이 포함되어 있으면 예외가 발생한다.")
        void parse_with_non_numeric_input(String invalidInput) {
            // when & then
            assertThatThrownBy(() -> LottoNumbersParser.parse(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_LOTTO_NUMBER_FORMAT.getMessage());
        }
    }

}