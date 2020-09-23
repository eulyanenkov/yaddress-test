package com.example.yaddress.teststep;

import com.example.yaddress.testcase.TestCaseData;
import org.apache.commons.lang3.StringUtils;

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
    default void getRequestAndVerifyResponse(TestCaseData testCaseData){
        if ("XML".equals(StringUtils.upperCase(System.getProperty("contentType")))) {
            // contentType = XML
            getRequestAndVerifyXmlResponse(testCaseData);
        } else {
            // else default = JSON
            getRequestAndVerifyJsonResponse(testCaseData);
        }
    }


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
