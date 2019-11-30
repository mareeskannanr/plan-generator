package com.plan.generator.app.controllers;

import com.plan.generator.app.models.Payload;
import com.plan.generator.app.models.Repayment;
import com.plan.generator.app.services.PlanGeneratorService;
import com.plan.generator.app.utils.AppConstants;
import com.plan.generator.app.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import static com.plan.generator.app.utils.AppConstants.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Plan Generator"}, description = "EndPoints")
public class WebRestController {

    @Autowired
    private PlanGeneratorService planGeneratorService;

    /**
     * Loan Repayment Planner Service expose through this endpoint
     * @param payload
     * @return
     */
    @PostMapping("/generate-plan")
    @ApiOperation(value = CTRL_API_OPR_VALUE, response = Repayment.class, responseContainer = "List", notes = CTRL_API_OPR_NOTES)
    @ApiResponse(code = 200, response = Repayment.class, responseContainer = "List", message = RESPONSE_MESSAGE)
    public ResponseEntity generatePlan(@RequestBody @Valid Payload payload) {
        return ResponseEntity.ok(planGeneratorService.generateRepaymentList(payload));
    }

}
