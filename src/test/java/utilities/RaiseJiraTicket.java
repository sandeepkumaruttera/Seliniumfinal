package utilities;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RaiseJiraTicket {

	String projectPath = System.getProperty("user.dir");
    String propertyFile = projectPath + "/project.properties";
    TestUtils utils = new TestUtils(propertyFile);
    String baseUri = utils.getProperty("BaseURI");
    
    public RaiseJiraTicket(String failedTest) {
    	String key = createBugPostRequest(failedTest);
    	addAttachmentToBugCreated(key);
    }
	
    public String createBugPostRequest(String scenarioName) {
        try {
        	String os = System.getProperty("os.name");
        	String bodyData = "{\n"
        		    + "    \"fields\": {\n"
        		    + "        \"summary\": \"Automation: Failed to " + scenarioName + "\",\n"
        		    + "        \"description\": {\n"
        		    + "            \"type\": \"doc\",\n"
        		    + "            \"version\": 1,\n"
        		    + "            \"content\": [\n"
        		    + "                {\n"
        		    + "                    \"type\": \"paragraph\",\n"
        		    + "                    \"content\": [\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Description:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"This bug is created through Automation, Detailed step by step execution and test coverage are present in the attached .zip file. Please download and extract the test-output.zip file for more details\\n\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Summary:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Automation Test Script for the Scenario -" + scenarioName + " was failed during execution.\\n\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Details:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Test Scenario: " + scenarioName + "\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Environment: " + os + " \\n\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Steps to see Test Report:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"1. Download the attached test-output.zip file attached to this bug\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"2. Extract the contents of the zip file\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"3. For HTML report open reports folder present inside test-output folder\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"4. Open Spark.html file for detailed view of execution dashboard and navigate through pages for step-wise and scenario-wise reports\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"5. For PDF report please open PdfReport folder and open ExtentPdf.pdf file\\n\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Expected Result:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Script should pass with all validations and no flow should work without breaking in-between\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Actual Result:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Script failed please refer to the attachment for reason\\n\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Attachments:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"test-output.zip\\n\\n\"\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Additional Information:\\n\",\n"
        		    + "                            \"marks\": [\n"
        		    + "                                {\n"
        		    + "                                    \"type\": \"strong\"\n"
        		    + "                                }\n"
        		    + "                            ]\n"
        		    + "                        },\n"
        		    + "                        {\n"
        		    + "                            \"type\": \"text\",\n"
        		    + "                            \"text\": \"Please contact Assignee for re-run or any assistance.\\n\"\n"
        		    + "                        }\n"
        		    + "                    ]\n"
        		    + "                }\n"
        		    + "            ]\n"
        		    + "        },\n"
        		    + "        \"issuetype\": {\n"
        		    + "            \"id\": \"10005\"\n"
        		    + "        },\n"
        		    + "        \"project\": {\n"
        		    + "            \"id\":\"10000\"\n"
        		    + "        },\n"
        		    + "        \"reporter\": {\n"
        		    + "            \"id\": \"5e395beccaf29f0cb8df80d7\"\n"
        		    + "        },\n"
        		    + "        \"assignee\": {\n"
        		    + "            \"id\": \"5e395beccaf29f0cb8df80d7\"\n"
        		    + "        }\n"
        		    + "    }\n"
        		    + "}";







            String baseUri = utils.getProperty("BaseURI");

            String username = utils.getProperty("Username");
            String password = utils.getProperty("Password");
            String credentials = org.apache.commons.codec.binary.Base64.encodeBase64String((username + ":" + password).getBytes());

            // Make the POST request
            Response response = RestAssured.given()
                    .baseUri(baseUri)
                    .header("Authorization", "Basic " + credentials)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(bodyData)
                    .when()
                    .post("issue");

            if (response.getStatusCode() == 201) {
                String key = response.jsonPath().getString("key");
                System.out.println("Response body: " + response.getBody().asString());
                return key;
            } else {
                System.err.println("Failed to create bug. Response code: " + response.getStatusCode());
                System.err.println("Response body: " + response.getBody().asString());
                return null;
            }
        } catch (Exception e) {
            // If an exception occurs, print the stack trace
            e.printStackTrace();
            return null;
        }
    }
	
	
	public void addAttachmentToBugCreated(String issueKey) {
		try {
	        String baseUri = utils.getProperty("BaseURI");
	
	        String username = utils.getProperty("Username");
	        String password = utils.getProperty("Password");
	        
	        String endpoint = "/rest/api/3/issue/" + issueKey + "/attachments";
	        System.out.println("End point"+endpoint);
	        String filePath = projectPath+"/test-output.zip";
	
	        given()
		        .baseUri(baseUri)
		        .auth()
		        .preemptive()
		        .basic(username, password)
		        .header("X-Atlassian-Token", "no-check")
		        .multiPart("file", new File(filePath))
	        .when()
	            .post(endpoint)
	        .then()
	//            .log().all()
	            .assertThat().statusCode(200);
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
