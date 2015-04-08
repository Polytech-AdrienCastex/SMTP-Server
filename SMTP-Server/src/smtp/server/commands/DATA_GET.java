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
    
    private String implode(String[] array)
    {
        String result = this.getCommandName();
        for(String s : array)
            result += " " + s;
        return result;
    }
    
    @Override
    public String Run(String[] parameters, CommandResult cmdResult)
    {
        String data = implode(parameters);
        
        cmdResult.write(data);
        
        cmdResult.setExecutedWell(data.endsWith("\r\n.\r\n"));
        if(cmdResult.isExecutedWell())
            return "250 ...";
        else
            return null;
    }
}
