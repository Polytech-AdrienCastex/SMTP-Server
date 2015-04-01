package smtp.server;

public abstract class Command
{
    public Command(String name, State nextState)
    {
        this.name = name.trim().toLowerCase();
        this.nextState = nextState;
    }
    
    private final String name;
    private final State nextState;
    
    public boolean is(String cmd)
    {
        return cmd.trim().toLowerCase().equals(name);
    }
    
    public State getNextState()
    {
        return nextState;
    }

    public abstract String Run(String[] parameters, CommandResult cmdResult);
}
