package lotto.domain;

public class LottoNumber {
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber of(int number) {
        validateRange(number);
        return new LottoNumber(number);
    }

    public static void validateRange(int number) {
        if (number < LottoPolicy.MIN_NUMBER.getValue()
                || number > LottoPolicy.MAX_NUMBER.getValue()) {
            throw new IllegalArgumentException();
        }
    }

}
