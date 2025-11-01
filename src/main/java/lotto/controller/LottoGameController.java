package lotto.controller;

import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoFactory lottoFactory;

    public LottoGameController(InputView inputView, OutputView outputView, LottoFactory lottoFactory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoFactory = lottoFactory;
    }

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        Lottos lottos = lottoFactory.issueLottos(purchaseAmount);
        outputView.printIssuedLottos(lottos);
    }

    private PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                String amountInput = inputView.readPurchaseAmount();
                return PurchaseAmount.of(amountInput);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
