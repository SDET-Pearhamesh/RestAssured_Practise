package org.practise;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PractiseClass5 {


    RequestSpecification rs;


@BeforeClass(description = "Instead od rewriting same request method we can use RequestSpecification ")
    public void beforeClass(){

    System.out.println("================================Running Before Class method===================================");
         rs =  with().                 // I am using with method instead of given(). Only difference is syntax both operate same as per functionality
                baseUri("https://api.postman.com").
                header("x-api-key" , ConfigUtil.getApiKey()) ;

    // Above code can be used in subsequent methods rather than written duplicate

    }

@Test(description = "RequestSpecification is an interface that allows you to define the common " +
        "configurations and settings for your API requests, making your code more reusable and maintainable")
    public void request_specification(){

    given(rs).
    when().
              get("/workspaces").
    then().
              assertThat().
              statusCode(200);
}

@Test(description = "Lets convert from BDD Style thats given, when, then format to Non BDD")
    public void BDDtoNONBDD(){

   Response response =  rs.get("/workspaces").then().log().all().extract().response();

   assertThat(response.statusCode() , is(equalTo(200)));
   assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));
}
}
