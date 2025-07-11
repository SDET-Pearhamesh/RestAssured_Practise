package org.practise;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PractiseClass10 {

    @Test(description = "We are sending single query parameter using param method" , enabled = false)
    public void single_query_parameter(){

        given().
                baseUri("https://postman-echo.com").
                params("foo1" , "bar1").
                log().all().
        when().
                get().
        then().
                log().all().
                statusCode(200);
    }

    @Test(description = "We are sending multiple query parameter using param method" , enabled = false)
    public void multiple_query_parameter(){

        given().
                baseUri("https://postman-echo.com").
                queryParam("foo1" , "bar1").
                queryParam("foo2" , "bar2").
                log().all().
        when().
                get().
        then().
                log().all().
                statusCode(200);
    }

    @Test(description = "We are sending multiple calues for single parameter using param method" , enabled = false)
    public void multiple_value_parameter(){

        given().
                baseUri("https://postman-echo.com").
                queryParam("foo1" , "bar1 ; bar2; bar3").
                log().all().
        when().
                get().
        then().
                log().all().
                statusCode(200);
    }

    @Test(description = "Path parameters are used to identify a specific resource or resources within a collection." , enabled = false)
    public void path_parameter(){

        given().
                baseUri("https://regres.in").
                pathParam("userID" , "2").
                log().all().
        when().
                get("/api/users/{userID}").
        then().
                log().all().
                statusCode(200);
    }


}
