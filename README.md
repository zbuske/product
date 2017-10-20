# Product Service

The Product service can retrieve data, or update pricing data, for a specified product, This data includes
* id
* name
* currency code
* value (price)

It is built using Spring Boot. The product data comes from an external API. 
The pricing data comes from a Mongo databse. Because it is a POC, 
and the Mongo database is embedded, any modified data will be lost when the application terminates.
It is only meant for testing at this point. I would use profiles. in application.yml for setting the database 
configurartion in a production system.

### Installation

* Clone this repo to your local computer
* cd into the repo directory
* `./gradlew clean build`

### Run the Service

There are two ways to run the application from the commandline
 * `./gradlew bootRun`
 * `java -jar build/libs/product-0.0.1-SNAPSHOT.jar`
 
(The first time you run it, it will download the embedded mongo dpendency. This may take some time.) 


### Using the Service

There are two endpoints to the service. The both have the following url"

`http://localhost:8080/com/myretail/products/${id}`

#### GET request.  
This will retieve a single product by its id. 

You can run that from any browser with the URL and product id.
#### PUT request. 
This will update the currency code and value in the ProductPrice that have changed. or create a new ProductPrice if it doesn't exist.
 
In addition to the  URL you will need a json payload. 
These are two ways of doing that:

* **Curl** (substitute the json with what you want to post)
 
 `curl -H 'Content-Type: application/json' -X PUT -d '{"id": 13860428,"name": "The Big Lebowski (Blu-ray)","productPrice": {"currencyCode": "USD","value": 13.99}}`
    
* **Postman**  
    * Choose a new PUT request
    * Click on Body and choose 'raw' and from the dropdown choose JSON(application/json)
    * In the Body Text field put the json that you are posting. 
    * Click Send

#### Note
To get the json format you need you can do  do a GET before a PUT. 
You can then use the json from the GET to do the PUT, 
changing the values you would like to update. You can reuse that json.

### Valid Product Ids
Only two of the product ids in the exercise description returned product data. 
I found a few more by trying the next and previous ones in sequence. 
This is the list of product ids supported by this application.

(The pricing data will not be available for any others that may return a product, but you could add it with a PUT)

13860428
13860427
13860429
16696651
16696652
16696650