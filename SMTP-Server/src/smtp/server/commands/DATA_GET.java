package smtp.server.commands;

import java.util.stream.Stream;
import smtp.server.command.CommandOther;
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
        
        cmdResult.setExecutedWell(data.endsWith("\r\n.\r\n"));
        
        if(cmdResult.isExecutedWell())
        {
            byte[] data_in = data.getBytes();
            byte[] data_result = new byte[data_in.length];
            System.arraycopy(data_in, 0, data_result, 0, data_in.length - 2);
            cmdResult.write(data_result);
        }
        else
            cmdResult.write(data);
        
        if(cmdResult.isExecutedWell())
        {
            cmdResult.close();
            return "250 Requested mail action okay, completed";
        }
        else
            return null;
    }
}
