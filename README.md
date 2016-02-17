# Festivities REST API

This REST API has been created following HATEOAS pattern, that means it should be self documented and you can navigate the entire API 
from http://localhost:8080/festivities Also its architecture is based on Spring Boot,
Spring Data and Spring Data Rest over a MongoDB repository.

# Configuration and Setup

For setup your local environment please follow steps bellow:

1. Execute git clone https://github.com/jhelbert2012/festivities-api.git
2. Go to the festivities-api folder.
3. Import data with: mongoimport --db festivities --collection festivity --drop --file resources/json/festivities.json
3. Execute cd festivities-microservices.
4. Execute mvn clean install
5. Download and install Mongo following https://www.mongodb.org/downloads
6. Execute mongod
7. Execute mvn spring-boot:run

That's it.

Now you can access http://localhost:8080/ and start navigating the Festivities API from here.

## ETL Extract, Transform and Load 

The provided XML was transformed to JSON using a online tool: http://www.utilities-online.info/xmltojson/#.VsP4yZMrLGI

## REST Services

### Create Festivities

curl -i -X POST -H "Content-Type:application/json" -d '{ "name" : "test", "place" : "home" ,"start": "2017-01-01", "end": "2018-02-02"}' http://localhost:8080/festivities

Start date before end date validation on creation 

curl -i -X POST -H "Content-Type:application/json" -d '{ "name" : "testwithout place","start": "2017-01-01", "end": "2018-02-02"}' http://localhost:8080/festivities

### Search

All the methods for search can be requested using your preferred browser. If you want better responses 
please install JSONVIew plugin. 

For testing find by name service use: 

http://localhost:8080/festivities/search/findByName?name=Clarence's event

For testing find by place service use:

http://localhost:8080/festivities/search/findByPlace?place=West's joint

You can test search by start in the browser with:

http://localhost:8080/festivities/search/findByStartAfter?start=2016/12/31

To test find by start service run:

http://localhost:8080/festivities/search/findByStartBetween?start=01/10/2015&end=01/10/2016

### Update:

For a complete update use:
curl -i -X PUT -H "Content-Type:application/json" -d '{ "name" : "Craig s recognition modified", "place" : "West s joint modified", "start": "2018-01-01", "end": "2018-02-28"}' http://localhost:8080/festivities/56c310328eedfa7195996026

Note: All the fields will be replaced, so if you don't send any field, that field will have null as value.

For a partial update use:

curl -i -X PATCH -H "Content-Type:application/json" -d '{ "place" : "West s joint modified (partially)"}' http://localhost:8080/festivities/56c310328eedfa7195996026

This will replace only the sent fields and maintain the others without modifications.

#TESTING

There are some integration tests for the API. Those test will start the context and execute direct calls to 
the REST API. For this you can run: 

mvn -Dtest=com.prodigious.festivities.test.api.FestivityControllerTest surefire:test

Or as easy as mvn clean build

