package ua.dn.effect.ilg.agentappsunrise.data.model;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.Date;

import ua.dn.effect.ilg.agentappsunrise.util.AgentAppLogger;

/**
 * Created by LittleN on 11/21/2015.
 */
public class Notification {
    public String title;
    public String subTitle;
    public String message;
    public boolean read;
    public Date createdDate;
    public File notificationFile;

    public Notification(File nf){
        if (FilenameUtils.getExtension(nf.getName()).equals("htm")){
            AgentAppLogger.Error(new Exception("Notification - Constructor - Not supported extension"));
        }
        initialize(nf);
    }

    private void initialize(File file){
        notificationFile = file;
        createdDate = new Date(notificationFile.lastModified());
        title = "Test title";
        subTitle  = "Test subtitle";
        message  = "Test message";
        read = file.getName().startsWith("_");
    }
}
