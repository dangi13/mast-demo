package mast.api.test.mast;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mast.api.utils.common.Config;

public class RestAssuredAPITest {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://gist.githubusercontent.com/kumarpani/1e759f27ae302be92ad51ec09955e765/raw/184cef7125e6ef5a774e60de31479bb9b2884cb5/TeamRCB.json";
		RequestSpecification requestSpecification = RestAssured.given();

		Map<String, String> authParams = new HashMap<String, String>();
		authParams.put("grant_type", Config.getProperty("GRANT_TYPE"));
		authParams.put("audience", Config.getProperty("AUDIENCE"));
		authParams.put("clientSecret", Config.getProperty("CLIENT_SECRET"));
		authParams.put("client_id", Config.getProperty("CLIENT_ID"));

		requestSpecification.formParams(authParams);

		Response response = given().log().all().spec(requestSpecification).when().post().then().and().extract()
				.response();

		System.out.println("Status received => " + response.getStatusLine());
		System.out.println("Response=>" + response.prettyPrint());
	}
}