package info.setmy.examples.lessons.level6;

import info.setmy.examples.junit5.RandomParametersExtension;
import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import java.math.MathContext;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import static java.util.stream.Collectors.toUnmodifiableList;
import java.util.stream.DoubleStream;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * https://www.mathsisfun.com/data/correlation.html
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@DisplayName("JUnit 5 collection examples")
@ExtendWith(RandomParametersExtension.class)
public class Lesson1MathTest {

    @Test
    public void correllationCalculation() {
        final double[] x = {
            14.2d,
            16.4d,
            11.9d,
            15.2d,
            18.5d,
            22.1d,
            19.4d,
            25.1d,
            23.4d,
            18.1d,
            22.6d,
            17.2d,
            18.7d
        };
        final double[] y = {
            215.0d,
            325.0d,
            185.0d,
            332.0d,
            406.0d,
            522.0d,
            412.0d,
            614.0d,
            544.0d,
            421.0d,
            445.0d,
            408.0d,
            402.0d
        };

        final double meanX = new Statistics(new Stream(x)).mean();
        final double meanY = new Statistics(new Stream(y)).mean();

        final List<Double> subtractedX = subtractMean(x, meanX);
        final List<Double> subtractedY = subtractMean(y, meanY);

        final List<Double> multiplied = multiply(subtractedX, subtractedY);

        final List<Double> subtractedXSquer = squere(subtractedX);
        final List<Double> subtractedYSquer = squere(subtractedY);

        final Double multipliedSum = multiplied.stream().reduce(0.0d, Double::sum);
        final Double subtractedXSquerSum = subtractedXSquer.stream().reduce(0.0d, Double::sum);
        final Double subtractedYSquerSum = subtractedYSquer.stream().reduce(0.0d, Double::sum);

        final double result = multipliedSum / sqrt(subtractedXSquerSum * subtractedYSquerSum);

        //final BigDecimal resultInBD = new CorrelationCalculation(toBigDecimal(x), toBigDecimal(y)).calculate();
        assertThat(result).isEqualTo(0.9575028943810974d);
        //assertThat(resultInBD).isEqualTo(ZERO);
    }

    private List<Double> subtractMean(final double[] set, final double mean) {
        final List<Double> result = new ArrayList<>(set.length);
        stream(set).forEach(element -> {
            result.add(element - mean);
        });
        return result;
    }

    private List<Double> multiply(final List<Double> subtractedX, final List<Double> subtractedY) {
        final List<Double> result = new ArrayList<>(subtractedX.size());
        if (subtractedX.size() == subtractedY.size()) {
            final Double[] setX = subtractedX.toArray(new Double[subtractedX.size()]);
            final Double[] setY = subtractedY.toArray(new Double[subtractedY.size()]);
            for (int i = 0; i < setX.length; i++) {
                result.add(setX[i] * setY[i]);
            }
            return result;
        }
        throw new RuntimeException("Different array sisez");
    }

    private List<Double> squere(List<Double> values) {
        return values.stream()
                .map(element -> {
                    return element * element;
                })
                .collect(toUnmodifiableList());
    }

    private static class Stream {

        private final DoubleStream arrayStream;

        public Stream(final double[] array) {
            this.arrayStream = stream(array);
        }

        public DoubleStream getArrayStream() {
            return arrayStream;
        }
    }

    private static class Statistics {

        private final DoubleSummaryStatistics statistics;

        public Statistics(final Stream stream) {
            this.statistics = stream.getArrayStream().summaryStatistics();
        }

        public DoubleSummaryStatistics getStatistics() {
            return statistics;
        }

        public double mean() {
            return this.statistics.getAverage();
        }
    }

    private List<BigDecimal> toBigDecimal(final double[] array) {
        final List<BigDecimal> result = new ArrayList<>(array.length);
        for (double element : array) {
            result.add(new BigDecimal(element, MathContext.DECIMAL64));
        }
        return result;
    }
}
