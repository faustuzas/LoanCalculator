package Main;

import Calculators.Calculator;
import Calculators.CalculatorFactory;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller implements Constants {
    @FXML
    private Button calcButton;

    @FXML
    private Slider loanSlider;

    @FXML
    private TextField loanInput;

    @FXML
    private Slider yearsSlider;

    @FXML
    private TextField yearsInput;

    @FXML
    private ToggleGroup typeGroup;

    @FXML
    private RadioButton annuityRadio;

    @FXML
    private RadioButton linearRadio;

    @FXML
    private LineChart<Integer, Double> graph;

    @FXML
    private Label monthlyPay;

    @FXML
    public void initialize() {
        loanSlider.setMin(MIN_LOAN);
        loanSlider.setMax(MAX_LOAN);
        loanInput.setText(String.valueOf(MIN_LOAN));

        yearsSlider.setMin(MIN_YEARS);
        yearsSlider.setMax(MAX_YEARS);
        yearsInput.setText(String.valueOf(MIN_YEARS));

        linearRadio.setUserData(LINEAR_TYPE);
        annuityRadio.setUserData(ANNUITY_TYPE);

        setLoanSliderListener();
        setLoanInputListener();

        setYearsSliderListener();
        setYearsInputListener();

        setCalcButtonListener();
    }

    private void setCalcButtonListener() {
        calcButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Calculator calculator = CalculatorFactory.make(
                    (String) typeGroup.getSelectedToggle().getUserData(),
                    getSum(),
                    getYears()
            );

            monthlyPay.setText(String.valueOf(calculator.getMonthlyPay()));

            graph.getData().clear();
            graph.getData().add(calculator.getPaymentSeries());
        });
    }

    private void setYearsSliderListener() {
        adjustInput(yearsSlider, yearsInput);
    }

    private void setLoanSliderListener() {
        adjustInput(loanSlider, loanInput);
    }

    private void adjustInput(Slider slider, TextField input) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            input.setText(Integer.toString((int)slider.getValue()));
        });
    }

    private void setYearsInputListener() {
        adjustSlider(yearsInput, yearsSlider, MIN_YEARS, MAX_YEARS);
    }

    private void setLoanInputListener() {
        adjustSlider(loanInput, loanSlider, MIN_LOAN, MAX_LOAN);
    }

    private void adjustSlider(TextField input, Slider slider, int min, int max) {
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            filterNumeric(input, newValue);

            int intValue = getValue(input, min, max);

            input.setText(String.valueOf(intValue));
            slider.setValue(intValue);
        });
    }

    private void filterNumeric(TextField textField, String value) {
        if (!value.matches("\\d*")) {
            textField.setText(value.replaceAll("[^\\d]", ""));
        }
    }

    private int getYears() {
        return getValue(yearsInput, MIN_YEARS, MAX_YEARS);
    }

    private int getSum() {
        return getValue(loanInput, MIN_LOAN, MAX_LOAN);
    }

    private int getValue(TextField textField, int min, int max) {
        int intValue;

        try {
            intValue = Integer.parseInt(textField.getText());
        } catch (Exception e) {
            intValue = min;
        }

        if (intValue < min) {
            intValue = min;
        }

        if (intValue > max) {
            intValue = max;
        }

        return intValue;
    }
}
