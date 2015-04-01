
package smtp.server.states;
import smtp.server.Command;
import smtp.server.State;
import smtp.server.commands.EHLO;
import smtp.server.commands.HELO;
import smtp.server.commands.QUIT;


public class ConnectedState extends State
{
    public ConnectedState()
    {
        ConfiguredState next = new ConfiguredState();
        
        Initialize(new Command[]
        {
            new HELO(next),
            new EHLO(),
            new QUIT()
        });
    }
}
