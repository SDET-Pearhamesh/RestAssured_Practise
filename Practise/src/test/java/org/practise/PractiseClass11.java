package org.practise;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class PractiseClass11 {

@Test(description = "Sending form data in API request ", enabled = false)
public void sending_form_data(){

    given().
            baseUri("https://postman-echo.com").
            multiPart("Key 1" , "Value 1").
            multiPart("Key 2" , "Value 2").
            log().all().
    when().
            post("/post").
            then().
            log().all().
            assertThat().
    statusCode(200);
}

    @Test(description = "Sending file in API request " , enabled = false)
    public void sending_file(){

        given().
                baseUri("https://postman-echo.com").
                multiPart("file" , new File("/Users/prathameshingale/RestAsuured Practise/Practise/src/main/test.txt")).
                log().all().
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test(description = "downloading file in API request " , enabled = false)
    public void download_file() throws IOException {

     byte[] bytes =   given().
                baseUri("https://raw.githubusercontent.com").
                log().all().
        when().
                get("/appium/appium/master/sample-code/app/ApiDemos-debug.apk").
        then().
                log().all().
                extract().
                response().asByteArray();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        os.write(bytes);
        os.close();
    }



}
