package org.practise;

import io.restassured.http.Headers;
import org.hamcrest.Description;
import org.testng.annotations.Test;
import io.restassured.http.Header;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class PractiseClass4 {


    @Test(description = "How to send single headers in the request" , enabled = false)
    public void single_header_InRequest(){

        /* Method 1 = header("Key1" , "value 1").
                      header("Key2" , "value 2")  */

        // Method 2 - Using header class from Rest assured

        // Some has 2 values for same header. In that case use header("Key 1"  , "Value 1 " , "Value 2")
        // We cant use map as it does not accept  duplicate key. User Header class

        Header authorizationHeader = new Header("x-api-key" , ConfigUtil.getApiKey());

        given().
                baseUri("https://api.postman.com").
                header(authorizationHeader).
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);

    }

    @Test(description = "How to send multiple headers in the request" , enabled = false)
    public void multiple_header_InRequest(){

        // Method 1 = We can user header() method multiple times. header().header().header().header().when()

        // Method 2 - Using header class from Rest assured. Where we can create multiple instances of header class
        // Header authorizationHeader = new Header(header1 , header 2, header 3);

        //Method 3 - Efficient - Use hashmap and add values. Refer below for implementation

        HashMap<String , String > headers = new HashMap<>();

        headers.put("x-api-key" , ConfigUtil.getApiKey());
     //   headers.put("key 1 " , " value 1");
     //   headers.put("key 2 " , " value 2");
     //  headers.put("key 3 " , " value 3");

        given().
                baseUri("https://api.postman.com").
                headers(headers).                     // we have used headers not header
        when().
                get("/workspaces").
        then().
                log().all().
                assertThat().
                statusCode(200);

    }

    @Test(description = "In this method we are verifying the response headers. Single and Multiple headers of response" , enabled = false)
    public void assert_response_headers(){

        Header authorizationHeader = new Header("x-api-key" , ConfigUtil.getApiKey());

        given().baseUri("https://api.postman.com")
                .header(authorizationHeader).
        when().get("/workspaces").
        then().assertThat().
                statusCode(200).
                header("Connection" ,"keep-alive").          // when we want to assert single header in response
        headers("x-ratelimit-limit" , "10" ,
                "x-ratelimit-reset" ,  "9" ,                // user headers method with all headers as arguments
                "x-ratelimit-remaining" , "9");

    }

    @Test(description = "To extract response headers to add asserts ot use further")
    public void extract_responseHeaders(){


     Headers extractHeaders =

        given().
                baseUri("https://api.postman.com").
                header("x-api-key" , ConfigUtil.getApiKey()).
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                extract().headers();

     System.out.println("Header : " + extractHeaders.get("Connection"));
     System.out.println("Header name : " + extractHeaders.get("Connection").getName());
     System.out.println("Header value : " + extractHeaders.getValue("Connection"));

     for(Header header: extractHeaders ){
         System.out.print( header.getName());
         System.out.println(header.getValue());
     }

     // If any header has multiple values. Store it in list<String> values = extractHeaders.getValues("NanemOfHeader")
        // Use for each loop to extract values
    }

}
