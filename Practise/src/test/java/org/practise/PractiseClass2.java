package org.practise;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import static io.restassured.RestAssured.given;

public class PractiseClass2 {


    @Test(description = "This test is to explore simple assertThat methods from hamcrest" , enabled = false)
    public void hamcrest_asserts_on_extracted_response() {

        String firstWorkspaceName =
                given().
                        baseUri("https://api.postman.com").
                        header("x-api-key", ConfigUtil.getApiKey()).
                when().
                        get("/workspaces").
                then().
                        log().all().
                        assertThat().
                        statusCode(200).
                        extract().response().path("workspaces[0].name");

        assertThat(firstWorkspaceName , equalTo("My Workspace")); // import static org.hamcrest.MatcherAssert.assertThat;

        Assert.assertEquals("My Workspace" , firstWorkspaceName); // TestNG Assertion

    }

    @Test(description = "Contains validates each value and order. It has to have all elements and to be in right order" , enabled = false)
    public void hamcrest_asserts_contains() {

                given().
                        baseUri("https://api.postman.com").
                        header("x-api-key", ConfigUtil.getApiKey()).
                when().
                        get("/workspaces").
                then().
                        log().all().
                        assertThat().
                        statusCode(200).
                        body("workspaces.name" , contains("My Workspace" , "Team Workspace" , "User_Managemnt_team" , "QA")).
                        body("workspaces.name" , containsInAnyOrder( "QA" , "My Workspace" , "Team Workspace" , "User_Managemnt_team" ));
    }

    @Test(description = "Checking response size, is empty or not, verifying has particular element" , enabled = false)
    public void few_handy_assertions_Byhamecrest() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name" , containsInAnyOrder(  "My Workspace" , "QA" , "Team Workspace" , "User_Managemnt_team"  ) ,
                        "workspaces.name" , is(not(empty())) ,
                        "workspaces.name" , hasSize(4) ,
                        "workspaces.name" , is(not(emptyArray())) ,
                        "workspaces.name" , hasItem("QA"));
       //               "workspaces.name" , everyItem(startsWith("My"))) ;

    }

    @Test(description = "Considering repsonse in Key and value pair" , enabled = false)
    public void few_more_handy_assertions_Byhamecrest() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces[0]" , hasKey("name") ,
                        "workspaces[0]" ,hasValue("My Workspace") ,
                                 "workspaces[0]" , hasEntry("id" , "a1befd3a-f6ca-4256-848f-23fdcf00b91c"));
          //                     "workspaces[0]" , equalTo(Collections.EMPTY_MAP));
    }

    @Test(description = "Works on string. anyOf - At least one of these & allOf All of these" )
    public void allof_anyof_matchers_byHamcrest() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces[0].name" , allOf(startsWith("My") , containsString("Workspace")) ,
               "workspaces[0].name" , anyOf(startsWith("jdfhkjf") , containsString("Workspace")));


    }












}
