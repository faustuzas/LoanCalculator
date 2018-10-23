package Calculators;

import Main.Constants;

public class CalculatorFactory implements Constants {
    enum TYPE {
        ANNUITY,
        LINEAR
    }
    public static Calculator make(TYPE type, int sum, int years) {
        switch (type) {
            case ANNUITY:
                return new AnnuityCalculator(sum, years);
            case LINEAR:
                return new LinearCalculator(sum, years);
        }

        return null;
    }

    public static Calculator make(String type, int sum, int years) {
       switch (type) {
           case ANNUITY_TYPE:
               return make(TYPE.ANNUITY, sum, years);
           default:
               return make(TYPE.LINEAR, sum, years);
       }
    }
}
