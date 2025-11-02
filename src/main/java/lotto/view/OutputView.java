package lotto.view;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printIssuedLottos(Lottos lottos) {
        System.out.println("\n" + lottos.getLottoCount() + "개를 구매했습니다.");

        List<List<LottoNumber>> sortedLottoNumbers = lottos.getSortedLottoNumbersList();
        sortedLottoNumbers.forEach(System.out::println);
    }


}
