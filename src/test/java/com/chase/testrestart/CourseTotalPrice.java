package com.chase.testrestart;

import org.junit.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class CourseTotalPrice {
	
	
	@Test
	public void compareAmount() {
		
		int totalSum = 0;
		
		JsonPath js = new JsonPath(payload.courses());
		
		int Total_Amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(Total_Amount);
		int count = js.getInt("courses.size()");
		System.out.println(count);
		for (int i=0; i <count; i++) {
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int Sum = price*copies;
		totalSum = totalSum +Sum;
		}
		System.out.println(totalSum);
		
		Assert.assertEquals(Total_Amount, totalSum);
		
	}

}
