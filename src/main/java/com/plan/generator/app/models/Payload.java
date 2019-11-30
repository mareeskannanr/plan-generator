package com.plan.generator.app.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.plan.generator.app.utils.AppConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.plan.generator.app.utils.AppConstants.*;

/**
 * Payload contains details about loan includes amount, duration, repayment start date and interest
 **/
@Data
@ApiModel(description = PAYLOAD_MODEL_DESCRIPTION)
public class Payload {

    //total principal amount
    @NotNull(message = LOAN_AMOUNT_REQUIRED)
    @Min(value = 1, message = MIN_LOAN_AMOUNT)
    @ApiModelProperty(notes = LOAN_AMOUNT_NOTE, required = true, example = LOAN_AMOUNT_EXAMPLE)
    private BigDecimal loanAmount;

    //number of instalments in months
    @NotNull(message = DURATION_REQUIRED)
    @Min(value = 1, message = MIN_DURATION)
    @ApiModelProperty(notes = DURATION_NOTE, required = true, example = DURATION_EXAMPLE)
    private Integer duration;

    //nominal interest rate
    @NotNull(message = NOMINAL_RATE_REQUIRED)
    @ApiModelProperty(notes = NOMINAL_RATE_NOTE, required = true, example = NOMINAL_RATE_EXAMPLE)
    private BigDecimal nominalRate;

    //Date of Disbursement/Payout
    @NotNull(message = START_DATE_REQUIRED)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @ApiModelProperty(notes = START_DATE_NOTE, required = true, example = START_DATE_EXAMPLE)
    private LocalDateTime startDate;

}
