package com.example.yaddress.test;

import com.example.yaddress.testcase.TestCaseData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

/**
 * Positive tests to verify possible combinations in Address request fields
 * </>
 */

@SpringBootTest
class AddressParameterTest extends AbstractTest {

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        new TestCaseData("TC-03-01 - AddressLine1 is empty")
                                .addressLine1("")
                ),

                Arguments.of(
                        new TestCaseData("TC-03-02 - AddressLine2 contains only City")
                                .addressLine2("ASBURY PARK")
                ),

                Arguments.of(
                        new TestCaseData("TC-03-03 - AddressLine2 contains only State")
                                .addressLine2("NJ")
                ),

                Arguments.of(
                        new TestCaseData("TC-03-04 - AddressLine2 contains only ZIP")
                                .addressLine2("07712")
                ),

                Arguments.of(
                        new TestCaseData("TC-03-05 - AddressLine2 contains only ZIP + ZIP4")
                                .addressLine2("07712-6086")
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testJson(TestCaseData testCaseData) {
        testStep.getRequestAndVerifyJsonResponse(testCaseData);
    }
}