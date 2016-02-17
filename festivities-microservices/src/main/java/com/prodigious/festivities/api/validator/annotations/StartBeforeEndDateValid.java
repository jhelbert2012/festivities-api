/*
 * The MIT License
 *
 * Copyright 2016 Pivotal Software, Inc..
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
package com.prodigious.festivities.api.validator.annotations;

import com.prodigious.festivities.api.validator.StartBeforeEndDateValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Calendar;
import java.util.Date;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation for Java Bean Validations JSR 303. 
 * Evaluate if the start date is before than end date.
 *
 * @author helbert
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StartBeforeEndDateValidator.class)
@Documented
public @interface StartBeforeEndDateValid {

    /**
     * Message when the validation fails.
     * @return 
     */
    String message() default "{com.prodigious.validation.startdateshouldbegreaterthanenddate}";

    /**
     * Groups applied for the annotation.
     * @return Groups applied for the annotation.
     */
    Class<?>[] groups() default {};

    /**
     * Payload for the annotation
     * @return Payload for the annotation
     */
    Class<? extends Payload>[] payload() default {};
}
