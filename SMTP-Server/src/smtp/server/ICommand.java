package smtp.server;

public abstract class ICommand
{
    public ICommand(String name, State nextState)
    {
        this.name = name;
        if(this.name != null)
            this.name = name.trim().toLowerCase();
        
        this.nextState = nextState;
    }
    
    protected String name;
    private final State nextState;
    
    public abstract boolean is(String cmd);
    
    public String getCommandName()
    {
        return name;
    }
    public State getNextState()
    {
        return nextState;
    }

    public abstract String Run(String[] parameters, CommandResult cmdResult);
}
