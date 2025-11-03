package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.exception.ErrorMessage;

public class LottoNumbersParser {
    private static final String DELIMITER = ",";

    private LottoNumbersParser() {
    }

    public static List<Integer> parse(String input) {
        validateBlank(input);
        return Arrays.stream(input.split(DELIMITER, -1))
                .map(String::trim)
                .map(LottoNumbersParser::validateAndParseInt)
                .toList();
    }

    private static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_LOTTO_INPUT.getMessage());
        }
    }

    public static int validateAndParseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_FORMAT.getMessage());
        }
    }

}
