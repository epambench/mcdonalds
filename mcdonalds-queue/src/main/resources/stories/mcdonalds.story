Story: McDonalds working day

Meta:
@scope concurrency education


Scenario:
When more than 0 of the clients is free and more than 0 of consumers are available
Then 1 consumer takes 1 client 

Scenario:
When more than 0 of the clients is waiting for coming and there is an empty space for more than 0 client to come 
Then 1 client is coming 

Scenario: 
When 1 of the consumers is free and more than 0 clients are awaiting  
Then he must serve 1 client

Scenario: 
When one of the consumers take 1 client 
Then free space increased by 1


