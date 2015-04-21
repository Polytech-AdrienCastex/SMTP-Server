
package smtp.server.commands;

import smtp.server.command.UnimplementedCommand;

public class EHLO extends UnimplementedCommand
{
    public EHLO()
    {
        super("ehlo");
    }
}
