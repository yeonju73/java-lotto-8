package lotto;

import lotto.controller.LottoGameController;
import lotto.domain.LottoFactory;
import lotto.service.LottoService;
import lotto.util.LottoResultCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoFactory lottoFactory = new LottoFactory();
        LottoResultCalculator lottoResultCalculator = new LottoResultCalculator();
        LottoService lottoService = new LottoService(lottoFactory, lottoResultCalculator);

        LottoGameController lottoGameController = new LottoGameController(
                inputView,
                outputView,
                lottoService
        );

        lottoGameController.run();
    }
}
