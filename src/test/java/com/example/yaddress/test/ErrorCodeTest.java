package com.example.yaddress.test;

import com.example.yaddress.testcase.TestCaseData;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.example.yaddress.testcase.DefaultTestData.ADDRESS_LINE_1;
import static com.example.yaddress.testcase.DefaultTestData.ADDRESS_LINE_2;
import static org.apache.commons.lang3.StringUtils.upperCase;

/**
 * Negative tests to verify incorrect combinations in Address request fields
 * </>
 * Verified:
 * ErrorCodes and ErrorMessages
 * </>
 * Additionally verified AddressLines in response:
 * If the address failed to validate, Address lines in request are a copies of the input lines
 * </>
 */

@SpringBootTest
class ErrorCodeTest extends AbstractTest {

    private static Stream<Arguments> testData() {
        return Stream.of(

                Arguments.of(
                        new TestCaseData("TC-02-02 - Error code = 2 (empty AddressLine2)")
                                .addressLine2("")
                                .expectedErrorCode("2")
                                .expectedErrorMessage("Invalid address: invalid City-State-Zip line")
                ),

                Arguments.of(
                        new TestCaseData("TC-02-03 - Error code = 3 (not found street)")
                                .addressLine1("506 Leopoldstrasse Unit 1")
                                .expectedErrorCode("3")
                                .expectedErrorMessage("Street not found in city/state")
                ),

                Arguments.of(
                        new TestCaseData("TC-02-04 - Error code = 4 (not found city)")
                                .addressLine2("Asbury Prk NY")
                                .expectedErrorCode("4")
                                .expectedErrorMessage("City not found in state")
                ),

                Arguments.of(
                        new TestCaseData("TC-02-08 - Error code = 8 (not found house number)")
                                .addressLine1("777777 Fourth Avenue Unit 1")
                                .expectedErrorCode("8")
                                .expectedErrorMessage("No such house number in the street")
                ),

                Arguments.of(
                        new TestCaseData("TC-02-11 - Error code = 2 (invalid AddressLine1)")
                                .addressLine1("Fourth Avenue")
                                .expectedErrorCode("2")
                                .expectedErrorMessage("Invalid address: invalid street address line")
                                .expectedAddressLine1(upperCase("Fourth Avenue"))
                                .expectedAddressLine2(upperCase(ADDRESS_LINE_2))
                ),

                Arguments.of(
                        new TestCaseData("TC-02-12 - Error code = 2 (invalid AddressLine2)")
                                .addressLine2("!!!!!!!!!")
                                .expectedErrorCode("2")
                                .expectedErrorMessage("Invalid address: invalid City-State-Zip line")
                                .expectedAddressLine1(upperCase(ADDRESS_LINE_1))
                                .expectedAddressLine2("!!!!!!!!!")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testJson(TestCaseData testCaseData) {
        testStep.getRequestAndVerifyResponse(testCaseData);
    }
}