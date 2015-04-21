
package smtp.server.states;
import smtp.server.command.Command;
import smtp.server.ICommand;
import smtp.server.State;
import smtp.server.commands.DATA_GET;


public class WaitForDataState extends State
{
    public WaitForDataState(State parent)
    {
        Initialize(new ICommand[]
        {
            new DATA_GET(parent)
        });
    }
}
