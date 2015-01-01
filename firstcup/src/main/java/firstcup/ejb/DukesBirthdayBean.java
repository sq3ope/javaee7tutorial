/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package firstcup.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.logging.Logger;

/**
 * DukesBirthdayBean is a stateless session bean that calculates the age
 * difference between a user and Duke, who was born on May 23, 1995.
 */
@Stateless
public class DukesBirthdayBean {

    private static final Logger logger =
            Logger.getLogger("firstcup.ejb.DukesBirthdayBean");

    @PersistenceContext
    private EntityManager em;

    public Double getAverageAgeDifference() {
        // Insert code here
    }

    public int getAgeDifference(Date date) {
        // Insert code here
    }
}
