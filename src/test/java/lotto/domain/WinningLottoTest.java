package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호와 보너스 번호가 중복되지 않으면 정상적으로 생성된다.")
    void create_with_valid_numbers() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        BonusNumber bonusNumber = BonusNumber.of("7");

        // when & then
        assertThatCode(() -> WinningLotto.of(winningNumbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    void should_throw_exception_when_bonus_number_is_duplicated() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        BonusNumber duplicateBonusNumber = BonusNumber.of("6"); // 중복되는 보너스 번호

        // when & then
        assertThatThrownBy(() -> WinningLotto.of(winningNumbers, duplicateBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_LOTTO_NUMBERS_WITH_BONUS_NUMBER.getMessage());
    }

}