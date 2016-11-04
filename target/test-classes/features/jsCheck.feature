Feature: Checking web elements for color and size with js on and on different browsers 

@test01 
  Scenario: I check elements for color and size with js on 
    To be tested with firefox chrome and phantomJS js enabled only.
    
    Given I go to http://www.klockia.se/ 
    When I click on “KLOCKOR” button 
    And mousover on “Calvin Klein City Chrono” watch 
    Then I validate that “KÖP” background color is equal to "#029f8b" 
    And “LÄS MER” background color is equal to "#000000" 
    And padding of elements is equal to "20px"
