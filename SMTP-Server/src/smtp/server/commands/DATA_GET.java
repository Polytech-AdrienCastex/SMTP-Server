package smtp.server.commands;

import java.util.stream.Stream;
import smtp.server.CommandOther;
import smtp.server.CommandResult;
import smtp.server.State;

public class DATA_GET extends CommandOther
{
    public DATA_GET(State nextState)
    {
        super(nextState);
    }
    
    @Override
    public String Run(String[] parameters, CommandResult cmdResult)
    {
        String data = cmdResult.getFullInputCommand();
        
        cmdResult.write(data);
        
        cmdResult.setExecutedWell(data.endsWith("\r\n.\r\n"));
        if(cmdResult.isExecutedWell())
            return "250 ...";
        else
            return null;
    }
}
