package com.example.yaddress.test;

import com.example.yaddress.teststep.ITestStep;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yevgen.ulyanenkov
 * @since 22.09.2020
 */
public abstract class AbstractTest {

    @Autowired
    protected ITestStep testStep;

}