Feature: Testing Hotels

Scenario: Testing a status code
Given a hotel exists with the name Hotel Berlin
  When a user retrieves the hotel by the name Hotel Berlin
  Then the status code reads 200
 
Scenario: Testing both a status code and a field
Given a hotel exists with the name Hotel Berlin
  When a user retrieves the hotel by the name Hotel Berlin
  Then the status code reads 200
  And the Rating Field is equal to 5  
  
Scenario Outline: Testing the API using a table
Given a hotel exists with the name "<name>"
  When a user retrieves the hotel by the name "<name>"
  Then the status code reads 200
  And the rating Field is equal to "<rating>"
   
Examples:
    | name   | rating |
    | Hotel Frankfurt | 4 |
    | Hotel Cologne | 3 |
    