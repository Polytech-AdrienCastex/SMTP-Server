
package smtp.server.commands;

import smtp.server.command.Command;
import smtp.server.CommandResult;
import smtp.server.State;

public class DATA extends Command
{
    public DATA(State nextState)
    {
        super("data", nextState);
    }
    
    @Override
    public String Run(String[] parameters, CommandResult cmdResult)
    {
        cmdResult.openWriters();
        
        cmdResult.setExecutedWell(true);
        return "354 Start mail input; end with <CRLF>.<CRLF>";
    }
}
