package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {

    private LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        lottoFactory = new LottoFactory();
    }

    @DisplayName("구입 금액에 따라 정확한 개수의 로또를 발행해야 한다.")
    @ParameterizedTest
    @CsvSource({"5000, 5", "8000, 8", "12000, 12"})
    void should_issue_lottos_according_to_purchase_amount(String inputAmount, int expectedCount) {
        // given
        PurchaseAmount purchaseAmount = PurchaseAmount.of(inputAmount);

        // when
        Lottos issuedLottos = lottoFactory.issueLottos(purchaseAmount);

        // then
        assertThat(issuedLottos).isNotNull();
        assertThat(issuedLottos.getLottoCount()).isEqualTo(expectedCount);
    }


}