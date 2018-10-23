package Calculators;

import javafx.scene.chart.XYChart;

import java.util.stream.IntStream;

public class LinearCalculator extends Calculator {

    LinearCalculator(int sum, int years) {
        super(sum, years);
    }

    @Override
    public double monthlyPay() {
        double totalInterest = YEARLY_INTEREST * years;

        double totalSum = sum + sum * totalInterest;

        return totalSum  / (12 * years);
    }

    @Override
    public XYChart.Series<Integer, Double> getPaymentSeries() {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();

        double monthlyPayment = getMonthlyPay();

        IntStream.range(0, 12 * years).forEach(i ->
                series.getData().add(new XYChart.Data<>(i, monthlyPayment)));

        return series;
    }
}
