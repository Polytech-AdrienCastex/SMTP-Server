
package smtp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import smtp.server.states.ConnectedState;

public class AcceptSession implements Runnable
{
    public AcceptSession(String serverName, ServerSocket serverSocket)
    {
        this.serverName = serverName;
        this.serverSocket = serverSocket;
    }

    private final String serverName;
    private final ServerSocket serverSocket;

    @Override
    public void run()
    {
        try
        {
            System.out.println("[SERVER] " + serverName + " started on port " + serverSocket.getLocalPort()); 
            
            while(true)
            { // Each client connected via TCP
                Socket client = serverSocket.accept();

                Session s = new Session(client, new ConnectedState());
                new Thread(s).start(); // Run the client session
            }
        }
        catch (IOException ex)
        {
            System.err.println("[ERROR] " + ex.getMessage());
        }
        
        System.err.println("[/!\\] " + this.serverName + " server stopped!");
    }
}
