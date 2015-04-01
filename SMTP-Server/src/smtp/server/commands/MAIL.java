
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
        try
        {
            String address = parameters[0].substring("FROM:".length()).replace(">", "").replace("<", "").trim().split("@")[0];
            cmdResult.setFrom(address);

            cmdResult.setExecutedWell(true);
            return "250 Requested mail action okay, completed";
        }
        catch(Exception ex)
        {
            cmdResult.setExecutedWell(false);
            return "501 Syntax error in parameters or arguments";
        }
    }
}
