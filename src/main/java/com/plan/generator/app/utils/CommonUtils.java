package com.plan.generator.app.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CommonUtils {

    private static final Integer DAYS_IN_MONTH = 30;
    private static final Integer DAYS_IN_YEAR = 360;

    /**
     * This method calculate annuity amount value
     * @param duration
     * @param interest
     * @param loanAmount
     * @return
     */
    public static BigDecimal calculateAnnuityAmount(Integer duration, BigDecimal interest, BigDecimal loanAmount) {
        interest = BigDecimal.valueOf(interest.doubleValue() / (12 * 100));
        BigDecimal annuityAmount = interest.add(BigDecimal.ONE);
        annuityAmount = annuityAmount.pow(-1 * duration, MathContext.DECIMAL128);
        annuityAmount = BigDecimal.ONE.subtract(annuityAmount);
        return loanAmount.multiply(interest).divide(annuityAmount, 2, RoundingMode.HALF_EVEN);
    }

    /**
     * This method calculate interest amount using the below formula
     *  (principal * interest * 12 / 360 * 100)
     * @param principal
     * @param interest
     * @return
     */
    public static BigDecimal calculateInterest(BigDecimal principal, BigDecimal interest) {
        BigDecimal interestAmount = principal.multiply(interest).multiply(BigDecimal.valueOf(DAYS_IN_MONTH));
        interestAmount = interestAmount.divide(BigDecimal.valueOf(DAYS_IN_YEAR), 2, RoundingMode.HALF_EVEN);
        interestAmount = interestAmount.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);
        return interestAmount;
    }

}
