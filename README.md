#Product Service

The Product service can retrieve data, or update pricing data, for a specified product, This data includes
* id
* name
* currency 
* value (price)

It is built using Spring Boot. The product data comes from an external API. 
The pricing data comes from an embedded Mongo databse. Because it is a POC, 
and the Mongo database is embedded, any modified data will be lost when the application terminates.
It is only meant for testing at this point. 

### Installation

* Clone this repo to your local computer
* cd into the repo directory
* `./gradlew clean build`

### Run the Service

There are two ways to run the application from the commandline
 * `./gradlew bootRun`
 * `java -jar build/libs/product-0.0.1-SNAPSHOT.jar`


### Using the Service

There are two endpoints to the service. The both have the following url"

`http://localhost:8080/com/myretail/products/${id}`

#### GET request.  
This will retieve a single product by its id. 

You can run that from any browser with the URL and product id.
#### PUT request. 
This will update any fields in the ProductPrice that hav changed.
 
In addition to the  URL you will need a json payload. 
These are two ways of doing that:

* **Curl** (substitute the json with what you want to post)
 
 `curl -H 'Content-Type: application/json' -X PUT -d '{"id": 13860428,"name": "The Big Lebowski (Blu-ray)","productPrice": { "id": "59e3b1fde6ca77286b82c7de","productId": 16696652,"currencyCode": "USD","price": 13.99}}' http://localhost:8080/com/myretail/products/13860429`
    
* **Postman**  
    * Choose a new PUT request
    * Click on Body and choose 'raw' and from the dropdown choose JSON(application/json)
    * In the Body Text field put the json that you are posting. 
    * Click Send

#### Note
Because the Mongo database reloads each time the application starts, it's important to go a GET before a PUT. 
The mongo id for the ProductPrice will change each time the application is started. You can use the json from the GET to do the PUT, 
changing the price values you would like to update.

### Valid Product Ids
Only two of the product ids in the exercise description returned product data. I found a few more by trying the next and previous ones in sequence. This is the list of product ids supported by this application.

(The pricing data will not be available for any others, but you could add it with a PUT)

13860428
13860427
13860429
16696651
16696652
16696650