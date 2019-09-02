package test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import api.GetUsers;
import constants.Constants;
import static io.restassured.RestAssured.baseURI;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AllTests {
	
	GetUsers getUsers = new GetUsers();
	
	@BeforeClass
	public void setUp() {		
		baseURI = Constants.BASE_URI;
	}

	@Test
	public void validateEmailFormat() throws JsonProcessingException, JsonMappingException, IOException {
		getUsers.getUsersAPI();
		assertThat("Verify GET /users API Response code", getUsers.getUsersAPIResponseCode(), equalTo(200));
		Reporter.log("userId of Samantha : " + getUsers.getUserIdOfUser("Samantha"));	
	}
}
