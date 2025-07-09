package org.practise;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PractiseClass8 {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;


    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("x-api-key" , ConfigUtil.getApiKey()).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);

        RestAssured.responseSpecification = responseSpecBuilder.build();


    }

    @Test
    public void validate_Response_Body(){

        String payload = "{\n" +
                "\"workspace\": {\n" +
                "        \"name\": \"Post request Workspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Trying to create workspace using Rest assured\"\n" +
                "}\n" +
                "}";
        given().
                body(payload).
        when().
                post("/workspaces").
        then().assertThat().
        body("workspace.name" , equalTo("Post request Workspace"));


    }

}
