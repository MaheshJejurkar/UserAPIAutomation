package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payload.User;

//CRUD operation
//Create, Read, Update and Delete

public class UserEndPoints2 {
	
	//Method created for getting URL's from properties file
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response postUser(User payload){
		String post_url = getURL().getString("post_url");
		
		Response resonase = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return resonase;
	}
	
	
	public static Response getUser(String username){
		String get_url = getURL().getString("get_url");
	
		Response response = 
		given()
			.pathParam("username", username)
		.when()
			.get(get_url)
		;
		
		return response;
	}
	
	
	public static Response putUser(String username, User payload){
		String put_url = getURL().getString("put_url");
		
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(put_url)
		;
		
		return response;
	}
	
	
	public static Response deleteUser(String username){
		String delete_url = getURL().getString("delete_url");
		
		Response response = 
		given()
			.pathParam("username", username)
		.when()
			.delete(delete_url)
		;
	
		return response;
	}

}
