package org.practise;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PractiseClass7 {


    RequestSpecification requestSpecification ;

    ResponseSpecification responseSpecification;

    @BeforeClass()
    public  void setupBothSpecification(){

        requestSpecification = new RequestSpecBuilder().setBaseUri("https://api.postman.com").addHeader("x-api-key" , ConfigUtil.getApiKey()).build();

        responseSpecification = RestAssured.expect().statusCode(200).contentType(ContentType.JSON);
    }

    @Test(description = "We are validating specs added to responseSpecification" , enabled = false )
    public void validateResponseSpec(){

        requestSpecification.get("/workspaces").then().spec(responseSpecification);

    }

    @Test(enabled = false)
    public void responseSpecBuilderMethod(){

        requestSpecification = new RequestSpecBuilder().setBaseUri("https://api.postman.com").addHeader("x-api-key" , ConfigUtil.getApiKey()).build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }


}
