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

public abstract class Message {

    public abstract void process(Session session, ManagedExecutorService mes, BotBean botbean);

}
