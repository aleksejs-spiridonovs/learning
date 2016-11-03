Feature: Checking popup windows js on and on different browsers

@test02
  Scenario: I handle pop-up windows with js on
  To be tested with firefox chrome and phantomJS js enabled only.
  
    Given I go to http://www.klockia.se/
    When I click on “KLOCKOR” button
    And I click on “Marc By Marc Jacobs Baker” watch
    And on the new opened window click on Facebook logo
    And I log in with my facebook account
    And close Facebook window
    And press on “Stay” and validate that Facebook page is visible
    And press on “Leave” and validate that Facebook page is not visible