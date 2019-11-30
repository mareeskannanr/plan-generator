package com.plan.generator.app;

import com.plan.generator.app.utils.AppConstants;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	private static final String PLAN_GENERATOR_URL = "/generate-plan";

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Check all required fields in payload
	 * @throws Exception
	 */
	@Test
	void checkPayloadRequiredValidation() throws Exception {
		MvcResult result = mockMvc.perform(post(PLAN_GENERATOR_URL)
				.content("{}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		String errors = result.getResponse().getContentAsString();
		assertTrue(errors.contains(AppConstants.LOAN_AMOUNT_REQUIRED));
		assertTrue(errors.contains(AppConstants.DURATION_REQUIRED));
		assertTrue(errors.contains(AppConstants.NOMINAL_RATE_REQUIRED));
		assertTrue(errors.contains(AppConstants.START_DATE_REQUIRED));
	}

	/**
	 * Check for minimum values of duration and loanAmount
	 * @throws Exception
	 */
	@Test
	void checkPayloadWithMin() throws Exception {
		String payload = "{\"duration\": 0, \"loanAmount\": 0, \"nominalRate\": \"5.0\", \"startDate\": \"2020-01-01T00:00:01Z\"}";
		MvcResult result = mockMvc.perform(post(PLAN_GENERATOR_URL)
				.content(payload)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
		String errors = result.getResponse().getContentAsString();
		assertTrue(errors.contains(AppConstants.MIN_DURATION));
		assertTrue(errors.contains(AppConstants.MIN_LOAN_AMOUNT));
	}

	@Test
	void checkRepaymentPlanForValidPayload() throws Exception {
		String payload = "{\"duration\": 24, \"loanAmount\": 5000, \"nominalRate\": \"5.0\", \"startDate\": \"2020-01-01T00:00:01Z\"}";
		MvcResult result = mockMvc.perform(post(PLAN_GENERATOR_URL)
				.content(payload)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();

		assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
		JSONArray repayments = new JSONArray(result.getResponse().getContentAsString());
		assertEquals(repayments.length(), 24);
		assertEquals(repayments.getJSONObject(0).getString("initialOutstandingPrincipal"), "5000");
		assertEquals(repayments.getJSONObject(23).getString("remainingOutstandingPrincipal"), "0");
		assertTrue(repayments.getJSONObject(0).getString("date").contains("2020-01-01"));
		assertTrue(repayments.getJSONObject(23).getString("date").contains("2021-12-01"));
	}
}
