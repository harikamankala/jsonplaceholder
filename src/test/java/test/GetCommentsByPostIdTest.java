 package test;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import api.AbstractBaseAPI;
import api.GetCommentsByPostId;
import constants.Constants;
import pojo.Comment;

public class GetCommentsByPostIdTest extends AbstractBaseAPI{
	
	GetCommentsByPostId getCommentsByPostId = new GetCommentsByPostId();
	
	@BeforeClass
	public void setUp() {		
		baseURI = Constants.BASE_URI;
	}

	@Test(testName = "Valid postId")
	public void verifyGetCommentsByPostIdAPI() throws JsonParseException, JsonMappingException, IOException{		
		response = getCommentsByPostId.getCommentsByPostIdAPI("21");
		assertThat("Verify GET /comments?postId=postId API Response code", getCommentsByPostId.getCommentsByPostIdAPIResponseCode(), equalTo(200));
		List<Comment> actualComments = getObjectMapper().readValue(getResponse().getBody().asString(), new TypeReference<List<Comment>>(){});
		File file = new File(Constants.FILE_PATH + "CommentsByPostId.json");		
		List<Comment> expectedComments = getObjectMapper().readValue(file, new TypeReference<List<Comment>>(){});
		assertThat("Verify Comments of Post", actualComments.equals(expectedComments));		
	}
	
	@Test(testName = "Invalid postId (No user with the given Id)")
	public void verifyGetPostsByUserIdAPI_Invalid() throws JsonParseException, JsonMappingException, IOException{		
		response = getCommentsByPostId.getCommentsByPostIdAPI("600");
		assertThat("Verify GET /comments?postId=postId API Response code", getCommentsByPostId.getCommentsByPostIdAPIResponseCode(), equalTo(404));				
	}
	
	@Test(testName = "Bad postId")
	public void verifyGetPostsByUserIdAPI_BadData() throws JsonParseException, JsonMappingException, IOException{		
		response = getCommentsByPostId.getCommentsByPostIdAPI("abc");
		assertThat("Verify GET /comments?postId=postId API Response code", getCommentsByPostId.getCommentsByPostIdAPIResponseCode(), equalTo(404));			
	}
}
