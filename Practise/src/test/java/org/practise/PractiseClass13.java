package org.practise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class PractiseClass13 {



    @Test(enabled = false)
    public void serialize_json_using_jackson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode nestedObjectNode = objectMapper.createObjectNode();
        nestedObjectNode.put("name", "myWorkspace3");
        nestedObjectNode.put("type", "personal");
        nestedObjectNode.put("description", "Rest Assured created this");

        ObjectNode mainObjectNode = objectMapper.createObjectNode();
        mainObjectNode.set("workspace", nestedObjectNode);

        String mainObjectStr = objectMapper.writeValueAsString(mainObjectNode);

//        given()
//                .body(mainObjectNode)
//        .when()
//                .post("/workspaces")
//        .then()
//                .spec(responseSpecification)
//                .assertThat()
//                .body("workspace.name", equalTo("myWorkspace3"))
//                .body("workspace.id", matchesPattern("^[a-z0-9-]{36}$"));


        System.out.println(mainObjectNode);
    }

    @Test
    public void serialize_json_array_using_jackson() throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNodeList = objectMapper.createArrayNode();

        ObjectNode obj5001Node = objectMapper.createObjectNode();
        obj5001Node.put("id", "5001");
        obj5001Node.put("type", "None");

        ObjectNode obj5002Node = objectMapper.createObjectNode();
        obj5002Node.put("id", "5002");
        obj5002Node.put("type", "Glazed");

        arrayNodeList.add(obj5001Node);
        arrayNodeList.add(obj5002Node);

        String jsonListStr = objectMapper.writeValueAsString(arrayNodeList);

//        given()
//                .body(jsonListStr)
//                .when()
//                .post("/post")
//                .then()
//                .spec(customResponseSpecification)
//                .assertThat()
//                .body("msg", equalTo("Success"));

        System.out.println(jsonListStr);
    }

}
