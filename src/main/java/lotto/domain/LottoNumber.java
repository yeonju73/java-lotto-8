package lotto.domain;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber fromInteger(int number) {
        validateRange(number);
        return new LottoNumber(number);
    }

    public static void validateRange(int number) {
        if (number < LottoPolicy.MIN_NUMBER.getValue()
                || number > LottoPolicy.MAX_NUMBER.getValue()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber that)) {
            return false;
        }
        return this.number == that.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }
}
