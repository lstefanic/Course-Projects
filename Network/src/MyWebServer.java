/*
 * MyWebServer.java
 * 
 * Version:
 * $Id: MyWebServer.java,v 1.3 2014/12/08 16:27:46 lxs8146 Exp $
 * Revisions:
 * $Log: MyWebServer.java,v $
 * Revision 1.3  2014/12/08 16:27:46  lxs8146
 * Third revision: Fixed a bug and added comments
 *
 * Revision 1.2 2014/12/08 15:53:26 lxs8146
 * Second revision: Corrected spelling (changed Solitcitor to Solicitor)
 * 
 * Revision 1.1 2014/12/08 15:48:16 lxs8146
 * Initial revision: Initial implementation (untested)
 */

import java.net.*;

/**
 * Class definition for a server that displays the contents of a file on a
 * client.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class MyWebServer {

    /**
     * Start the server
     * 
     * @param args Command line arguments -- unused
     */
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(0);
            System.out.println("Port: " + server.getLocalPort());
            while ( true ) {
                Socket client = server.accept();
                Servant servant = new Servant(client);
                servant.start();
                System.out.println(client);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
