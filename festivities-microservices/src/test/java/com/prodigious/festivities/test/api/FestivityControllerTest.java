/*
 * The MIT License
 *
 * Copyright 2016 helbert rico.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.prodigious.festivities.test.api;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.http.ContentType.JSON;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import com.prodigious.festivities.api.app.Application;
import com.prodigious.festivities.api.bean.Festivity;
import com.prodigious.festivities.api.repository.FestivityRepository;
import java.util.Arrays;
import java.util.Calendar;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author helbert
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FestivityControllerTest {

    @Autowired
    FestivityRepository repository;

    Festivity helbertFestivity;
    Festivity sofiaFestivity;
    Festivity momFestivity;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {

        helbertFestivity = new Festivity("Helbert's amazing days", "Helbert's home", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        sofiaFestivity = new Festivity("Sofia's trip days", "Santa Marta", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        momFestivity = new Festivity("Mom's vacations", "USA", Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        repository.removeByName(helbertFestivity.getName());
        repository.removeByName(sofiaFestivity.getName());
        repository.removeByName(momFestivity.getName());
        repository.save(helbertFestivity);
        repository.save(Arrays.asList(helbertFestivity, sofiaFestivity, momFestivity));
//        
        RestAssured.port = port;
    }

    @Test
    public void canAddNewFestivitie() {

        given().
                body("{ \"name\" : \"" + momFestivity.getName() + "\", \"place\" : \"" + momFestivity.getPlace() + "\"}").
                with().
                contentType(JSON).
                then().
                expect().
                statusCode(HttpStatus.SC_CREATED).
                when().
                post("/festivities");
    }

    @Test
    public void canFetchAll() {
        when().
                get("/festivities").
                then().
                statusCode(HttpStatus.SC_OK).
                body("_embedded.festivities.name", Matchers.hasItems("Craig's recognition", "Edna's event", "Clarence's event"));
    }

    @Test
    public void canFindByName() {
        when().
                get("/festivities/search/findByName?name={name}", "Clarence's event").
                then().
                statusCode(HttpStatus.SC_OK).
                body("_embedded.festivities.name", Matchers.hasItem("Clarence's event"));
    }

    @Test
    public void canFindByDates() {
        when().
                get("/festivities/search/findByStartBetween?start={start}&end={end}", "01/10/2016", "01/12/2016").
                then().
                statusCode(HttpStatus.SC_OK).
                body("_embedded.festivities.size()", equalTo(6));
    }

    @Test
    public void canDeleteHelbert() {
        Response res = get("/festivities/search/findByName?name={name}", "Helbert's amazing days");
        assertEquals(200, res.getStatusCode());
        String json = res.asString();
        System.out.println("json " + json);
        JsonPath jp = new JsonPath(json);
        String id = jp.get("_embedded.festivities[0].id");
        System.out.println("id " + id);

        delete("/festivities/{id}", id).
                then().
                statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
