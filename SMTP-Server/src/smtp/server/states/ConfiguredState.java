
package smtp.server.states;
import smtp.server.Command;
import smtp.server.State;


public class ConfiguredState extends State
{
    public ConfiguredState()
    {
        Initialize(new Command[]
        {
        });
    }
}
