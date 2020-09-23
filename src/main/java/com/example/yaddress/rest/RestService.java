package com.example.yaddress.rest;

import com.example.yaddress.properties.ConfigProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author yevgen.ulyanenkov
 * @since 22.09.2020
 */
@Service
public class RestService {

    private ConfigProperties configProperties;

    @Autowired
    public RestService(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    private RequestSpecification getRequestSpec(Map<String, String> parameters,
                                               String accept) {
        return new RequestSpecBuilder()
                .setBaseUri(configProperties.getUrl())
                .addParams(parameters)
                .setAccept(accept)
                .build();
    }

    public Response sendGetRequest(Map<String, String> parameters,
                                   String accept) {
        RequestSpecification requestSpec = getRequestSpec(parameters, accept);

        // Execute GET request
        return given()
                .spec(requestSpec)
                .when()
                .get();
    }
}
