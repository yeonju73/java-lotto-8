package lotto.view;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

public class OutputView {

    public static final String ISSUED_LOTTOS_PROMPT_MESSAGE = "개를 구매했습니다.";

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printIssuedLottos(Lottos lottos) {
        System.out.println("\n" + lottos.getLottoCount() + ISSUED_LOTTOS_PROMPT_MESSAGE);

        List<List<LottoNumber>> sortedLottoNumbers = lottos.getSortedLottoNumbersList();
        sortedLottoNumbers.forEach(System.out::println);
    }


}
