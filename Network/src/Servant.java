/*
 * Servant.java
 * 
 * Version:
 * $Id: Servant.java,v 1.2 2014/12/08 16:27:46 lxs8146 Exp $
 * Revisions:
 * $Log: Servant.java,v $
 * Revision 1.2  2014/12/08 16:27:46  lxs8146
 * Third revision: Fixed a bug and added comments
 *
 * Revision 1.1 2014/12/08 15:48:17 lxs8146
 * Initial revision: Initial implementation (untested)
 */

import java.net.*;
import java.io.*;

/**
 * Manages the connection between a server and a single client.
 * 
 * @author Lucas Stefanic (lxs8146)
 */

public class Servant extends Thread {

    private Socket socket;
    private InetAddress address;
    private int port;
    private String hostName;

    /**
     * Store the client's socket and get other data for testing and debugging.
     * 
     * @param s The client's socket
     */
    public Servant(Socket s) {
        socket = s;
        address = socket.getInetAddress();
        port = socket.getPort();
        hostName = address.getHostName();
    }

    /**
     * Prompt the client for a filename and write the file's contents to the
     * client.
     */
    public void run() {
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println("Enter a the name of a file you want to see");
            String fileName = reader.readLine();
            try {
                BufferedReader fileReader =
                        new BufferedReader(new FileReader(new File(fileName)));
                while ( fileReader.ready() ) {
                    writer.println(fileReader.readLine());
                }
                System.out.println("File transfer complete");
                fileReader.close();
                socket.close();
                return;
            } catch (FileNotFoundException e) {
                System.err.println("The file can't be opened");
                socket.close();
                return;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
