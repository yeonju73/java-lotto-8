package lotto;

import lotto.controller.LottoGameController;
import lotto.domain.LottoFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoFactory lottoFactory = new LottoFactory();

        LottoGameController lottoGameController = new LottoGameController(
                inputView,
                outputView,
                lottoFactory
        );

        lottoGameController.run();
    }
}
