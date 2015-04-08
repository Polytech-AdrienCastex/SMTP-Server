package smtp.server;

public abstract class State
{
    public void Initialize(ICommand[] commands)
    {
        _nextState = this;
        
        this.commands = commands;
    }
    
    //*********** NEXT STATE
    private State _nextState;
    protected void setNewState(State newState)
    {
        _nextState = newState;
    }
    public State getNewState()
    {
        return _nextState;
    }
    //**********************
    
    private ICommand[] commands;
    
    public String Run(String cmd, String[] parameters, CommandResult cmdResultGlobal)
    {
        for(ICommand c : commands)
        {
            if(c.is(cmd))
            {
                CommandResult cmdResult = new CommandResult(cmdResultGlobal);
                String result = c.Run(parameters, cmdResult);
                if(cmdResult.isExecutedWell())
                {
                    setNewState(c.getNextState());
                    System.out.println(":: Command received : " + cmd + " [SUCCESS]");
                }
                else
                    System.out.println(":: Command received : " + cmd + " [FAIL]");
                
                return result;
            }
        }
        
        System.out.println(":: Command received : " + cmd + " [NOT FOUND]");
        
        return "500 Syntax error, command unrecognized";
    }
}
