package constants;

public class Constants {
	
	public static final String BASE_URI = "http://jsonplaceholder.typicode.com";
	public static final String GET_USERS = "/users";
	public static final String GET_POSTS_BY_USERID = "/posts?userId=";
	public static final String GET_COMMENTS_BY_POSTID = "/comments?postId=";
	public static final String SLASH = "/";
	public static final int SUCCESS_CODE = 200;
	public static final int NOT_FOUND_CODE = 404;
	public static final int BAD_REQUEST_CODE = 400;
	public static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/TestData/";
}
