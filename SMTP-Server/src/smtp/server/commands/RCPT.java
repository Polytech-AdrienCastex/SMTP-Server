
package smtp.server.commands;

import smtp.server.command.Command;
import smtp.server.CommandResult;
import smtp.server.State;
import smtp.server.User;

public class RCPT extends Command
{
    public RCPT(State nextState)
    {
        super("rcpt", nextState);
    }
    
    @Override
    public String Run(String[] parameters, CommandResult cmdResult)
    {
        try
        {
            String address = parameters[0].substring("TO:".length()).replace(">", "").replace("<", "").trim().split("@")[0];
            System.out.println("[MAIL TO] : " + address);
            User user = new User(address);
            
            if(!user.exists() || !user.canAccess())
            { // User exists
                cmdResult.setExecutedWell(false);
                return "550 Requested action not taken: mailbox unavailable";
            }
            else
            { // User doesn't exist
                cmdResult.addTo(user);

                cmdResult.setExecutedWell(true);
                return "250 Requested mail action okay, completed";
            }
        }
        catch(Exception ex)
        {
            cmdResult.setExecutedWell(false);
            return "501 Syntax error in parameters or arguments";
        }
    }
}
