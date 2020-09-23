package com.example.yaddress.rest;

import com.example.yaddress.properties.ConfigProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yevgen.ulyanenkov
 * @since 22.09.2020
 */
@Service
public class RestService {

    private ConfigProperties configProperties;

    @Autowired
    public RestService(ConfigProperties configProperties){
        this.configProperties = configProperties;
    }

    public RequestSpecification getRequestSpec(Map<String, String> parameters,
                                               String accept) {
        return new RequestSpecBuilder()
                .setBaseUri(configProperties.getUrl())
                .addParams(parameters)
                .setAccept(accept)
                .build();
    }
}
