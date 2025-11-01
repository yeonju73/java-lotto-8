package lotto.view;

import lotto.domain.Lottos;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printIssuedLottos(Lottos lottos) {
        System.out.println("\n" + lottos.getLottoCount() + "개를 구매했습니다.");
//        lottos.getLottos().stream()
//                .map(Lotto::getNumbers) // 각 Lotto에서 숫자 리스트를 가져옴
//                .forEach(System.out::println); // 형식에 맞게 출력
    }


}
