package smtp.server;

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
