package smtp.server;

public abstract class CommandOther extends ICommand
{
    public CommandOther(State nextState)
    {
        super(null, nextState);
    }
    
    private String command;
    protected String getCommand()
    {
        return command;
    }
    
    @Override
    public boolean is(String cmd)
    {
        this.command = cmd;
        return true;
    }

    @Override
    public abstract String Run(String[] parameters, CommandResult cmdResult);
}
