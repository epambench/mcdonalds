Story: McDonalds working day with 1 consumer and 1 client

Meta:
@scope concurrency education

Narrative:
First working day of McDonalds. No clients, no hands :)

Scenario:
Given the McDonalds is about to open with director
And 1 consumer
And 1 client
When McDonalds opens
Then 1 client comes in
And 0 client awaiting outside

Scenario:
Given the McDonalds is about to open with director
And 1 consumer
And 1 client
When McDonalds opens
And 1 client comes in
Then 1 consumer takes 1 client
And 0 client awaiting outside
And 0 consumer waiting for client
And 1 consumer is working with 1 client

Scenario:
Given the McDonalds is about to open with director
And 1 consumer
And 1 client
When McDonalds opens
And 1 client comes in
And 1 consumer takes 1 client
And 1 consumer has processed 1 client
Then 0 client awaiting outside
And 1 consumer waiting for client
And 0 consumer is working with 0 client