package test;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import api.AbstractBaseAPI;
import api.GetPostsByUserId;
import constants.Constants;
import pojo.Post;

public class GetPostsByUserIdTest extends AbstractBaseAPI{
	
	GetPostsByUserId getPostsByUserId = new GetPostsByUserId();
	
	@BeforeClass
	public void setUp() {		
		baseURI = Constants.BASE_URI;
	}

	@Test(testName = "Valid userId")
	public void verifyGetPostsByUserIdAPI() throws JsonParseException, JsonMappingException, IOException{		
		response = getPostsByUserId.getPostsByUserIdAPI("3");
		assertThat("Verify GET /posts?userId=userId API Response code", getPostsByUserId.getPostsByUserIdAPIResponseCode(), equalTo(200));
		List<Post> actualPosts = getObjectMapper().readValue(getResponse().getBody().asString(), new TypeReference<List<Post>>(){});
		File file = new File(Constants.FILE_PATH + "PostsByUserId.json");		
		List<Post> expectedPosts = getObjectMapper().readValue(file, new TypeReference<List<Post>>(){});
		assertThat("Verify User Posts", actualPosts.equals(expectedPosts));		
	}
	
	@Ignore
	@Test(testName = "Invalid userId (No user with the given Id)")
	public void verifyGetPostsByUserIdAPI_Invalid() throws JsonParseException, JsonMappingException, IOException{		
		getPostsByUserId.getPostsByUserIdAPI("20");
		assertThat("Verify GET /posts?userId=userId API Response code", getPostsByUserId.getPostsByUserIdAPIResponseCode(), equalTo(Constants.NOT_FOUND_CODE));				
	}
	
	@Ignore
	@Test(testName = "Bad userId")
	public void verifyGetPostsByUserIdAPI_BadData() throws JsonParseException, JsonMappingException, IOException{		
		getPostsByUserId.getPostsByUserIdAPI("abc");
		assertThat("Verify GET /posts?userId=userId API Response code", getPostsByUserId.getPostsByUserIdAPIResponseCode(), equalTo(Constants.BAD_REQUEST_CODE));			
	}
}
