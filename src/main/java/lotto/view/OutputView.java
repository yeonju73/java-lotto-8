package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.Lottos;

public class OutputView {

    public static final String ISSUED_LOTTOS_PROMPT_MESSAGE = "개를 구매했습니다.";
    private static final String STATISTICS_HEADER = "\n당첨 통계\n---";
    private static final String PROFIT_FORMAT = "총 수익률은 %.1f%%입니다.";

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printIssuedLottos(Lottos lottos) {
        System.out.println("\n" + lottos.getLottoCount() + ISSUED_LOTTOS_PROMPT_MESSAGE);

        List<List<LottoNumber>> sortedLottoNumbers = lottos.getSortedLottoNumbersList();
        sortedLottoNumbers.forEach(System.out::println);
    }

    public void printStatistics(Map<LottoRank, Integer> rankCountMap, double profitRate) {
        System.out.println(STATISTICS_HEADER);
        printMatchCount(rankCountMap);
        System.out.printf(PROFIT_FORMAT, profitRate);
    }

    private void printMatchCount(Map<LottoRank, Integer> rankCountMap) {
        LottoRank.streamRanksSortedByMatchCount()
                .filter(rank -> rank != LottoRank.NONE)
                .forEach(rank -> System.out.printf("%s - %d개%n",
                        formatRankMessage(rank),
                        rankCountMap.getOrDefault(rank, 0)));
    }

    private String formatRankMessage(LottoRank rank) {
        if (rank == LottoRank.SECOND) {
            return String.format("5개 일치, 보너스 볼 일치 (%s원)", formatMoney(rank.getPrize()));
        }
        return String.format("%d개 일치 (%s원)", rank.getMatchCount(), formatMoney(rank.getPrize()));
    }

    private String formatMoney(int amount) {
        return new DecimalFormat("#,###").format(amount);
    }


}
