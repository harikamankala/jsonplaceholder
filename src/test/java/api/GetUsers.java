package api;

import static io.restassured.RestAssured.given;
import org.testng.Reporter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import constants.Constants;
import io.restassured.response.Response;
import pojo.User;
import java.io.IOException;
import java.util.List;

public class GetUsers extends AbstractBaseAPI{
	
	public void getUsersAPI() {
		response = given().get(Constants.GET_USERS).andReturn();
	}

	public int getUsersAPIResponseCode() {
		Reporter.log("Status Code of GET /users API " + response.getStatusCode());
		return response.getStatusCode();		
	}
	
	public int getUserIdOfUser(String userName) throws JsonParseException, JsonMappingException, IOException {		
		List<User> usersList = getObjectMapper().readValue(getResponse().getBody().asString(), new TypeReference<List<User>>(){});	
		
		//finding user whose name matches with the userName from a list of users
		User user = usersList.stream().filter(element -> element.getUsername().equals(userName)).findFirst().orElse(null);
		return user.getId();
	}
	
	public Response getUserByIdAPI(String userId) {
		return response = given().get(Constants.GET_USERS + Constants.SLASH + userId).andReturn();
	}
	
	
}
