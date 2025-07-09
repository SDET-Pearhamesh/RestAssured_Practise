package org.practise;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class PractiseClass6 {


    RequestSpecification baseRequestSpec;

    @Test(description = "Common RequestSpecBuilder Methods" , enabled = false)
    public void example(){

        RequestSpecification requestSpec = new RequestSpecBuilder()
                // Base configuration
                .setBaseUri("https://api.example.com")
                .setBasePath("/api/v1")


                // Headers
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")

                // Authentication
                .setAuth(RestAssured.basic("username", "password"))
                // or
                .addHeader("Authorization", "Bearer token123")

                // Query parameters
                .addQueryParam("api_key", "12345")

                // Form parameters
                .addFormParam("username", "testuser")

                // Request body
                .setBody("requestBody")

                // Cookies
                .addCookie("session_id", "abc123")

                // Build the specification
                .build();
    }

    @BeforeClass
    public void setupRequestSpecs(){

         baseRequestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.postman.com")
                .addHeader("x-api-key" , ConfigUtil.getApiKey()).build();

    }

    @Test(description = "RequestSpecBuilder is a more structured and fluent way to create request specifications." , enabled = false)
    public void testStatusCode(){

        Response response = RestAssured.given()
                .spec(baseRequestSpec)
                .get("/workspaces")
                .then().log().all()
                .contentType(ContentType.JSON)     // Validating content type
                .extract().response();

    }

}
