# festivities-api

mongoimport --db festivities --collection festivity --drop --file ~/NetBeansProjects/festivities-api/resources/json/festivities.json

curl -i -X POST -H "Content-Type:application/json" -d '{ "name" : "test", "place" : "home" ,"start": "2017-01-01", "end": "2018-02-02"}' http://localhost:8080/festivities

Testing validations

curl -i -X POST -H "Content-Type:application/json" -d '{ "name" : "testwithout place","start": "2017-01-01", "end": "2018-02-02"}' http://localhost:8080/festivities


You can test it using:

http://localhost:8080/festivities/search/findByStartAfter?start=2016/12/31

http://localhost:8080/festivities/search/findByStartBetween?start=01/10/2015&end=01/10/2016

Update:

Complete update:
curl -i -X PUT -H "Content-Type:application/json" -d '{ "name" : "Craig s recognition modified", "place" : "West s joint modified", "start": "2018-01-01", "end": "2018-02-28"}' http://localhost:8080/festivities/56c310328eedfa7195996026

Partial update:

curl -i -X PATCH -H "Content-Type:application/json" -d '{ "place" : "West s joint modified (partially)"}' http://localhost:8080/festivities/56c310328eedfa7195996026


http://localhost:8080/festivities/search/findByName?name=Clarence's event

http://localhost:8080/festivities/search/findByPlace?place=West's joint

#TESTING

mvn" -Dtest=com.prodigious.festivities.test.api.FestivityControllerTest surefire:test

