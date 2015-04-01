
package smtp.server.commands;

import smtp.server.UnimplementedCommand;

public class EHLO extends UnimplementedCommand
{
    public EHLO()
    {
        super("ehlo");
    }
}
