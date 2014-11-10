Story: McDonalds working day with 1 consumer and 1 client

Meta:
@scope concurrency education

Narrative:
Clients like the food. More clients, still no hands :)

Scenario:
Given the McDonalds is about to open with director
And 1 consumer
And 10 client
When McDonalds opens
Then 1 client comes in
And 9 client awaiting outside

Scenario:
Given the McDonalds is about to open with director
And 1 consumer
And 10 client
When McDonalds opens
And 1 client comes in
Then 1 consumer takes 1 client
And 9 client awaiting outside
And 0 consumer waiting for client
And 1 consumer is working with 1 client

Scenario:
Given the McDonalds is about to open with director
And 1 consumer
And 10 client
When McDonalds opens
And 1 client comes in
And 1 consumer takes 1 client
And 1 consumer has processed 1 client
Then 1 consumer takes 1 client
And 8 client awaiting outside
And 0 consumer waiting for client
And 1 consumer is working with 1 client