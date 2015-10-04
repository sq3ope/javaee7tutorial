package javaeetutorial.web.websocketbot;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell Latitude E5430 on 04.10.15.
 */
public class UserToolbox {
    /* Returns the list of users from the properties of all open sessions */
    public static List<String> getUserList(Session session) {
        List<String> users = new ArrayList<>();
        users.add("Duke");
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }
}
