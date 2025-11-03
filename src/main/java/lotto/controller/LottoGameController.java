package lotto.controller;

import java.util.List;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.util.LottoNumbersParser;
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

        WinningLotto winningLotto = getWinningLotto();

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

    private WinningLotto getWinningLotto() {
        while (true) {
            try {
                String winningLottoInput = inputView.readWinningNumbers();
                List<Integer> parsedNumber = LottoNumbersParser.parse(winningLottoInput);
                return lottoFactory.issueWinningLotto(parsedNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
