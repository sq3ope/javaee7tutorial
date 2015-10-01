package javaeetutorial.web.dukeetf;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/dukeetf"}, asyncSupported = true)
public class DukeETFServlet extends HttpServlet {
    @EJB
    private PriceVolumeBean pvbean;

    private ConcurrentLinkedQueue<AsyncContext> requestQueue;
    private static final Logger logger = Logger.getLogger("DukeETFServlet");

    @Override
    public void init(ServletConfig config) throws ServletException {
        /* Queue for requests */
        requestQueue = new ConcurrentLinkedQueue<>();
        /* Register with the enterprise bean that provides price/volume updates */
        pvbean.registerServlet(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        /* Put request in async mode */
        final AsyncContext acontext = request.startAsync();

        /* Remove from the queue when done */
        acontext.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent ae) throws IOException {
                requestQueue.remove(acontext);
            }

            public void onTimeout(AsyncEvent ae) throws IOException {
                requestQueue.remove(acontext);
            }

            public void onError(AsyncEvent ae) throws IOException {
                requestQueue.remove(acontext);
            }

            public void onStartAsync(AsyncEvent ae) throws IOException {
            }
        });

        /* Add to the queue */
        requestQueue.add(acontext);
    }

    /* PriceVolumeBean calls this method every second to send updates */
    public void send(double price, int volume) {
        /* Send update to all connected clients */
        for (AsyncContext acontext : requestQueue) {
            try {
                String msg = String.format("%.2f / %d", price, volume);
                PrintWriter writer = acontext.getResponse().getWriter();
                writer.write(msg);
                logger.log(Level.INFO, "Sent: {0}", msg);
                /* Close the connection
                 * The client (JavaScript) makes a new one instantly */
                acontext.complete();
            } catch (IOException ex) {
                logger.log(Level.INFO, ex.toString());
            }
        }
    }
}
