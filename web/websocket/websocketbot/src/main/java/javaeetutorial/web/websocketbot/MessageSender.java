package javaeetutorial.web.websocketbot;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MessageSender {
    private static final Logger logger = Logger.getLogger("MessageSender");
    /* Forward a message to all connected clients
         * The endpoint figures what encoder to use based on the message type */
    public static synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }
}
