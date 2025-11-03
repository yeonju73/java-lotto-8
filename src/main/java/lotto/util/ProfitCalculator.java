package lotto.util;

public class ProfitCalculator {

    public static double calculateProfitRate(long totalPrize, int purchaseAmount) {
        double rate = ((double) totalPrize / purchaseAmount) * 100;
        return Math.round(rate * 100) / 100.0; // 소수 둘째 자리 반올림
    }
}
