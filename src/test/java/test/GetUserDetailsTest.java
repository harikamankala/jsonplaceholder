package test;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import api.AbstractBaseAPI;
import api.GetUsers;
import constants.Constants;
import pojo.User;

public class GetUserDetailsTest extends AbstractBaseAPI{
	
	GetUsers getUsers = new GetUsers();
	
	@BeforeClass
	public void setUp() {		
		baseURI = Constants.BASE_URI;
	}

	@Test(testName = "Valid userId")
	public void verifyGetUserDetailsByIdAPI() throws JsonParseException, JsonMappingException, IOException{		
		response = getUsers.getUserByIdAPI("3");
		assertThat("Verify GET /users API Response code", getUsers.getUsersAPIResponseCode(), equalTo(200));
		User actualUser = getObjectMapper().readValue(getResponse().getBody().asString(), User.class);
		File file = new File(Constants.FILE_PATH + "UserDetails.json");		
		User expectedUser = getObjectMapper().readValue(file, User.class);
		assertThat("Verify User Details", actualUser.equals(expectedUser));		
	}
	
	@Ignore
	@Test(testName = "Invalid userId (No user with the given Id)")
	public void verifyGetUserDetailsByIdAPI_Invalid() throws JsonParseException, JsonMappingException, IOException{		
		response = getUsers.getUserByIdAPI("20");
		assertThat("Verify GET /users API Response code", getUsers.getUsersAPIResponseCode(), equalTo(Constants.NOT_FOUND_CODE));				
	}
	
	@Ignore
	@Test(testName = "Bad userId")
	public void verifyGetUserDetailsByIdAPI_BadData() throws JsonParseException, JsonMappingException, IOException{		
		response = getUsers.getUserByIdAPI("abc");
		assertThat("Verify GET /users API Response code", getUsers.getUsersAPIResponseCode(), equalTo(Constants.NOT_FOUND_CODE));			
	}
}
