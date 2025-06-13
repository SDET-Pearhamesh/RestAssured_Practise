package org.practise;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import static io.restassured.RestAssured.given;

public class PractiseClass2 {


    @Test(description = "This test is to explore simple assertThat methods from hamcrest")
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


}
