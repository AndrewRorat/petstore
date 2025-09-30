package business.common.client;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static core.util.config.EnvironmentConfig.getBaseUrl;
import static core.util.config.EnvironmentConfig.isLoggingEnabled;
import static io.restassured.RestAssured.given;


public abstract class BaseClient {

    protected RequestSpecification getSpec() {
        RequestSpecification spec = given().baseUri(getBaseUrl())
                .contentType("application/json")
                .accept("application/json")
                .filters(new AllureRestAssured());
        if (isLoggingEnabled()) {
            spec.filters(new RequestLoggingFilter(LogDetail.ALL),
                    new ResponseLoggingFilter(LogDetail.ALL));
        }
        return spec;
    }
}
