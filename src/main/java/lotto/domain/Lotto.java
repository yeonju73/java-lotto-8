package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.ErrorMessage;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplication(numbers);
        this.numbers = getLottoNumberList(numbers);
    }

    private static List<LottoNumber> getLottoNumberList(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::fromInteger)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE.getMessage());
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBERS.getMessage());
        }
    }

    public List<LottoNumber> getSortedNumbers() {
        return this.numbers.stream()
                .sorted()
                .toList();
    }

    public boolean contains(BonusNumber bonusNumber) {
        return this.numbers.stream()
                .anyMatch(bonusNumber::isSameAs);
    }

    public int countMatchingNumbers(Lotto winningLotto) {
        return (int) this.numbers.stream()
                .filter(winningLotto.numbers::contains)
                .count();

    }
}
