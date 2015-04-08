
package smtp.server.states;
import smtp.server.Command;
import smtp.server.State;
import smtp.server.commands.MAIL;
import smtp.server.commands.QUIT;


public class WaitForSenderState extends State
{
    public WaitForSenderState()
    {
        Initialize(new Command[]
        {
            new MAIL(new WaitForRecepterState()),
            new QUIT()
        });
    }
}
