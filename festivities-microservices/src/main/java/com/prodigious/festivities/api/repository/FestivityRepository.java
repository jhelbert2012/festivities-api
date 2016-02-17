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
package com.prodigious.festivities.api.repository;

import com.prodigious.festivities.api.bean.Festivity;
import java.util.Date;
import java.util.List;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Festivity Repository, this class generates all the available REST services
 *
 * @author helbert
 */
@Api(description = "The books controller", name = "Books services")
@RepositoryRestResource(collectionResourceRel = "festivities", path = "festivities")
public interface FestivityRepository extends MongoRepository<Festivity, String> {

    /**
     * Find festivities by name
     *
     * @param name the name of the event
     * @return the list of festivities
     */
    @ApiMethod
    List<Festivity> findByName(@Param("name") String name);

    /**
     * Find festivities by place
     *
     * @param place the name of the place
     * @return the list of festivities
     */
    @ApiMethod
    List<Festivity> findByPlace(@Param("place") String place);

    /**
     * Find festivities by start date
     *
     * @param start the start date of the event
     * @return the list of festivities
     */
    @ApiMethod
    List<Festivity> findByStart(@Param("start") Date start);

    /**
     * Find festivities within start between start and end parameters
     *
     * @param start the start date of the range
     * @param end the end date  of the range
     * @return the list of festivities
     */
    @ApiMethod
    List<Festivity> findByStartBetween(@Param("start") Date start, @Param("end") Date end);

    /**
     * Deletes festivities by name
     *
     * @param name the name of the event
     * @return the list of festivities
     */
    @ApiMethod
    List<Festivity> removeByName(@Param("name") String name);

    /**
     * Find festivities within start date is after date parameter
     *
     * @param date the minimum date for the query.
     * @return the list of festivities
     */
    @ApiMethod
    List<Festivity> findByStartAfter(@Param("start") Date date);
}
