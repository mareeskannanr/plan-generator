package com.plan.generator.app.utils;

public class AppConstants {

    public static final String LOAN_AMOUNT_REQUIRED = "loanAmount is required";
    public static final String DURATION_REQUIRED = "duration is required";
    public static final String NOMINAL_RATE_REQUIRED = "nominalRate is required";
    public static final String START_DATE_REQUIRED = "startDate is required";
    public static final String MIN_DURATION = "minimum duration value is 1";
    public static final String MIN_LOAN_AMOUNT = "minimum loan amount value is 1";

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss'Z'";

    public static final String CTRL_PACKAGE = "com.plan.generator.app.controllers";
    public static final String TITLE = "Plan Generator Tool";
    public static final String DESCRIPTION = "In order to inform borrowers about the final repayment schedule, we need to have pre-calculated\n" +
            "repayment plans throughout the lifetime of a loan.";
    public static final String VERSION = "1.0.0";
    public static final String RESPONSE_MESSAGE = "Loan Repayment Plan generated successfully!";
    public static final String PAYLOAD_MODEL_DESCRIPTION = "This model represents the payload to be given to plan generator";

    //Payload Swagger Values
    public static final String LOAN_AMOUNT_NOTE = "Total principal amount";
    public static final String LOAN_AMOUNT_EXAMPLE = "5000";
    public static final String DURATION_NOTE = "Number of instalments in months";
    public static final String DURATION_EXAMPLE = "24";
    public static final String NOMINAL_RATE_NOTE = "Nominal Interest Rate";
    public static final String NOMINAL_RATE_EXAMPLE = "5.0";
    public static final String START_DATE_NOTE = "Date of Disbursement/Payout";
    public static final String START_DATE_EXAMPLE = "2018-01-01T00:00:01Z";

    //Controller Swagger Values
    public static final String CTRL_API_OPR_NOTES = "Returns list of repayment plan throughout the lifetime of a loan";
    public static final String CTRL_API_OPR_VALUE = "API to calculate a repayment plan for an annuity loan";

}
