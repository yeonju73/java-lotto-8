package lotto.domain;

import lotto.exception.ErrorMessage;

public class PurchaseAmount {
    private final int amount;

    private PurchaseAmount(int amount) {
        this.amount = amount;
    }

    public static PurchaseAmount of(String amountInput) {
        validateNotEmpty(amountInput);
        int amountNumber = validateAndParseNumber(amountInput);
        validateUnit(amountNumber);
        return new PurchaseAmount(amountNumber);
    }

    private static void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_AMOUNT_INPUT.getMessage());
        }
    }

    private static int validateAndParseNumber(String numberInput) {
        try {
            return Integer.parseInt(numberInput);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT_NUMBER_FORMAT.getMessage());
        }
    }

    private static void validateUnit(int amount) {
        if (amount % LottoPolicy.PRICE.getValue() != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_UNIT.getMessage());
        }
    }

    public int calculateLottoCount() {
        return amount / LottoPolicy.PRICE.getValue();
    }
}
