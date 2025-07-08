package org.practise;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PractiseClass3 {

    @Test(description = "We can log required thing in both request and response" , enabled = false)
    public void few_handy_assertions_Byhamecrest() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
                log().all().        // To log all everything in the request
                log().headers().    // To log all headers
                log().parameters(). // to log all parameters
        when().
                get("/workspaces").
        then().
                log().all().       // To log everything in response
                log().body().      // To log body
                log().cookies().   // To log cookies in response
                log().headers().   // to log headers in response
                assertThat().
                statusCode(200);
    }

    @Test(description = "Using ifError method " , enabled = false)
    public void logOnly_ifErrors() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
        when().
                get("/workspaces").
        then().
                log().ifError().     // Only if we have error Example - Wrong authenticate, Syntax error
                assertThat().
                statusCode(200);
    }

    @Test(description = "Using ifValidationFails method " , enabled = false )
    public void logOnly_if_validationFails() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
                log().ifValidationFails().   // To get request if validation fails
        //        config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()));
        when().
                get("/workspaces").
        then().
                log().ifValidationFails().    // Only if added valiadation here status code fails
                assertThat().
                statusCode(200);

        System.out.println("Instead of writting 2 times log().ifValidationFails()");
        System.out.println("Use config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())) ");

    }

    @Test(description = "Using config blacklistHeader() method" , enabled = false)
    public void hiding_sensitive_from_logging_headers() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
                config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key"))).
                log().all().
         when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(403);

        System.out.println("If we have multiple attributes to hide, We can send Collection like Set of strings");

    }

}
