#Author: Eswar Kumar Kota
@tag
Feature: Main test cases to run in CI/CD Pipeline
  These tests are integrated and ran with CI/CD pipeline

 #Vishal
 @tag1
 Scenario: Validate header of nandamurisolutions
    Given browser is open
    When user is on nandamuri website home page
    And user is able to see the logo image
    And user is able to see Home button
    When user clicks Home
    Then user navigates to nandamuri home page
    And user is able to see About us button
    When user clicks About us 
    Then user navigates to About us page
    Then user navigates back to nandamuri website home page

     And user is able to see services button
    When user hovers on services button 
    Then user is able to see dropdown list
    
     And user Expands  Services dropdown 
    When Clicks on ML service  
    Then user navigates to ML service page 
    Then user navigates back to nandamuri website home page

    And user Expands  Services dropdown 
    When Clicks on Data Engineering service  
    Then user navigates to Data Engineering service page 
    Then user navigates back to nandamuri website home page
   
    
    And user Expands  Services dropdown 
    When Clicks on Testing service  
    Then user navigates to Testing service page 
    Then user navigates back to nandamuri website home page
    
    
    And user Expands  Services dropdown 
    When Clicks on Fullstack service  
    Then user navigates to Fullstack service page 
    Then user navigates back to nandamuri website home page
    
    
    And user Expands  Services dropdown 
    When Clicks on Devops service  
    Then user navigates to Devops service page 
    Then user navigates back to nandamuri website home page
   
    And user is able to see contact us button
    When Clicks on contact us button
    Then navigate to contact us page
    And user is able to see whatsapp logo
    And user is able to see contact number 
    Then End of the test
 
 
 #vamshi
 @tag2
 Scenario: Validate  lables in contact us page 
    Given browser is open
    When user is on nandamuri website
    And user is navigated to contact us page
    And user is able to see name label 
    And user is able to see email label
    And user is able to see contact number label
    And user is able to see subject label
    And user is able to see message label
    And user is able to see send message button
    Then end of test cases
    
 #Vamshi
 @tag3
 Scenario: Validate phone, location and email in contact us page
    Given browser is open
    When user is on nandamurisloution website
    And user is navigated to contact us page
    And user is able to see the phone number label
    And user is able to see Location label
    And user is able to see email address label
    Then end of the test cases
    
 #Vamshi   
 @tag4
 Scenario: Validate social platform icons in the contact us page
    Given browser is open
    When user is on google search
    And user is on nandamurisloution website
    And user is navigated to contact us page
    And user is able to see facebook icon in contact us page
    And user is able to see twitter icon in contact us page
    And user is able to see insta icon in contact us page
    And user is able to see linkedin icon in contact us page
    Then end of test cases
    
 #Harshitha   
 @tag3
 Scenario: Validate Customer Experience section in About Us page
    Given browser is open
    When User is on Home page
    And user clicks on About us in the header 
    Then User is on About Us Page
    And user scrolls to Customer Experience section
    Then validate customer experience content 
    And Validate user is able to see the image beside the content
    Then End of the test
  