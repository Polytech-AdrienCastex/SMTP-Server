
package smtp.server.commands;

import smtp.server.Command;
import smtp.server.CommandResult;
import smtp.server.State;

public class MAIL extends Command
{
    public MAIL(State nextState)
    {
        super("mail", nextState);
    }
    
    @Override
    public String Run(String[] parameters, CommandResult cmdResult)
    {
        cmdResult.setExecutedWell(true);
        return "250 Requested mail action okay, completed";
    }
}
