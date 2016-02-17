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
package com.prodigious.festivities.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prodigious.festivities.api.validator.StartEndDateable;
import com.prodigious.festivities.api.validator.annotations.StartBeforeEndDateValid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

@StartBeforeEndDateValid
public class Festivity implements StartEndDateable {

    @Id
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String place;
    private Date start;
    private Date end;

    public Festivity() {
    }

    public Festivity(String name, String place, Date start, Date end) {
        this.name = name;
        this.place = place;
        this.start = start;
        this.end = end;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    @JsonIgnore
    public LocalDate getStartDate() {
        if (start == null) {
            return null;
        }
        return start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    @JsonIgnore
    public LocalDate getEndDate() {
        if (end == null) {
            return null;
        }
        return end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
