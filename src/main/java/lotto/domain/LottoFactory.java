package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;

public class LottoFactory {
    public Lottos issueLottos(PurchaseAmount purchaseAmount) {
        int count = purchaseAmount.calculateLottoCount();

        List<Lotto> lottoList = IntStream.range(0, count)
                .mapToObj(i -> createLotto())
                .toList();

        return Lottos.of(lottoList);
    }

    private Lotto createLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                LottoPolicy.MIN_NUMBER.getValue(),
                LottoPolicy.MAX_NUMBER.getValue(),
                LottoPolicy.NUMBER_COUNT.getValue()
        );

        return new Lotto(numbers);
    }
}
