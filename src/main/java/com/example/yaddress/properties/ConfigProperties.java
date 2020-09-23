package com.example.yaddress.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yevgen.ulyanenkov
 * @since 22.09.2020
 */
@Getter
@Configuration
public class ConfigProperties {

    @Value("${yaddress.url}")
    private String url;

}
