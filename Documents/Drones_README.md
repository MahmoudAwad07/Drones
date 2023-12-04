## Drones :
	this is a spring boot application to handle all the actions related to deliver medications to specific zones by drones 

## Modules :
	1. Zone : represents a geographical zone 
	2. Fleet : represents a group (One of more) Drone, and each Fleet serves one or more zone 
	3. Drone : represents the delivery tool that carry medications to deliver it to specific address in specific zone 
	4. Medication : represents a medication needs to be delivered 
	5. order : represents one or more of medications needs to be delivered to specific address.
	
## use Cases :
	1. the user creates a one zone or more , each zone that cover a geographical area
	2. the user update/delete/select the zones he created.
	3. the user creates one Fleet or moe , and indicate when creating the Fleet the zone that fleet serves.
	4. the user update/delete/select the fleets he created.
	5. the user creates drone, adn indicate which fleet this drone related to.
	6. the user update/delete/select the drones he created.
	7. the user creates medications.
	8. the user place order contains the address and zone and medications needed to be delivered
	9. the system will place the order and assign a drone to serve the order and deliver it.
	10. there are 2 periodic taks is running in the background oen to serve orders and assign idle drones to the orders and another one to check drones battery and recharge them.
	
## Request && Response 
	1. Json collection is shared in the documents floder. 
	2. Swagger Documention can be opened at http://localhost:8080/drones/swagger-ui/index.html#/
	
## Notes 
	1. application is lack of unit tests and proper logging due to time :)
	
	