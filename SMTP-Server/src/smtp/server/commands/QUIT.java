
package smtp.server.commands;

import smtp.server.command.Command;
import smtp.server.CommandResult;
import smtp.server.State;

public class QUIT extends Command
{
    public QUIT()
    {
        super("quit", null);
    }
    
    @Override
    public String Run(String[] parameters, CommandResult cmdResult)
    {
        cmdResult.setExecutedWell(true);
        return "221 Arnien-Server.com Service closing transmission channel";
    }
}
