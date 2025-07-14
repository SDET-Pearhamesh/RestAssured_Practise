package org.practise;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PractiseClass14 {

    @Test(enabled = false)
    public void way_To_send_cookies(){

       Response response=  given().
                cookie("cookieName", "value1").
               when().get().
               then().extract().response();

       // Way to get value of SessionID from Cookies for further use
       String cookieId = response.getCookie("SessionID");


       Response response1=  given().
                cookie("cookieName", "value1", "value2").
               when().get().
               then().extract().response();

       // Way to get detailed cookie
        String cookieIdFromResponse = String.valueOf(response.getDetailedCookie("SessionID"));

    }

    @Test(enabled = false)
    public void send_ising_cookieBuilder(){

       //  You can also specify a detailed cookie using:

        Cookie someCookie = new Cookie.Builder("some_cookie", "some_value").setSecured(true).setComment("some comment").build();

      //  given().cookie(someCookie).when().get("/cookie").then().assertThat().body(equalTo("x"));
        System.out.println(someCookie);

        //or several detailed cookies at the same time:

        Cookie cookie1 = new Cookie.Builder("username", "John").setComment("comment 1").build();
        Cookie cookie2 = new Cookie.Builder("token", "1234").setComment("comment 2").build();

        Cookies cookies = new Cookies(cookie1, cookie2);

     //   given().cookies(cookies).when().get("/cookie").then().body(equalTo("username, token"));

        System.out.println(cookies);



    }
}
