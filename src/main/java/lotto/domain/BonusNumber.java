package lotto.domain;

public class BonusNumber {
    private final LottoNumber bonusNumber;

    private BonusNumber(LottoNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber of(String bonusNumber) {
        LottoNumber number = LottoNumber.fromString(bonusNumber);
        return new BonusNumber(number);
    }

    public boolean isSameAs(LottoNumber other) {
        return this.bonusNumber.equals(other);
    }
}
