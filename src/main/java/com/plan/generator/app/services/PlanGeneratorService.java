package com.plan.generator.app.services;

import com.plan.generator.app.models.Payload;
import com.plan.generator.app.models.Repayment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.plan.generator.app.utils.AppConstants.DATE_FORMAT;
import static com.plan.generator.app.utils.CommonUtils.*;

@Service
public class PlanGeneratorService {

    public List<Repayment> generateRepaymentList(Payload payload) {
        List<Repayment> repayments = new ArrayList<>();
        BigDecimal annuity = calculateAnnuityAmount(payload.getDuration(), payload.getNominalRate(), payload.getLoanAmount());

        BigDecimal loanAmount = payload.getLoanAmount();
        BigDecimal interestRate = payload.getNominalRate();
        LocalDateTime date = payload.getStartDate();
        BigDecimal interestAmount;
        BigDecimal principal;
        Repayment repayment;

        for (int i = 0; i < payload.getDuration(); i++) {
            repayment = new Repayment();
            repayment.setInitialOutstandingPrincipal(loanAmount.toString());
            repayment.setDate(date.plusMonths(i).format(DateTimeFormatter.ofPattern(DATE_FORMAT)));

            interestAmount = calculateInterest(interestRate, loanAmount);
            principal = annuity.subtract(interestAmount.compareTo(loanAmount) == 1 ? loanAmount : interestAmount);
            annuity = principal.add(interestAmount);
            loanAmount = loanAmount.subtract(principal);

            //for last instalment add pending amount to principal and set remaining amount to zero
            if(i == payload.getDuration() - 1 && loanAmount.compareTo(BigDecimal.ZERO) != 0) {
                annuity = annuity.add(loanAmount);
                principal = principal.add(loanAmount);
                loanAmount = BigDecimal.ZERO;
            }

            repayment.setRemainingOutstandingPrincipal(loanAmount.toString());
            repayment.setBorrowerPaymentAmount(annuity.toString());
            repayment.setPrincipal(principal.toString());
            repayment.setInterest(interestAmount.toString());
            repayments.add(repayment);
        }

        return repayments;
    }

}
