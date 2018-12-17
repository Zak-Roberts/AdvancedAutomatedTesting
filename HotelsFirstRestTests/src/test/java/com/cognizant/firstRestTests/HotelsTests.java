package com.cognizant.firstRestTests;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class HotelsTests {

	private RequestSpecification request;
	private Response response;
	private ValidatableResponse json;

	@Test
	public void exampleRestTestWhichShouldBe200() {
		RestAssured.given().contentType(ContentType.JSON).when().get("http://localhost:8090/example/v1/hotels/").then()
				.statusCode(200);
	}

	@Test
	public void getRequest() {
		request = RestAssured.given().contentType(ContentType.JSON);
		response = request.when().get("http://localhost:8090/example/v1/hotels/");
		json = response.then().statusCode(200);
	}

	@Test
	public void postRequest() {
		request = RestAssured.given().contentType(ContentType.JSON);
		request.header("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("city", "Berlin");
		jsonObject.put("description", "Lovely hotel");
		jsonObject.put("rating", "5");
		jsonObject.put("name", "Hotel Berlin");

		request.body(jsonObject.toString());

		response = request.post("http://localhost:8090/example/v1/hotels/");
		json = response.then().statusCode(201);
	}

	@Test
	public void deleteRequest() {
		RestAssured.given().contentType(ContentType.JSON).when().delete("http://localhost:8090/example/v1/hotels/4")
				.then().statusCode(204);
	}

	@Test
	public void putRequest() { // updates!
		request = RestAssured.given().contentType(ContentType.JSON);
		request.header("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "3");
		jsonObject.put("city", "Frankfurt");
		jsonObject.put("description", "Lovely hotel");
		jsonObject.put("rating", "5");
		jsonObject.put("name", "Hotel Frankfurt");

		request.body(jsonObject.toString());

		response = request.post("http://localhost:8090/example/v1/hotels/");
		json = response.then().statusCode(201);
	}
}
