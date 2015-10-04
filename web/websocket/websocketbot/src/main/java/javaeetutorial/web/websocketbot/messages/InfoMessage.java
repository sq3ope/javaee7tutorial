/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.websocketbot.messages;

import javaeetutorial.web.websocketbot.BotBean;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.Session;

/* Represents an information message, like
 * an user entering or leaving the chat */
public class InfoMessage extends Message {
    
    private String info;
    
    public InfoMessage(String info) {
        this.info = info;
    }
    
    public String getInfo() {
        return info;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[InfoMessage] " + info;
    }

    @Override
    public void process(Session session, ManagedExecutorService mes, BotBean botbean) {

    }
}
