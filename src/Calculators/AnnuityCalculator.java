package Calculators;

import javafx.scene.chart.XYChart;

import java.util.stream.IntStream;

import static java.lang.Math.pow;

public class AnnuityCalculator extends Calculator {

    AnnuityCalculator(int sum, int years) {
        super(sum, years);
    }

    @Override
    public double monthlyPay() {
        return monthlyPay(sum);
    }

    private double monthlyPay(double remainingValue) {
        double monthlyInterest = YEARLY_INTEREST / 12;

        return (monthlyInterest * remainingValue) / (1 - pow(1 + monthlyInterest, -years * 12));
    }

    @Override
    public XYChart.Series<Integer, Double> getPaymentSeries() {
        XYChart.Series<Integer, Double> series = new XYChart.Series<>();

        double remaining = sum;
        for (int i : IntStream.range(1, years * 12).toArray()) {
            double payment = monthlyPay(remaining);
            remaining -= payment;
            series.getData().add(new XYChart.Data<>(i, payment));
        }

        return series;
    }
}
