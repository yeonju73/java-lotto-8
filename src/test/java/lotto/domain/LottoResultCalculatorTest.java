package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultCalculatorTest {
    private LottoResultCalculator calculator;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        calculator = new LottoResultCalculator();

        List<Integer> lotto = List.of(1, 2, 3, 4, 5, 6);
        BonusNumber bonus = BonusNumber.of("7");
        winningLotto = WinningLotto.of(lotto, bonus);
    }

    @Test
    @DisplayName("다양한 등수의 로또 결과와 총상금을 정확하게 계산해야 한다.")
    void calculate_results_and_total_prize_correctly() {
        // given: 각 등수에 해당하는 로또들을 포함하는 Lottos 객체 생성
        Lottos purchasedLottos = Lottos.of(List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)), // 5등
                new Lotto(List.of(1, 2, 3, 13, 14, 15)), // 5등
                new Lotto(List.of(1, 2, 3, 4, 20, 21)), // 4등
                new Lotto(List.of(1, 2, 3, 4, 5, 22)), // 3등 (보너스 불일치)
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),  // 2등 (보너스 일치)
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝
        ));

        // when: 결과 계산 실행
        calculator.calculateResults(purchasedLottos, winningLotto);

        // then 1: 등수별 당첨 횟수 검증
        Map<LottoRank, Integer> rankCount = calculator.getRankCount();
        assertThat(rankCount.get(LottoRank.FIFTH)).isEqualTo(2);
        assertThat(rankCount.get(LottoRank.FOURTH)).isEqualTo(1);
        assertThat(rankCount.get(LottoRank.THIRD)).isEqualTo(1);
        assertThat(rankCount.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(rankCount.get(LottoRank.FIRST)).isZero(); // 1등은 없음

        // then 2: 총상금 검증
        long expectedTotalPrize = (5000L * 2) + 50000L + 1500000L + 30000000L;
        long totalPrize = calculator.getTotalPrizeMoney();
        assertThat(totalPrize).isEqualTo(expectedTotalPrize);
    }

}