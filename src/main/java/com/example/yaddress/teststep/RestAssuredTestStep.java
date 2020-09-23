package com.example.yaddress.teststep;

import com.example.yaddress.rest.RestService;
import com.example.yaddress.testcase.TestCaseData;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestAssuredTestStep implements ITestStep {

    private RestService restService;

    @Autowired
    public RestAssuredTestStep(RestService restService) {
        this.restService = restService;
    }

    @Override
    public void getRequestAndVerifyJsonResponse(TestCaseData testCaseData) {

        // Execute GET request
        Response response = restService.sendGetRequest(testCaseData.getParameters(), "application/json");

        // Verify response: ErrorCode and ErrorMessage
        response.then()
                .log().all()
                .and().assertThat()
                .body("ErrorCode", Matchers.equalTo(Integer.parseInt(testCaseData.expectedErrorCode()))).and()
                .body("ErrorMessage", Matchers.equalTo(testCaseData.expectedErrorMessage()));

        // Verify response: other fields
        if (testCaseData.expectedAddressLine1() != null) {
            response.then().and()
                    .body("AddressLine1", Matchers.equalTo(testCaseData.expectedAddressLine1()));
        }

        if (testCaseData.expectedAddressLine2() != null) {
            response.then().and()
                    .body("AddressLine2", Matchers.equalTo(testCaseData.expectedAddressLine2()));
        }
    }

    @Override
    public void getRequestAndVerifyXmlResponse(TestCaseData testCaseData) {

        // Execute GET request
        Response response = restService.sendGetRequest(testCaseData.getParameters(), "application/xml");

        // Verify response: ErrorCode and ErrorMessage
        response.then().log().all()
                .and().assertThat()
                .body("Address.ErrorCode", Matchers.equalTo(testCaseData.expectedErrorCode())).and()
                .body("Address.ErrorMessage", Matchers.equalTo(testCaseData.expectedErrorMessage()));

        // Verify response: other fields
        if (testCaseData.expectedAddressLine1() != null) {
            response.then().and()
                    .body("Address.AddressLine1", Matchers.equalTo(testCaseData.expectedAddressLine1()));
        }

        if (testCaseData.expectedAddressLine2() != null) {
            response.then().and()
                    .body("Address.AddressLine2", Matchers.equalTo(testCaseData.expectedAddressLine2()));
        }
    }
}
