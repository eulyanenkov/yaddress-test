package com.example.yaddress.teststep;

import com.example.yaddress.rest.RestService;
import com.example.yaddress.testcase.TestCaseData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class RestAssuredTestStep implements ITestStep {

    private RestService restService;

    @Autowired
    public RestAssuredTestStep(RestService restService) {
        this.restService = restService;
    }

    @Override
    public void getRequestAndVerifyResponse(TestCaseData testCaseData) {
        if ("XML".equals(StringUtils.upperCase(System.getProperty("contentType")))) {
            // contentType = XML
            getRequestAndVerifyXmlResponse(testCaseData);
        } else {
            // else default = JSON
            getRequestAndVerifyJsonResponse(testCaseData);
        }
    }

    @Override
    public void getRequestAndVerifyJsonResponse(TestCaseData testCaseData) {
        RequestSpecification requestSpec =
                restService.getRequestSpec(testCaseData.getParameters(), "application/json");

        // Execute GET request
        Response response = given()
                .spec(requestSpec)
                .log().all()
                .when()
                .get();

        // Verify response: ErrorCode and ErrorMessage
        response.then()
                .log().all()
                .and().assertThat()
                .body("ErrorCode", Matchers.equalTo(Integer.parseInt(testCaseData.expectedErrorCode()))).and()
                .body("ErrorMessage", Matchers.equalTo(testCaseData.expectedErrorMessage()));

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
        RequestSpecification requestSpec =
                restService.getRequestSpec(testCaseData.getParameters(), "application/xml");

        // Execute GET request
        given()
                .spec(requestSpec)
                .when()
                .get().then().log().all()
                .and().assertThat()
                .body("Address.ErrorCode", Matchers.equalTo(testCaseData.expectedErrorCode())).and()
                .body("Address.ErrorMessage", Matchers.equalTo(testCaseData.expectedErrorMessage()));

//        if (testCaseData.expectedAddressLine1() != null) {
//            response.then().and()
//                    .body("Address.AddressLine1", Matchers.equalTo(testCaseData.expectedAddressLine1()));
//        }
//
//        if (testCaseData.expectedAddressLine2() != null) {
//            response.then().and()
//                    .body("Address.AddressLine2", Matchers.equalTo(testCaseData.expectedAddressLine2()));
//        }
    }
}
