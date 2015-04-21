package smtp.server.command;

import smtp.server.CommandResult;
import smtp.server.ICommand;
import smtp.server.State;

public abstract class Command extends ICommand
{
    public Command(String name, State nextState)
    {
        super(name, nextState);
    }
    
    @Override
    public boolean is(String cmd)
    {
        return cmd.trim().toLowerCase().equals(getCommandName());
    }

    @Override
    public abstract String Run(String[] parameters, CommandResult cmdResult);
}
