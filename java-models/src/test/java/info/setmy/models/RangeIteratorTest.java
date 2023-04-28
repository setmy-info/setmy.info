package info.setmy.models;

import org.junit.jupiter.api.Test;

import static info.setmy.models.RangeIterator.integerRange;
import static org.assertj.core.api.Assertions.assertThat;

class RangeIteratorTest {

    RangeIterator<Integer> rangeIterator;

    @Test
    public void forEach() {
        rangeIterator = integerRange(10);
        final LambdaReturn<Integer> lambdaReturn = new LambdaReturn<>(0);
        rangeIterator.forEach(value -> lambdaReturn.setValue(lambdaReturn.getValue().get() + 1));
        assertThat(lambdaReturn.getValue().get()).isEqualTo(10);
    }

    @Test
    public void range() {
        rangeIterator = integerRange(10);
        int i = 0;
        int sum = 0;
        for (Integer value : rangeIterator) {
            System.out.println("Value: " + value);
            i++;
            sum += value;
        }
        assertThat(i).isEqualTo(10);//1, 2, 3, ...
        assertThat(sum).isEqualTo(45);
    }
}
