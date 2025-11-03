package lotto.service;

import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.LottoFactory;
import lotto.domain.LottoPolicy;
import lotto.domain.LottoRank;
import lotto.domain.LottoResultCalculator;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.util.LottoNumbersParser;
import lotto.util.ProfitCalculator;

public class LottoService {
    private final LottoFactory lottoFactory;
    private final LottoResultCalculator resultCalculator;

    public LottoService(LottoFactory lottoFactory, LottoResultCalculator resultCalculator) {
        this.lottoFactory = lottoFactory;
        this.resultCalculator = resultCalculator;
    }

    public Lottos issueLottos(PurchaseAmount amount) {
        return lottoFactory.issueLottos(amount);
    }

    public Map<LottoRank, Integer> calculateResults(Lottos lottos, WinningLotto winningLotto) {
        resultCalculator.calculateResults(lottos, winningLotto);
        return resultCalculator.getRankCount();
    }

    public double calculateProfitRate(Lottos lottos) {
        long totalPrize = resultCalculator.getTotalPrizeMoney();
        return ProfitCalculator.calculateProfitRate(
                totalPrize, lottos.getLottoCount() * LottoPolicy.PRICE.getValue());
    }

    public WinningLotto createWinningLotto(String winningNumberInput, String bonusNumberInput) {
        List<Integer> winningNumbers = LottoNumbersParser.parse(winningNumberInput);
        BonusNumber bonusNumber = BonusNumber.of(bonusNumberInput);
        return WinningLotto.of(winningNumbers, bonusNumber);
    }
}
