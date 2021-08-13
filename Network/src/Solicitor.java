/*
 * Solitcitor.java
 * 
 * Version:
 * $Id: Solicitor.java,v 1.2 2014/12/08 16:27:46 lxs8146 Exp $
 * Revisions:
 * $Log: Solicitor.java,v $
 * Revision 1.2  2014/12/08 16:27:46  lxs8146
 * Third revision: Fixed a bug and added comments
 *
 * Revision 1.1 2014/12/08 15:53:26 lxs8146
 * Second revision: Corrected spelling (changed Solitcitor to Solicitor)
 * 
 * Revision 1.1 2014/12/08 15:48:16 lxs8146
 * Initial revision: Initial implementation (untested)
 */

import java.net.*;
import java.io.*;

import java.util.Scanner;

/**
 * Class definition for a client that reads the contents of a file on a server.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Solicitor {

    /**
     * Connect to a specific port of a specific server. Write the desired
     * filename to the server and read the file's contents.
     * 
     * @param args Command line arguments -- [server name] [port]
     */
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(args[0]);
            Socket socket = new Socket(address,Integer.parseInt(args[1]));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            System.out.println(reader.readLine());
            Scanner scanner = new Scanner(System.in);
            writer.println(scanner.nextLine());
            scanner.close();
            Thread.sleep(1000);
            while ( reader.ready() ) {
                System.out.println(reader.readLine());
            }
            socket.close();
            return;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
