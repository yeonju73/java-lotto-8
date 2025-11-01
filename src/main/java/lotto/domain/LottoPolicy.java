package lotto.domain;

public enum LottoPolicy {
    MIN_NUMBER(1),
    MAX_NUMBER(45),
    NUMBER_COUNT(6),
    PRICE(1000);

    private final int value;

    LottoPolicy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
