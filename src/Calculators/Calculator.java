package Calculators;

import javafx.scene.chart.XYChart;

import java.text.DecimalFormat;

public abstract class Calculator {
    final double YEARLY_INTEREST = 0.06;

    int sum, years;

    Calculator(int sum, int years) {
        this.sum = sum;
        this.years = years;
    }

    protected abstract double monthlyPay();

    public abstract XYChart.Series<Integer, Double> getPaymentSeries();

    public double getMonthlyPay() {
        return Double.parseDouble(new DecimalFormat("##.##").format(monthlyPay()));
    }
}
