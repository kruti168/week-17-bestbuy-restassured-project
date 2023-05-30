package com.bestbuy.testsuite;


import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    //1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void extractStore() {
        String allStore = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of 5th store is : " + allStore);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the names of all the store
    @Test
    public void extractAllStore() {

        List<String> allStoreName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of all stores : " + allStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void extractAllStoreID() {
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all store name are : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
        //data[*].id
    }

    //6. Print the size of the data list
    @Test
    public void extractSize() {
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all store name are : " + allStoreId);
        System.out.println("The value of all store name are :" + allStoreId.size());
        System.out.println("------------------End of Test---------------------------");
        //data[*].id

    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void extractValue() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values of the store where store name are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void extractAddress() {
        String address1 = response.extract().path("data[8].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get the address of the store where store name = Rochester: " + address1);
        System.out.println("------------------End of Test---------------------------");

    }

    //9. Get all the services of 8th store
    @Test
    public void extract8Store() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get all the services of 8th store: " + services);
        System.out.println("------------------End of Test---------------------------");

    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void extractStoreService(){

        HashMap<String, ?> windowStore = response.extract().path("data[7].services[5].storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get storeservices of the store where service name = Windows Store " +windowStore);
        System.out.println("------------------End of Test---------------------------");

    }
    //11. Get all the storeId of all the store
    @Test
    public void extractAllStoreId(){
        List<Integer> StoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all store name are : " + StoreId);
     //   System.out.println("Get  all storeId are :" + StoreId.size());
        System.out.println("------------------End of Test---------------------------");

    }
    //12. Get id of all the store
    @Test
    public void extractAllIds() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all Ids of the store are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
        //data[*].storeId
    }
    //13. Find the store names Where state = ND
    @Test
    public void extractStoreName(){

        List<String> state = response.extract().path("data.findAll{it.state=='ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the store names Where state = ND " + state);
        System.out.println("------------------End of Test---------------------------");

    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void extractNumberServices() {

        List<String> totalServices = response.extract().path("data[8].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of services for the store where store name = Rochester " + totalServices.size());
        System.out.println("------------------End of Test---------------------------");

    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void extractAllServices(){

        List<String> allServices = response.extract().path("data.services[6].createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name = “Windows Store" + allServices);
        System.out.println("------------------End of Test---------------------------");

    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void extractStoreNameFargo(){

        List<String> storeFargo = response.extract().path("data.services[7]");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Find the name of all services Where store name = “Fargo”" + storeFargo);
        System.out.println("------------------End of Test---------------------------");


    }
    //17. Find the zip of all the store
    @Test
    public void extractZipStore(){
        List<String> zipStore = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store" + zipStore);
        System.out.println("------------------End of Test---------------------------");

    }
    //18. Find the zip of store name = Roseville
    @Test
    public void extractZipStoreRoseville(){
        String zipStore = response.extract().path("data[2].zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of store name = Roseville"   + zipStore);
        System.out.println("------------------End of Test---------------------------");

    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void verifyAllServiceForMangoliaHomeTheater () {
        List<String> allServices = response.extract().path("data.services[5].name");
        List<String> allServices1 = response.extract().path("data.services[7].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total service of Magnolia Home Theater  : " + allServices);
        System.out.println("The value of total service of Magnolia Home Theater  : " + allServices1);
        System.out.println("------------------End of Test---------------------------");
        //data[*].services[5].name

    }

    //20. Find the lat of all the stores

    @Test
    public void extract() {
        List<Double> lat = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }


}





