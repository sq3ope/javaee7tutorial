package javaeetutorial.web.dukeetf;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Random;

@Startup
@Singleton
public class PriceVolumeBean {
    /* Use the container's timer service */
    private DukeETFServlet servlet;
    private Random random;
    private double price = 100.0;
    private int volume = 300000;

    @PostConstruct
    public void init() {
        /* Initialize the EJB and create a timer */
        random = new Random();
        servlet = null;
    }

    public void registerServlet(DukeETFServlet servlet) {
        /* Associate a servlet to send updates to */
        this.servlet = servlet;
    }

    @Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void timerTick() {
        /* Adjust price and volume and send updates */
        price += 1.0*(random.nextInt(100)-50)/100.0;
        volume += random.nextInt(5000) - 2500;
        if (servlet != null)
            servlet.send(price, volume);
    }
}
