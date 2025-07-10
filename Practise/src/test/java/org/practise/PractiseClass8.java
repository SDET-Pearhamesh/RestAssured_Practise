package org.practise;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class PractiseClass8 {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    String id;


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

    @Test(description = "This method is BDD style POST request" , enabled = false)
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
        body("workspace.name" , equalTo("Post request Workspace") ,
        "workspace.id" , matchesPattern("^[a-z0-9-]{36}$"));

        System.out.println(id);


    }

    @Test(description = "In this test we are trying with NONBDD method" , enabled = false )
    public void validate_post_request_non_bdd_style(){

        // We are using default responseSpecBuilder so

        String payload = "{\n" +
                "\"workspace\": {\n" +
                "        \"name\": \"Post request Workspace 2\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Trying to create workspace 2 using Rest assured\"\n" +
                "}\n" +
                "}";


        Response response = with().
                body(payload).
                post("/workspaces");

        assertThat(response.path("workspace.name") , equalTo("Post request Workspace 2"));
        assertThat(response.path("workspace.id") , matchesPattern("^[a-z0-9-]{36}$"));

         id = (response.path("workspace.id"));
         this.id = id;

         System.out.println(id);

    }

    @Test(dependsOnMethods = "validate_post_request_non_bdd_style" , enabled = false)
    public void put_method(){

      String workspaceId = this.id;

        String payload = "{\n" +
                "\"workspace\": {\n" +
                "        \"name\": \"Put request Workspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Trying to change workspace 2 name using Rest assured\"\n" +
                "}\n" +
                "}";

        given().
                body(payload).
                pathParams("workspaceID" , workspaceId).
        when().
                put("/workspaces/{workspaceID}").
        then().
                log().all().
                assertThat().
                body("workspace.name" , equalTo("Put request Workspace") ,
                        "workspace.id" , matchesPattern("^[a-z0-9-]{36}$"));

        System.out.println(id);

    }

    @Test(dependsOnMethods = "put_method" , enabled = false)
    public void delete_method(){

        String workspaceId = this.id;

        given().
                pathParams("workspaceID" , workspaceId).
                when().
                delete("/workspaces/{workspaceID}").
                then().
                log().all().
                assertThat().
                statusCode(200);

    }





}
