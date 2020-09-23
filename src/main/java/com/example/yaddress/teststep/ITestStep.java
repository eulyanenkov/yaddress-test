package com.example.yaddress.teststep;

import com.example.yaddress.testcase.TestCaseData;

/**
 * @author yevgen.ulyanenkov
 * @since 22.09.2020
 */
public interface ITestStep {

    /**
     * Send GET request and verify response
     * contentType of response is defined by system variable "contentType"
     * </>
     * @param testCaseData Instance of @TestCaseData
     */
    void getRequestAndVerifyResponse(TestCaseData testCaseData);


    /**
     * Send GET request and verify JSON response
     * contentType of response = JSON
     * </>
     * @param testCaseData Instance of @TestCaseData
     */
    void getRequestAndVerifyJsonResponse(TestCaseData testCaseData);

    /**
     * Send GET request and verify XML response
     * contentType of response = XML
     * </>
     * @param testCaseData Instance of @TestCaseData
     */
    void getRequestAndVerifyXmlResponse(TestCaseData testCaseData);
}
