package lotto.domain;

import java.util.List;
import lotto.exception.ErrorMessage;

public class WinningLotto {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    private WinningLotto(Lotto winningLotto, BonusNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(List<Integer> winningNumbers, BonusNumber bonusNumber) {
        Lotto winningLotto = new Lotto(winningNumbers);
        validateDuplication(winningLotto, bonusNumber);
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private static void validateDuplication(Lotto winningLotto, BonusNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBERS_WITH_BONUS_NUMBER.getMessage());
        }
    }
}
