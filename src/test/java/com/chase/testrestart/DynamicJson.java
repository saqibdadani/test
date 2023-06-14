package com.chase.testrestart;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsableMethod;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider="bookData")
	public void addBook(String isbn,String asile) {
		
	RestAssured.baseURI= "http://216.10.245.166";
	
	String response = given().header("Content-Type","application/json").body(payload.addBook(isbn,asile))
	.when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js = ReUsableMethod.rawToJson(response);
	String Id = js.get("ID");
	System.out.println(Id);

	}
	
@DataProvider
public Object[][] bookData() {
	
	return new Object [] [] {{"zhd1","33333"},{"zhd2","33335"},{"zhd3e","33334"}};
	
}
}


