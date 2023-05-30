package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest extends TestBase {

    static String name = "Amazone5" + TestUtils.getRandomValue();
    static String type = "Battery";
    static Double price= 5.99;
    static Double shipping = 1.99;
    static String description = "Amazone Basic Home Battery1" + TestUtils.getRandomValue();
    static String manufacturer = "Amazon China";
    static String model = "Amazon Basic 1.0";
    static String upc = "Text";
    static String url ="http://www.amazon.co.uk/battery";
    static String image = "http://www.amazon.co.uk/battery/imag";
    static int id;

    //{
//
//            "name": "Amazone Prime1",
//            "type": "Battery",
//            "price": 5.99,
//            "upc": "Text",
//            "shipping": 0,
//            "description": "Amazone Basic Home Battery",
//            "manufacturer": "Amazon China",
//            "model": "Amazon Basic 1.0",
//            "url": "http://www.amazon.co.uk/battery",
//            "image": "http://www.amazon.co.uk/battery/imag"
//  }

    @Before
    public void setUp(){

        RestAssured.basePath = "/products";
    }
    @Test
    public void postProduct(){

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post();
        response.then().log().all().statusCode(201);

    }
    @Test
    public void getProductData(){

        given()
                .when()
                .get("9999680")
                .then().statusCode(200)
                .log().all();
    }
    @Test
    public void deleteProduct(){

        given()
                .pathParams("id",id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(404);
    }



}
