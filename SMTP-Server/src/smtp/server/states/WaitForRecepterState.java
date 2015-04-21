
package smtp.server.states;
import smtp.server.command.Command;
import smtp.server.State;
import smtp.server.commands.DATA;
import smtp.server.commands.QUIT;
import smtp.server.commands.RCPT;


public class WaitForRecepterState extends State
{
    public WaitForRecepterState()
    {
        Initialize(new Command[]
        {
            new RCPT(this),
            new DATA(new WaitForDataState(this)),
            new QUIT()
        });
    }
}
