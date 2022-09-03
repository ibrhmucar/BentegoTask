package com.n11.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestApi {
    Object petId;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void ApisetUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }


    @Test(priority = 0)
    public void create_new_pet_positive() {
        //Create a new pet and verify created successfully

        String createBody = "{\"id\":0,\"category\"" +
                ":{\"id\":0,\"name\":\"string\"},\"name\":\"NewBird\",\"photoUrls\"" +
                ":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(createBody)
                .when().post("/pet");
        petId = response.path("id");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("name"), "NewBird");
        System.out.println("petId = " + petId);
    }

    @Test(priority = 1)
    public void create_new_pet_negative() {
        //Try to create a new pet without status information

        String body = "{\"id\":0,\"category\"" +
                ":{\"id\":0,\"name\":\"string\"},\"name\":\"NewBird\",\"photoUrls\"" +
                ":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\"}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(body)
                .when().post("/pet");

        System.out.println("petId = " + petId);
        softAssert.assertEquals(response.statusCode(),200);
        String message = response.path("message").toString().toUpperCase();
        softAssert.assertAll(message);



    }

    @Test(priority = 2)
    public void update_pet_information_positive() {
        //update an existing pet information and verify updated successfully
        String updatedBody = "{\"id\"" +
                ":" + petId + ",\"category\":{\"id\":0,\"name\":\"string\"},\"name\"" +
                ":\"updatedNewBirdName\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(updatedBody)
                .when().put("/pet");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("name"), "updatedNewBirdName");
        Assert.assertEquals(response.path("id"), petId);
        System.out.println("petId = " +petId);
    }

    @Test(priority = 3)
    public void update_pet_information_negative() {
        //failure to update an existing pet information and verify updated unsuccessfully
        String updatedBody = "{{\"id\":0," +
                "\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"updatedNewBirdName\"" +
                ",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(updatedBody)
                .when().put("/pet");


        softAssert.assertEquals(response.getStatusCode(),200);
        String message = response.path("message").toString().toUpperCase();
        softAssert.assertAll(message);


    }

    @Test(priority = 4)
    public void find_pet_information_positive() {
        //find pet by ID and verify get successfully
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", petId)
                .when().get("/pet/{petId}");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("name"), "updatedNewBirdName");
        Assert.assertEquals(response.path("id"), petId);
    }

    @Test(priority = 5)
    public void find_pet_information_negative() {
        //failure to find pet by ID and verify get unsuccessfully
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", "9999999888888")
                .when().get("/pet/{petId}");

        softAssert.assertEquals(response.getStatusCode(),200);
        String message = response.path("message").toString().toUpperCase();
        softAssert.assertAll(message);
    }

    @Test(priority = 6)
    public void deletes_pet_information_positive() {
        //deletes a pets information successfully and verify deleted successfully
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", petId)
                .when().delete("/pet/{petId}");

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.contentType(), "application/json");
        Assert.assertEquals(response.path("message"), String.valueOf(petId));

    }

    @Test(priority = 7)
    public void deletes_pet_information_negative() {
        //failure to deletes a pets information and verify deleted unsuccessfully
        Response response = RestAssured.given().contentType(ContentType.JSON)
                .and().pathParams("petId", "748")
                .when().delete("/pet/{petId}");

        softAssert.assertEquals(response.getStatusCode(),200);
        String message = Integer.toString(response.getStatusCode())+" Not Found ";
        softAssert.assertAll(message);


    }



}