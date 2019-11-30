package com.plan.generator.app.models;

import lombok.Data;

/**
 * Repayment Schedule for each month
 */
@Data
public class Repayment {

    private String borrowerPaymentAmount;

    private String date;

    private String initialOutstandingPrincipal;

    private String interest;

    private String principal;

    private String remainingOutstandingPrincipal;

}
