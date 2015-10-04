/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.websocketbot.messages;

import javaeetutorial.web.websocketbot.BotBean;
import javaeetutorial.web.websocketbot.MessageSender;
import javaeetutorial.web.websocketbot.UserToolbox;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.Session;
import java.util.concurrent.AbstractExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Represents a join message for the chat */
public class JoinMessage extends Message {
    private static final Logger logger = Logger.getLogger("JoinMessage");
    private String name;
    
    public JoinMessage(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[JoinMessage] " + name;
    }

    @Override
    public void process(Session session, ManagedExecutorService mes, BotBean botbean) {
        /* Add the new user and notify everybody */
        session.getUserProperties().put("name", this.getName());
        session.getUserProperties().put("active", true);
        logger.log(Level.INFO, "Received: {0}", this.toString());
        MessageSender.sendAll(session, new InfoMessage(this.getName() +
                " has joined the chat"));
        MessageSender.sendAll(session, new ChatMessage("Duke", this.getName(),
                "Hi there!!"));
        MessageSender.sendAll(session, new UsersMessage(UserToolbox.getUserList(session)));
    }
}
