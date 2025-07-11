package org.practise;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;

public class PractiseClass12 {


    @Test(description = "We can log request and response specification using Filter Interface" , enabled = false)
    public void loggingFilter(){

        given().
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter()).
                filter( new ResponseLoggingFilter()).
         //       log().all().      // We use to use log feature
        when().
                get("/get").
        then().
         //      log().all().      //// We use to use log feature
                statusCode(200);
    }


    @Test(description = "We can log specific content of request and response specification using Filter" , enabled = false)
    public void speficic_logging_Using_Filter(){

        given().
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter(LogDetail.BODY)).
                filter( new ResponseLoggingFilter(LogDetail.STATUS)).
                //       log().all().      // We use to use log feature
                        when().
                get("/get").
                then().
                //      log().all().      //// We use to use log feature
                        statusCode(200);
    }

    @Test(description = "Using Filters we can store logs in seperate file" , enabled = false)
    public void logging_Using_Filter_in_file() throws FileNotFoundException {

        PrintStream fileOutPutStream = new PrintStream((new File("mylogs1.log")));
        given().
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter(LogDetail.BODY , fileOutPutStream)).   // pass instance ref here to print in file
                filter( new ResponseLoggingFilter(LogDetail.STATUS , fileOutPutStream)). // // pass instance ref here to print in file
                //       log().all().      // We use to use log feature
                        when().
                get("/get").
                then().
                //      log().all().      //// We use to use log feature
                        statusCode(200);
    }

    @Test(description = "Modifying the above code and using requesr and response specification" , enabled = false)
    public void logging_using_filter() throws FileNotFoundException {

        // This should be in before class

        PrintStream fileOutPutStream = new PrintStream((new File("mylogs2.log")));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                addFilter(new RequestLoggingFilter(fileOutPutStream)).
                addFilter(new ResponseLoggingFilter(fileOutPutStream));

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder.build();

        // Add this in the test

        given().
                spec(requestSpecification).
                baseUri("https://postman-echo.com").
        when().
                get("/get").
        then().
                spec(responseSpecification).
                assertThat().
                statusCode(200);
    }


}
