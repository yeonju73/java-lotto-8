package lotto.util;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitCalculatorTest {

    @DisplayName("총상금과 구매 금액에 따라 수익률을 정확하게 계산해야 한다.")
    @ParameterizedTest
    @CsvSource({
            "5000, 8000, 62.5",
            "8000, 8000, 100.0",
            "16000, 8000, 200.0",
            "0, 5000, 0.0",
            "5000, 3000, 166.67"    // 소수점 둘째 자리 반올림 테스트
    })
    void calculate_profit_rate_correctly_for_various_scenarios(
            long totalPrize, int purchaseAmount, double expectedRate) {

        // when
        double actualRate = ProfitCalculator.calculateProfitRate(totalPrize, purchaseAmount);

        // then
        assertThat(actualRate).isEqualTo(expectedRate);
    }

}