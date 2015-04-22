
package smtp.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CommandResult
{
    public CommandResult()
    {
        executedWell = false;
        to = new ArrayList<>();
        writers = new ArrayList<>();
        
        this.cmdResultParent = null;
        this.fullInputCommand = null;
    }
    public CommandResult(CommandResult cmdResultParent, String fullInputCommand)
    {
        executedWell = false;
        
        this.to = cmdResultParent.to;
        this.writers = cmdResultParent.writers;
        this.cmdResultParent = cmdResultParent;
        this.fullInputCommand = fullInputCommand;
    }
    
    //*********** PROPERTIES
    private final CommandResult cmdResultParent;
    
    private boolean executedWell;
    
    private final String fullInputCommand;
    private String from;
    private final List<User> to;
    private final List<BufferedWriter> writers;
    //**********************
    
    
    //*********** ACCESSORS
    public String getFullInputCommand()
    {
        return fullInputCommand;
    }
    
    public void setFrom(String from)
    {
        this.from = from;
    }
    public String getFrom()
    {
        return from;
    }
    
    public void addTo(User to)
    {
        this.to.add(to);
    }
    public List<User> getTo()
    {
        return to;
    }
    
    public void openWriters()
    {
        to.forEach(u -> 
        {
            try
            {
                writers.add(new BufferedWriter(new FileWriter(u.getAvailableFile())));
            }
            catch(IOException ex)
            { }
        });
    }
    public void write(byte[] content)
    {
        try
        {
            write(new String(content, "UTF-8"));
        }
        catch(UnsupportedEncodingException ex)
        { }
    }
    public void write(String content)
    {
        writers.forEach(w -> 
        {
            try
            {
                w.write(content);
            }
            catch(IOException ex)
            { }
        });
    }
    
    public void close()
    {
        writers.forEach(w -> 
        {
            try
            {
                w.flush();
                w.close();
            }
            catch(IOException ex)
            { }
        });
    }
    
    public boolean isExecutedWell()
    {
        return executedWell;
    }
    public void setExecutedWell(boolean executedWell)
    {
        this.executedWell = executedWell;
        if(cmdResultParent != null)
            cmdResultParent.executedWell = executedWell;
    }
    //**********************
}
