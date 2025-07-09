package pages;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.WebDriver;

import StepDefinitions.SetupClass;
import io.cucumber.java.en.And;
import utilities.BasePage;
import utilities.TestUtils;


public class Trail {
	
	private WebDriver driver;
    private BasePage basePage;
    String projectPath = System.getProperty("user.dir");
    String propertyFile = projectPath + "/project.properties";
    TestUtils utils = new TestUtils(propertyFile);
    String baseUri = utils.getProperty("BaseURI");

    public Trail() {
        this.driver = SetupClass.getDriver();
        this.basePage = SetupClass.getBasePage();
    }
    @And("validate api")
    public void sample() {
        // Define the base URI
    	
    	

        String username = utils.getProperty("Username");
        String password = utils.getProperty("Password");

        String credentials = org.apache.commons.codec.binary.Base64.encodeBase64String((username + ":" + password).getBytes());

        given()
            .baseUri(baseUri)
            .header("Authorization", "Basic " + credentials)
            .header("Accept", "application/json")
        .when()
            .get("issue/KAN-1")
        .then()
            .log().all()
            .assertThat().statusCode(200);
    }
    
    
    public void sendPostRequest() {
        // Define the JSON body data
    	String bodyData = "{\n"
    			+ "    \"fields\": {\n"
    			+ "        \"summary\": \"Create new issue for testing from postman\",\n"
    			+ "        \"description\": {\n"
    			+ "          \"type\": \"doc\",\n"
    			+ "          \"version\": 1,\n"
    			+ "          \"content\": [\n"
    			+ "            {\n"
    			+ "              \"type\": \"paragraph\",\n"
    			+ "              \"content\": [\n"
    			+ "                {\n"
    			+ "                  \"type\": \"text\",\n"
    			+ "                  \"text\": \"description\"\n"
    			+ "                }\n"
    			+ "              ]\n"
    			+ "            }\n"
    			+ "          ]\n"
    			+ "        },\n"
    			+ "        \"issuetype\": {\n"
    			+ "            \"id\": \"10005\"\n"
    			+ "        },\n"
    			+ "        \"project\": {\n"
    			+ "            \"id\":\"10000\"\n"
    			+ "        }\n"
    			+ "    }\n"
    			+ "}";

        // Define the base URI
        String baseUri = utils.getProperty("BaseURI");

        String username = utils.getProperty("Username");
        String password = utils.getProperty("Password");
        String credentials = org.apache.commons.codec.binary.Base64.encodeBase64String((username + ":" + password).getBytes());
        
        // Make the POST request
        given()
            .baseUri(baseUri)
            .header("Authorization", "Basic " + credentials)
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .body(bodyData)
        .when()
            .post("issue")
        .then()
            .log().all()
            .assertThat().statusCode(201);
    }
}
