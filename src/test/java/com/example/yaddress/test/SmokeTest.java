package com.example.yaddress.test;

import com.example.yaddress.testcase.TestCaseData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

/**
 * Smoke tests to verify default request and response
 * </>
 */

@SpringBootTest
class SmokeTest extends AbstractTest {

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(new TestCaseData("TC-01-01 - Smoke Test"))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testJson(TestCaseData testCaseData) {
        testStep.getRequestAndVerifyResponse(testCaseData);
    }
}