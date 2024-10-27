package api.test;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		//logs
		logger = LogManager.getLogger(this.getClass());
		
		//userPayload
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password());
		userPayload.setUserStatus(1);
	}
	
	@Test(priority = 1)
	public void	testPostUser(){
		logger.info("*****Create User*****");
		Response response = UserEndPoints.postUser(userPayload);
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetUser() {
		
		logger.info("*****Reading User*****");
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 3)
	public void testUpdateUser() {
		logger.info("*****Update User*****");
		//Using same payload i am updating the data.
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password());
		userPayload.setUserStatus(0);
		
		Response response = UserEndPoints.putUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
		
		Response responseAfterUpdate = UserEndPoints.getUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("*****Delete User*****");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 200);
	}
}
