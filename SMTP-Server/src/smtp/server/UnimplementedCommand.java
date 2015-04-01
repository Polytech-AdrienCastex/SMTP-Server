/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smtp.server;

/**
 *
 * @author p1002239
 */
public class UnimplementedCommand extends Command
{
    public UnimplementedCommand(String name)
    {
        super(name, null);
    }
    
    @Override
    public String Run(String[] parameters, CommandResult cmdResult)
    {
        cmdResult.setExecutedWell(false);
        return "-ERR permission non accordée";
    }
}
