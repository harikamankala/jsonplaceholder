package test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import api.GetCommentsByPostId;
import api.GetPostsByUserId;
import api.GetUsers;
import constants.Constants;
import static io.restassured.RestAssured.baseURI;

import java.io.IOException;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.apache.commons.validator.routines.EmailValidator;

public class AllTests {
	
	GetUsers getUsers = new GetUsers();
	GetPostsByUserId getPostsByUserId = new GetPostsByUserId();
	GetCommentsByPostId getCommentsByPostId = new GetCommentsByPostId();
	
	@BeforeClass
	public void setUp() {		
		baseURI = Constants.BASE_URI;
	}

	@Test
	public void validateEmailFormat() throws JsonProcessingException, JsonMappingException, IOException {
		getUsers.getUsersAPI();
		assertThat("Verify GET /users API Response code", getUsers.getUsersAPIResponseCode(), equalTo(200));
		Reporter.log("userId of Samantha : " + getUsers.getUserIdOfUser("Samantha"));	
		getPostsByUserId.getPostsByUserIdAPI(getUsers.getUserIdOfUser("Samantha"));
		Reporter.log("List of Post Ids : " + getPostsByUserId.getPostIdsOfUser());
		for(int postId : getPostsByUserId.getPostIdsOfUser()) {
			getCommentsByPostId.getCommentsByPostIdAPI(postId);
			List<String> emails = getCommentsByPostId.getEmailsFromCommentsOfPost();			
			for(String email : emails) {
				EmailValidator validator = EmailValidator.getInstance();
				assertThat("Verify email format of email : '" + email + "'", validator.isValid(email));								
			}			
		}
	}
}
