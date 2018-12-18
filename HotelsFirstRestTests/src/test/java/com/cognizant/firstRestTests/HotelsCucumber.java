package com.cognizant.firstRestTests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class HotelsCucumber {

	Response response;
	RequestSpecification request;
	ValidatableResponse json;

	JSONObject responseBodyJSON;
	JSONObject object;

	@Given("^a hotel exists with the name Hotel Berlin$")
	public void a_hotel_exists_with_the_name_Hotel_Berlin() throws Throwable {

		request = RestAssured.given().contentType(ContentType.JSON);

	}

	@When("^a user retrieves the hotel by the name Hotel Berlin$")
	public void a_user_retrieves_the_hotel_by_the_name_Hotel_Berlin() throws Throwable {

		response = request.when().get("http://localhost:8090/example/v1/hotels/");

	}

	@Then("^the status code reads (\\d+)$")
	public void the_status_code_reads(int arg1) throws Throwable {

		json = response.then().statusCode(200);

	}

	@Then("^the Rating Field is equal to (\\d+)$")
	public void the_Rating_Field_is_equal_to(int arg1) throws Throwable {

		response = request.when().get("http://localhost:8090/example/v1/hotels/");
		String responseBody = response.getBody().asString();
		responseBodyJSON = new JSONObject(responseBody);
		String content = responseBodyJSON.get("content").toString();
		JSONArray contents = new JSONArray(content);
		JSONObject rating = new JSONObject(contents.get(0).toString());

		Assert.assertEquals("Hotel not rated properly", 5, rating.get("rating"));

	}

	@Given("^a hotel exists with the name \"([^\"]*)\"$")
	public void a_hotel_exists_with_the_name(String arg1) throws Throwable {

		request = RestAssured.given().contentType(ContentType.JSON);

	}

	@When("^a user retrieves the hotel by the name \"([^\"]*)\"$")
	public void a_user_retrieves_the_hotel_by_the_name(String arg1) throws Throwable {

		response = request.when().get("http://localhost:8090/example/v1/hotels/");
		response = request.when().get("http://localhost:8090/example/v1/hotels/");
		String responseBody = response.getBody().asString();
		responseBodyJSON = new JSONObject(responseBody);
		String content = responseBodyJSON.get("content").toString();
		JSONArray contents = new JSONArray(content);

		for (int i = 0; i < contents.length(); i++) {
			if (new JSONObject(contents.get(i).toString()).get("name").equals(arg1)) {
				object = new JSONObject(contents.get(i).toString());
			}
		}

	}

	@Then("^the rating Field is equal to \"([^\"]*)\"$")
	public void the_rating_Field_is_equal_to(int arg1) throws Throwable {

		Assert.assertEquals("Hotel not rated properly", arg1, object.get("rating"));

	}

}
