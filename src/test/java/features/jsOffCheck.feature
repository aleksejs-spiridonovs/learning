Feature: Checking web elements for color and size with js off and on different browsers 

@test03 
  Scenario: I check elements for color and size with js off 
	To be tested with firefox and chrome js disabled only.
  
	Given I go to http://www.klockia.se/ 
	When I click on “KLOCKOR” button 
	And I click on "Calvin Klein City Chrono" watch 
	Then I validate that “KÖP” background color is equal to "#029f8b" 
	And padding of elements is equal to "20px" 
	And Facebook, Twitter logo is missing
