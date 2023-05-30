package com.bestbuy.crudtest;

import com.bestbuy.model.ServicePojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {

    //HashMap List<String, ?>> serviceList = new ArrayList<>();
    static String name = "Watford" + TestUtils.getRandomValue();
    static String type = "smallbox";
    static String address = "13513 Ridgedale Dr";
    static String address2 = "";
    static String city = "London";
    static String state = "Hertfordshire";
    static String zip = "WD25";
    static Double lat =  44.969658;
    static Double lng = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static String service = "{}";
    static int id;

    @Before
    public void setup(){
        RestAssured.basePath ="/stores";
    }
    @Test
    public void postStore(){
        StorePojo storePojo=new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        ServicePojo servicePojo = new ServicePojo();

        servicePojo.setName("service1");
        storePojo.setServices(servicePojo);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post();
        response.then().log().all().statusCode(201);


//        {
//
//            "name": "Watford",
//                "type": "SmallBox",
//                "address": "13513 Ridgedale Dr",
//                "address2": "",
//                "city": "Hopkins",
//                "state": "WD",
//                "zip": "WD25",
//                "lat": 44.969658,
//                "lng": -93.449539,
//                "hours": "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8",
//                "services":{}
//        }

        //Created Id 8923
    }
    //Patchdata
    @Test
    public void test002(){

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        ServicePojo servicesPojo = new ServicePojo();
        servicesPojo.setName("service1");
        storePojo.setServices(servicesPojo);
        // storePojo.setServices(serviceList);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch();
        response.then().log().all().statusCode(200);

    }
    //Get Data
    @Test
    public void test003(){

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get();
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
    //Delete Data
    @Test
    public void test004()
    {
        given()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(404);
    }
    }


