## Sample microservices based application in Spring Boot


#### Infrastructure setup 

1. Active MQ should be installed and broker should be running on localhost at port `61616`
2. There should be one topic with name `microservice` should exist in activemq where different services can listen for events.

#### Application Setup

1. Create database according to `application.propeties` present in respective modules.
2. Provide database credential in `application.properties`
3. Populate databases using `sample_data.sql` present under `src/main/resources` in each module.
3. Build all services using `buildAll.sh`
4. Start all components using `startAll.sh`
5. Try to send following request to generate order:
 `  curl -i -X POST -H "Content-Type:application/json" -d '{ "productId":"1", "customerId": "1", "billingAddressId":"1","quantity":"15" }' http://localhost:9092/order
`
6. You should be able to see messages in log files generated in project root directory corresponding to each component.
7. Also , the shipment process automatically clears off all the shipments with status IN_TRANSIT and generates shipment success event.
8. 
