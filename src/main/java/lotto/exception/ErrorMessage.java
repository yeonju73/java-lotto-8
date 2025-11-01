package lotto.exception;

public enum ErrorMessage {
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1부터 45 사이여야 합니다."),
    EMPTY_AMOUNT_INPUT("구입 금액을 입력해 주세요."),
    INVALID_AMOUNT_NUMBER_FORMAT("구입 금액은 숫자만 입력 가능합니다."),
    INVALID_PURCHASE_UNIT("구입 금액은 1,000원 단위여야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
