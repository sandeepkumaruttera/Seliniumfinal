package pages;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.openqa.selenium.WebDriver;

import StepDefinitions.SetupClass;
import utilities.BasePage;
import utilities.TestUtils;

public class TrailToTestAPI {

	
	private WebDriver driver;
    private BasePage basePage;
    String projectPath = System.getProperty("user.dir");
    String propertyFile = projectPath + "/project.properties";
    TestUtils utils = new TestUtils(propertyFile);
    String baseUri = utils.getProperty("BaseURI");

    public TrailToTestAPI() {
        this.driver = SetupClass.getDriver();
        this.basePage = SetupClass.getBasePage();
    }
    
    public static void main(String[] args) {
    	TrailToTestAPI trail = new TrailToTestAPI();
    	trail.sendPostRequestToAddAttachment();
    }
    
    
//    @And("validate api")
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
    
//    @And("validate api")
    public void sendPostRequestToAddAttachment() {

        // Define the base URI
        String baseUri = utils.getProperty("BaseURI");

        String username = utils.getProperty("Username");
        String password = utils.getProperty("Password");
        String credentials = org.apache.commons.codec.binary.Base64.encodeBase64String((username + ":" + password).getBytes());
        
        String issueKey = "KAN-6";
        String endpoint = "/rest/api/3/issue/" + issueKey + "/attachments";
        String filePath = projectPath+"/test-output.zip";

        // Make the POST request
        given()
	        .baseUri(baseUri)
	        .auth().preemptive().basic(username, password)
	        .header("X-Atlassian-Token", "no-check")
	        .multiPart("file", new File(filePath))
        .when()
            .post(endpoint)
        .then()
            .log().all()
            .assertThat().statusCode(200);
    }
}
