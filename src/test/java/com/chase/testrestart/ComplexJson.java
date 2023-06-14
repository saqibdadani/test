package com.chase.testrestart;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
	public static void main(String[] args) {
		
	
	
	JsonPath js = new JsonPath(payload.courses());
	
	int course_Count = js.getInt("courses.size()");
	
	System.out.println(course_Count);
	int purchaseAmount = js.getInt("dashboard.purchaseAmount");
	//System.out.println(purchaseAmount);
	
	for (int i=0; i<course_Count; i++) {
	String title = js.getString("courses["+i+"].title");
	if(title.equalsIgnoreCase("RPA")){
		System.out.print(title + "    ");
		int copies = js.get("courses["+i+"].copies");
		System.out.println(copies);
	break;
	}
	}
	}
	
}
