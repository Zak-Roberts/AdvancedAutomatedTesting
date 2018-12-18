package com.cognizant.MoviesCucumberTests;

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

public class MoviesCucumber {

	Response response;
	RequestSpecification request;
	ValidatableResponse json;
	
	JSONObject responseBodyJSON;

	@Given("^a film exists with the Title Guardians of the Galaxy Two$")
	public void a_film_exists_with_the_Title_Guardians_of_the_Galaxy_Two() throws Throwable {

		request = RestAssured.given().contentType(ContentType.JSON);

	}

	@When("^a user retrieves the film by the title Guardians of the Galaxy Two$")
	public void a_user_retrieves_the_film_by_the_title_Guardians_of_the_Galaxy_Two() throws Throwable {

		response = request.when().get("http://www.omdbapi.com/?t=Guardians+of+the+galaxy+Vol.+2&apikey=91679ce8");

	}

	@Then("^the status code reads (\\d+)$")
	public void the_status_code_reads(int arg1) throws Throwable {

		json = response.then().statusCode(200);

	}

	@Given("^a film exists with the Title IT$")
	public void a_film_exists_with_the_Title_IT() throws Throwable {

		request = RestAssured.given().contentType(ContentType.JSON);
		
	}

	@When("^a user retrieves the film by the title IT$")
	public void a_user_retrieves_the_film_by_the_title_IT() throws Throwable {

		response = request.when().get("http://www.omdbapi.com/?t=It&apikey=91679ce8");
		String responseBody = response.getBody().asString();
		responseBodyJSON = new JSONObject(responseBody);
		
	}

	@Then("^the Rated Field is equal to R$")
	public void the_Rated_Field_is_equal_to_R() throws Throwable {

		Assert.assertEquals("Film is not rated R","R", responseBodyJSON.get("Rated"));
		
		
	}

	@Given("^a film exists with the Title \"([^\"]*)\"$")
	public void a_film_exists_with_the_Title(String arg1) throws Throwable {

		request = RestAssured.given().contentType(ContentType.JSON);
		
	}

	@When("^a user retrieves the film by the title \"([^\"]*)\"$")
	public void a_user_retrieves_the_film_by_the_title(String arg1) throws Throwable {

		response = request.when().get("http://www.omdbapi.com/?t=" + arg1 + "&apikey=91679ce8");
		String responseBody = response.getBody().asString();
		responseBodyJSON = new JSONObject(responseBody);
		
	}

	@Then("^the Rated Field is equal to \"([^\"]*)\"$")
	public void the_Rated_Field_is_equal_to(String arg1) throws Throwable {

		Assert.assertEquals("Not found correct rating!", arg1, responseBodyJSON.get("Rated"));
		
	}

}
