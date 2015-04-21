
package smtp.server.states;
import smtp.server.command.Command;
import smtp.server.State;
import smtp.server.commands.EHLO;
import smtp.server.commands.HELO;
import smtp.server.commands.QUIT;


public class ConnectedState extends State
{
    public ConnectedState()
    {
        Initialize(new Command[]
        {
            new HELO(new WaitForSenderState()),
            new EHLO(),
            new QUIT()
        });
    }
}
