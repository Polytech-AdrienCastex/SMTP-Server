package smtp.server.command;

import smtp.server.CommandResult;
import smtp.server.ICommand;
import smtp.server.State;

public abstract class CommandOther extends ICommand
{
    public CommandOther(State nextState)
    {
        super(null, nextState);
    }
    
    @Override
    public boolean is(String cmd)
    {
        name = cmd;
        return true;
    }

    @Override
    public abstract String Run(String[] parameters, CommandResult cmdResult);
}
