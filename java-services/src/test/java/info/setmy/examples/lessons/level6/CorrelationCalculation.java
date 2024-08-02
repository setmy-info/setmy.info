package info.setmy.examples.lessons.level6;

import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.UNLIMITED;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class CorrelationCalculation {

    private final List<BigDecimal> x;
    private final List<BigDecimal> y;

    private List<CalculationData> calculationDataList = new ArrayList<>();

    private BigDecimal meanX;
    private BigDecimal meanY;

    private BigDecimal multipliedSum = ZERO;

    private BigDecimal subtractedXSquerSum = ZERO;
    private BigDecimal subtractedYSquerSum = ZERO;

    public CorrelationCalculation(final BigDecimal[] x, final BigDecimal[] y) {
        this(asList(x), asList(y));
    }

    public CorrelationCalculation(final List<BigDecimal> x, final List<BigDecimal> y) {
        this.x = x;
        this.y = y;
        validate();
        calculationDataList = new ArrayList<>(this.x.size());
    }

    public BigDecimal calculate() {
        for (int i = 0; i < x.size(); i++) {
            calculationDataList.add(new CalculationData(x.get(i), y.get(i)));
        }
        BigDecimal sumX = ZERO;
        BigDecimal sumY = ZERO;
        for (CalculationData calculationData : calculationDataList) {
            sumX = sumX.add(calculationData.getX());
            sumY = sumY.add(calculationData.getY());
        }
        meanX = sumX.divide(new BigDecimal(calculationDataList.size()), UNLIMITED);
        meanY = sumY.divide(new BigDecimal(calculationDataList.size()), UNLIMITED);

        calculationDataList.stream().map(calculationData -> {
            calculationData.calculate(meanX, meanY);
            return calculationData;
        }).map(calculationData -> {
            multipliedSum = multipliedSum.add(calculationData.getMultiplied());
            return calculationData;
        }).map(calculationData -> {
            subtractedXSquerSum = subtractedXSquerSum.add(calculationData.getSubtractedXSquer());
            return calculationData;
        }).forEachOrdered(calculationData -> {
            subtractedYSquerSum = subtractedYSquerSum.add(calculationData.getSubtractedYSquer());
        });

        final BigDecimal squersMultipied = subtractedXSquerSum.multiply(subtractedYSquerSum);
        final BigDecimal squersMultipiedSquerRoot = squersMultipied.sqrt(UNLIMITED);

        return multipliedSum.divide(squersMultipiedSquerRoot);
    }

    private void validate() {
        if (x == null || y == null || x.size() != y.size()) {
            throw new RuntimeException("Arrays are in different size");
        }
    }

    private static class CalculationData {

        private final BigDecimal x;
        private final BigDecimal y;

        private BigDecimal subtractedX;
        private BigDecimal subtractedY;

        private BigDecimal multiplied;

        private BigDecimal subtractedXSquer;
        private BigDecimal subtractedYSquer;

        private CalculationData(final BigDecimal x, final BigDecimal y) {
            this.x = x;
            this.y = y;
        }

        private BigDecimal getX() {
            return x;
        }

        private BigDecimal getY() {
            return y;
        }

        private BigDecimal getSubtractedX() {
            return subtractedX;
        }

        private BigDecimal getSubtractedY() {
            return subtractedY;
        }

        private BigDecimal getMultiplied() {
            return multiplied;
        }

        private void setSubtractedX(final BigDecimal subtractedX) {
            this.subtractedX = subtractedX;
        }

        private void setSubtractedY(final BigDecimal subtractedY) {
            this.subtractedY = subtractedY;
        }

        private void setMultiplied(final BigDecimal multiplied) {
            this.multiplied = multiplied;
        }

        private BigDecimal getSubtractedXSquer() {
            return subtractedXSquer;
        }

        private void setSubtractedXSquer(BigDecimal subtractedXSquer) {
            this.subtractedXSquer = subtractedXSquer;
        }

        private BigDecimal getSubtractedYSquer() {
            return subtractedYSquer;
        }

        private void setSubtractedYSquer(BigDecimal subtractedYSquer) {
            this.subtractedYSquer = subtractedYSquer;
        }

        private void calculate(final BigDecimal meanX, final BigDecimal meanY) {
            subtractedX = x.subtract(meanX);
            subtractedY = y.subtract(meanY);
            multiplied = subtractedX.multiply(subtractedY);
            subtractedXSquer = subtractedX.multiply(subtractedX);
            subtractedYSquer = subtractedY.multiply(subtractedY);
        }
    }
}
