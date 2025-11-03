package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean matchBonus;
    private final int prize;

    LottoRank(int matchCount, boolean matchBonus, int prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public static LottoRank valueOf(int matchCount, boolean matchBonus) {
        return Arrays.stream(values())
                .filter(lottoRank ->
                        lottoRank.matchCount == matchCount &&
                                lottoRank.matchBonus == matchBonus)
                .findFirst()
                .orElse(NONE);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public static Stream<LottoRank> streamRanksSortedByMatchCount() {
        return Arrays.stream(values())
                .filter(rank -> rank != NONE)
                .sorted(Comparator
                        .comparingInt(LottoRank::getMatchCount)
                        .thenComparing(LottoRank::isMatchBonus));
    }

}
