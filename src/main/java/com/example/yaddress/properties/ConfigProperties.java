package com.example.yaddress.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yevgen.ulyanenkov
 * @since 22.09.2020
 */
@Getter
@Component
public class ConfigProperties {

    @Value("${yaddress.url}")
    private String url;

}
