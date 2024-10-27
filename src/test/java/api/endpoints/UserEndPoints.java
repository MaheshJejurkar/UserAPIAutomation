package api.endpoints;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payload.User;

//CRUD operation
//Create, Read, Update and Delete

public class UserEndPoints {
	
	public static Response postUser(User payload){
		Response resonase = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		
		return resonase;
	}
	
	
	public static Response getUser(String username){
		Response response = 
		given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_url)
		;
		
		return response;
	}
	
	
	public static Response putUser(String username, User payload){
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(Routes.update_url)
		;
		
		return response;
	}
	
	
	public static Response deleteUser(String username){
		Response response = 
		given()
			.pathParam("username", username)
		.when()
			.delete(Routes.delete_url)
		;
	
		return response;
	}

}
