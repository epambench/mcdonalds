Story: McDonalds performance

Meta:
@scope time testing

Scenario: Middle load 
When the incoming client number is 30 
Then the serve time will be 300

Scenario: Low load
When the incoming client number is 5
Then the serve time will be 50

Scenario: High load
When the incoming client number is 300 
Then the serve time will be 3000