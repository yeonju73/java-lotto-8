package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final String INPUT_PURCHASE_AMOUNT_PROMPT_MESSAGE = "구입금액을 입력해 주세요.";

    public String readPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT_PROMPT_MESSAGE);
        return Console.readLine().trim();
    }
}
