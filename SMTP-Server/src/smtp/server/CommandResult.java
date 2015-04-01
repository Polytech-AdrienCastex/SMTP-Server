
package smtp.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandResult
{
    public CommandResult()
    {
        executedWell = false;
        to = new ArrayList<>();
        writers = new ArrayList<>();
        
        this.cmdResultParent = null;
    }
    public CommandResult(CommandResult cmdResultParent)
    {
        executedWell = false;
        to = new ArrayList<>();
        writers = new ArrayList<>();
        
        this.cmdResultParent = cmdResultParent;
    }
    
    //*********** PROPERTIES
    private final CommandResult cmdResultParent;
    
    private boolean executedWell;
    
    private String from;
    private final List<User> to;
    private final List<BufferedWriter> writers;
    //**********************
    
    
    //*********** ACCESSORS
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
