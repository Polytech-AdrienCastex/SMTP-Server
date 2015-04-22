/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smtp.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author p1002239
 */
public class Session implements Runnable
{
    public Session(Socket socket, State startState) throws IOException
    {
        this.socket = socket;
        socket.setSoTimeout(10000);
        
        ibs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        obs = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        currentState = startState;
        
        sessionID = s_sessionID++;
    }
    
    private final int sessionID;
    private static int s_sessionID = 1;
    
    private final Socket socket;
    
    private final BufferedReader ibs;
    private final BufferedWriter obs;
    
    private State currentState;
    
    public void close()
    {
        try
        {
            if(!socket.isClosed())
                socket.close();
        }
        catch (IOException ex)
        { }
    }
    
    @Override
    public void run()
    {
        char[] buffer = new char[2500];
        CommandResult sessionResult = new CommandResult();
        
        try
        {
            obs.write("220 Arnien-Server.com Service ready\r\n");
            obs.flush();
            System.out.println("[" + sessionID + "] Session started");
            
            while(true)
            {
                // Receive a string
                int len = ibs.read(buffer);
                if(len <= 0) // Client closed
                    break;
                String fullInput = String.valueOf(buffer, 0, len);

                // Clear the start and the end of the received string
                int nb = 0;
                while(fullInput.startsWith(" ", nb))
                    nb++;
                String str_cleaned = fullInput.substring(nb, fullInput.length() - 2 - nb);
                System.out.println("[" + sessionID + ":INPUT] \"" + fullInput + "\"");

                // Split the string
                int spaceIndex = fullInput.indexOf(" ");
                String cmd;
                String[] parameters;
                if(spaceIndex == -1)
                { // No space
                    cmd = str_cleaned;
                    parameters = new String[0];
                }
                else
                { // Space found
                    cmd = str_cleaned.substring(0, spaceIndex);
                    parameters = str_cleaned.substring(spaceIndex + 1).split(" ");
                }

                String msg = currentState.Run(cmd, parameters, fullInput, sessionResult);
                
                if(msg != null)
                {
                    System.out.println("[MSG] \"" + msg + "\"");
                    obs.write(msg + "\r\n");
                    obs.flush();
                }
                currentState = currentState.getNewState();
                
                if(currentState == null)
                    break; // Exit the session
            }
        }
        catch (Exception ex)
        { // Timeout
            System.out.println("[" + sessionID + "] Error : " + ex.getMessage());
        }
        finally
        {
            sessionResult.close();
        }
        
        System.out.println("[" + sessionID + "] Closing");
        close();
    }
}
