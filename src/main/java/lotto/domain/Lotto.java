package lotto.domain;

import static lotto.view.Messages.CLOSE_BRACKET;
import static lotto.view.Messages.COMMA_WITH_SPACE;
import static lotto.view.Messages.OPEN_BRACKET;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private final List<Integer> numbers;
    public static final int LOWER_BOUND_OF_NUMBER = 1;
    public static final int UPPER_BOUND_OF_NUMBER = 45;
    public static final int VALID_SIZE_OF_NUMBERS = 6;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private static void validate(List<Integer> numbers) {
        validateSize(numbers.size());
        validateNoDuplicates(numbers);
        validateRange(numbers);
    }

    private static void validateNoDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (Integer number : numbers) {
            if (!uniqueNumbers.add(number)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static void validateSize(final int size) {
        if (size != VALID_SIZE_OF_NUMBERS) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateRange(List<Integer> numbers) {
        int minNumber = Collections.min(numbers);
        int maxNumber = Collections.max(numbers);

        if (minNumber < LOWER_BOUND_OF_NUMBER || maxNumber > UPPER_BOUND_OF_NUMBER) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        String result = String.join(COMMA_WITH_SPACE, converToStringList());
        return OPEN_BRACKET + result + CLOSE_BRACKET;
    }

    public List<String> converToStringList() {
        return numbers.stream()
                .sorted()
                .map(number -> Integer.toString(number))
                .collect(Collectors.toList());
    }
}
