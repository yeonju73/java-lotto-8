package lotto.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResultCalculator {

    public final Map<LottoRank, Integer> rankCount = new EnumMap<>(LottoRank.class);

    public LottoResultCalculator() {
        for (LottoRank rank : LottoRank.values()) {
            rankCount.put(rank, 0);
        }
    }

    public void calculateResults(Lottos purchasedLottos, WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = purchasedLottos.matchAll(winningLotto);
        lottoRanks.forEach(lottoRank -> {
            if (lottoRank != LottoRank.NONE) {
                rankCount.put(lottoRank, rankCount.get(lottoRank) + 1);
            }
        });
    }

    public Map<LottoRank, Integer> getRankCount() {
        return Collections.unmodifiableMap(rankCount);
    }

    public long getTotalPrizeMoney() {
        return rankCount.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}
