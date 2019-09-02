package api;

import static io.restassured.RestAssured.given;
import org.testng.Reporter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import constants.Constants;
import pojo.Post;
import pojo.User;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetPostsByUserId extends AbstractBaseAPI{
	
	public void getPostsByUserIdAPI(int userId) {
		response = given().get(Constants.GET_POSTS_BY_USERID + userId).andReturn();
	}

	public int getPostsByUserIdAPIResponseCode() {
		Reporter.log("Status Code of GET /posts?userId=userId API " + response.getStatusCode());
		return response.getStatusCode();		
	}
	
	public List<Integer> getPostIdsOfUser() throws JsonParseException, JsonMappingException, IOException {		
		List<Post> postsList = getObjectMapper().readValue(getResponse().getBody().asString(), new TypeReference<List<Post>>(){});		
		List<Integer> postIds = postsList.stream().map(Post::getId).collect(Collectors.toList());
		return postIds;
	}
}
