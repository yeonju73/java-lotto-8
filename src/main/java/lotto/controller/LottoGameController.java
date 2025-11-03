package lotto.controller;

import java.util.Map;
import java.util.function.Supplier;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoGameController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();

        Lottos lottos = lottoService.issueLottos(purchaseAmount);
        outputView.printIssuedLottos(lottos);

        WinningLotto winningLotto = getWinningLotto();

        Map<LottoRank, Integer> results = lottoService.calculateResults(lottos, winningLotto);
        double profitRate = lottoService.calculateProfitRate(lottos);

        outputView.printStatistics(results, profitRate);
    }

    private <T> T readUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private PurchaseAmount getPurchaseAmount() {
        return readUntilValid(() -> PurchaseAmount.of(inputView.readPurchaseAmount()));
    }

    private WinningLotto getWinningLotto() {
        return readUntilValid(() -> {
            String winningNumberInput = inputView.readWinningNumbers();
            String bonusNumberInput = inputView.readBonusNumbers();
            return lottoService.createWinningLotto(winningNumberInput, bonusNumberInput);
        });
    }
}
