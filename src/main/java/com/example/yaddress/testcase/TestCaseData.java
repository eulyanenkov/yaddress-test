package com.example.yaddress.testcase;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

import static com.example.yaddress.testcase.DefaultTestData.ADDRESS_LINE_1;
import static com.example.yaddress.testcase.DefaultTestData.ADDRESS_LINE_2;

/**
 * @author yevgen.ulyanenkov
 * @since 22.09.2020
 */
@Data
@Accessors(chain = true, fluent = true)
@RequiredArgsConstructor
public class TestCaseData {

    @NonNull
    private String name;

    private String addressLine1 = ADDRESS_LINE_1;

    private String addressLine2 = ADDRESS_LINE_2;

    private String userKey;

    private String expectedErrorCode = "0";

    private String expectedErrorMessage = "";

    private String expectedAddressLine1;

    private String expectedAddressLine2;

    public Map<String,String> getParameters(){
        Map<String,String> parameters = new HashMap<>();
        parameters.put("AddressLine1", addressLine1);
        parameters.put("AddressLine2", addressLine2);
        parameters.put("UserKey", userKey);
        return parameters;
    }

    @Override
    public String toString(){
        return name;
    }

}
