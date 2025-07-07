package org.practise;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PractiseClass1 {

    // Given when then are build patterns

    @Test(description = "simple test case to validate the status code" , enabled = false)
    public void check_status_code() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
                when().
                get("/workspaces").
                then().
                log().all().  // To get all response in console, If test fails the response does not print
                assertThat().statusCode(200);
    }

    @Test(description = "We can make use of different matchers from hamcrest to validate response body " , enabled = false)
    public void validate_response_body() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", ConfigUtil.getApiKey()).
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("My Workspace", "Team Workspace", "User_Managemnt_team", "QA"),
                        "workspaces.type", hasItems("team", "personal"),
                        "workspaces[0].name", equalTo("My Workspace"),
                        "workspaces[0].name", is(equalTo("My Workspace")),  // Is used just make expression more decorative
                        "workspaces.size()", equalTo(4),  // Don't use equals, it will give casting issue
                        "workspaces.name", hasItems("My Workspace"));

    }

    @Test(description = "if we want extract all response so that we can use specific value to store" , enabled = false)
    public void extract_response() {

        Response response =

                given().
                        baseUri("https://api.postman.com").
                        header("x-api-key", ConfigUtil.getApiKey()).
                        when().
                        get("/workspaces").
                        then().
                        assertThat().
                        statusCode(200).
                        extract().response();

        System.out.println("Response - " + response); // This will not give response as String
        System.out.println("Response - " + response.asString()); // T

    }

    @Test(description = "If we want to exctract only id of first item or any other single value" , enabled = false)
    public void extract_single_value1() {

        Response response =

                given().
                        baseUri("https://api.postman.com").
                        header("x-api-key", ConfigUtil.getApiKey()).
                        when().
                        get("/workspaces").
                        then().
                        assertThat().
                        statusCode(200).
                        extract().response();

        //Method 1 - Using path method if we want the id of first workspace -
        System.out.println("First workspace id - " + response.path("workspaces[0].id"));
        System.out.println("First workspace name - " + response.path("workspaces[0].name"));

        //Method 2 -  Using JsonPath class from restasuured
        JsonPath jsonpath = new JsonPath(response.asString());
        System.out.println("First workspace id - " + jsonpath.getString("workspaces[0].id"));
        System.out.println("First workspace name - " + jsonpath.getString("workspaces[0].name"));

    }


    @Test(description = "If we want to exctract only id of the first item or any other single value")
    public void extract_single_value2() {

        String response =

                given().
                        baseUri("https://api.postman.com").
                        header("x-api-key", ConfigUtil.getApiKey()).
                        when().
                        get("/workspaces").
                        then().
                        assertThat().
                        statusCode(200).
                        extract().response().asString();

        // response().path("workspaces[0].id"); // Method 3 - Directly getting it from response

        // Method 4 - Change response to String -  Using JsonPath
        String id = JsonPath.from(response).getString("workspaces[0].id");
        System.out.println(id);
    }
}
