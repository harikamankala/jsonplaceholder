package api;

import static io.restassured.RestAssured.given;
import org.testng.Reporter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import constants.Constants;
import io.restassured.response.Response;
import pojo.Comment;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetCommentsByPostId extends AbstractBaseAPI{
	
	public Response getCommentsByPostIdAPI(String postId) {
		response = given().get(Constants.GET_COMMENTS_BY_POSTID + postId).andReturn();
		return response;
	}

	public int getCommentsByPostIdAPIResponseCode() {
		Reporter.log("Status Code of GET /comments?postId=postId API " + response.getStatusCode());
		return response.getStatusCode();		
	}
	
	public List<String> getEmailsFromCommentsOfPost() throws JsonParseException, JsonMappingException, IOException {		
		List<Comment> commentsList = getObjectMapper().readValue(getResponse().getBody().asString(), new TypeReference<List<Comment>>(){});		
		
		//Collecting emails from list of Comment objects
		List<String> emails = commentsList.stream().map(Comment::getEmail).collect(Collectors.toList());
		return emails;
	}
}
