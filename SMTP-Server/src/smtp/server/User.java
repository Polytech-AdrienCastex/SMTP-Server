
package smtp.server;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class User
{
    private static final String USER_FOLDER = "emails_base";
    
    public User(String userName)
    {
        this.userName = userName;
        this.userFolder = getFolder(userName, extractPassword(userName));
        
        markedMsgId = new ArrayList<>();
    }
    
    private final String userName;
    private final File userFolder;
    
    private final List<Integer> markedMsgId;

    
    public String getUserName()
    {
        return userName;
    }
    
    public boolean exists()
    {
        return userFolder.exists();
    }
    
    public boolean canAccess()
    {
        return exists() && userFolder.canRead();
    }
    
    public void create()
    {
        if(!exists())
            userFolder.mkdir();
    }

    public String getAvailableFile()
    {
        String folder = userFolder.getAbsolutePath() + "/";
        
        int id = 0;
        while(new File(folder + id).exists())
            id++;
        
        return folder + id;
    }
    
    public static File getFolder(String userName, String password)
    {
        return new File(USER_FOLDER + "/" + userName.toLowerCase() + "@" + password);
    }
    public static boolean exists(String userName)
    {
        userName = userName.toLowerCase() + "@";
        
        File f = new File(USER_FOLDER);
        for(String dir : f.list())
            if(dir.startsWith(userName))
                return true;
        return false;
    }
    public static String extractPassword(String userName)
    {
        userName = userName.toLowerCase() + "@";
        
        File f = new File(USER_FOLDER);
        for(String dir : f.list())
            if(dir.startsWith(userName))
                return dir.substring(userName.length());
        return null;
    }
}
