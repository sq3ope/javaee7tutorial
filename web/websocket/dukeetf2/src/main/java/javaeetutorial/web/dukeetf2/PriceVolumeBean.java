/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.dukeetf2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;

/* Updates price and volume information every second */
@Startup
@Singleton
public class PriceVolumeBean {
    /* Use the container's timer service */
    private Random random;
    private volatile double price = 100.0;
    private volatile int volume = 300000;
    private static final Logger logger = Logger.getLogger("PriceVolumeBean");
    
    @PostConstruct
    public void init() {
        /* Initialize the EJB and create a timer */
        logger.log(Level.INFO, "Initializing EJB.");
        random = new Random();
    }

    @Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void timerTick() {
        /* Adjust price and volume and send updates */
        price += 1.0*(random.nextInt(100)-50)/100.0;
        volume += random.nextInt(5000) - 2500;
        ETFEndpoint.send(price, volume);
    }
}
