package api.test;

import org.testng.annotations.Test;
import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class UserTest_DDT {

	@Test(priority = 1, dataProvider = "getAllData", dataProviderClass = DataProviderClass.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName,	String Email, String Password, String Phone) {
		User userPayload = new User();
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response = UserEndPoints.postUser(userPayload);
		response.then().log().body();
	}
	
	@Test(priority = 2, dataProvider = "getUserName", dataProviderClass = DataProviderClass.class)
	void testDeleteUser(String UserName) {
		Response response = UserEndPoints.deleteUser(UserName);
		response.then().log().body();
	}

}
