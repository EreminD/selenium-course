package ru.inno.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@SelectClasspathResource("ru/inno/cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ru.inno.cucumber")
public class RunCucumberTest {
    // mvn clean test -Dtest=RunCucumberTest
}