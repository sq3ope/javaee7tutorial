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

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.Session;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

/* Represents a chat message */
public class ChatMessage extends Message {
    private static final Logger logger = Logger.getLogger("JoinMessage");
    private String name;
    private String target;
    private String message;
    
    public ChatMessage(String name, String target, String message) {
        this.name = name;
        this.target = target;
        this.message = message;
    }
    
    public String getName() {
        return name;
    }
    
    public String getTarget() {
        return target;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    /* For logging purposes */
    @Override
    public String toString() {
        return "[ChatMessage] " + name + "-" + target + "-" + message;
    }

    @Override
    public void process(final Session session, ManagedExecutorService mes, final BotBean botbean) {
        /* Forward the message to everybody */
        logger.log(INFO, "Received: {0}", this.toString());
        MessageSender.sendAll(session, this);
        if (this.getTarget().compareTo("Duke") == 0) {
                /* The bot replies to the message */
            mes.submit(new Runnable() {
                @Override
                public void run() {
                    String resp = botbean.respond(getMessage());
                    MessageSender.sendAll(session, new ChatMessage("Duke",
                            getName(), resp));
                }
            });
        }

    }
}
