package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class AbstractBaseAPI {
	
	protected Response response;
	
	protected Response getResponse() {
		return response;
	}

	protected void setResponse(Response response) {
		this.response = response;
	}

	protected ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
    	return objectMapper;
    }

}
