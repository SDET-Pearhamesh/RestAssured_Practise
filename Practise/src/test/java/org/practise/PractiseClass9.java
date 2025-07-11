package org.practise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.testng.annotations.Test;

public class PractiseClass9 {

    private String name;
    private int mobileNumber;
    private String[] documents;


    public PractiseClass9() {
        // Default constructor needed by TestNG and JSON serialization libraries
    }

    public PractiseClass9(String name , int mobileNumber, String[] documents){

        this.name = name;
        this.mobileNumber = mobileNumber;
        this.documents = documents;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setDocuments(String[] documents) {
        this.documents = documents;
    }


    public String getName() {
        return name;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String[] getDocuments() {
        return documents;
    }

    @Test(description = "Add Jackson Dependency" , enabled = false)
    public  void payloadTest() throws JsonProcessingException {

        PractiseClass9 pc9 = new PractiseClass9("Prathamesh", 989876765, new String[]{"AADHAR", "PAN"});

        // 🌟 Convert POJO to JSON String 🌟
        ObjectMapper objectMapper = new ObjectMapper();

        // Optional: Configure for pretty printing JSON (nicer to read in console)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            String jsonPayload = objectMapper.writeValueAsString(pc9);
            System.out.println("Generated JSON Payload:");
            System.out.println(jsonPayload);

    }




}
