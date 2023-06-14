package com.chase.testrestart;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReUsableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Testcase {

	public static void main(String[] args) throws IOException {

		RestAssured.baseURI = Urls.URL;
//TestCase 1: Add place API
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String (Files.readAllBytes(Paths.get("/Users/mohamedsaqib/eclipse-workspace/testrestart/src/test/java/files/addPlacePayload.json")))).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		System.out.println(response);

		
		JsonPath js = ReUsableMethod.rawToJson(response);
		String place_id = js.get("place_id");
		System.out.println(place_id);

//TestCase 2: Update place APIUpdate place
		String newAddress = "70 Summer walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\"place_id\":\"" + place_id + "\",\n" + "\"address\":\"" + newAddress + "\",\n"
						+ "\"key\":\"qaclick123\"}")
				.when().log().all().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

//TestCase 3: Verify address using get
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).when()
				.log().all().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		JsonPath js1 = ReUsableMethod.rawToJson(getResponse);
		String updatedAddress = js1.getString("address");
		System.out.println(updatedAddress);
		Assert.assertEquals(newAddress,updatedAddress);
	
	}

}
