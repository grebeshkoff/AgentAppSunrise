package ua.dn.effect.ilg.agentappsunrise.data.reader;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.dn.effect.ilg.agentappsunrise.data.model.DataFile;
import ua.dn.effect.ilg.agentappsunrise.data.model.DataFileType;
import ua.dn.effect.ilg.agentappsunrise.data.model.Notification;
import ua.dn.effect.ilg.agentappsunrise.data.store.Storage;
import ua.dn.effect.ilg.agentappsunrise.data.store.StorageException;

/**
 * Created by LittleN on 11/22/2015.
 */
public class NotificationsReader {

    private List<File> notificationsFileCollection = new ArrayList<>();

    private Storage storage;
    private Context ctx;

    public NotificationsReader(Storage s) throws StorageException {
        storage = s;
        String path = storage.getDictionariesTextFolder().getAbsolutePath() + "/";
        File f = new File(path);

        ArrayList<File> files = new ArrayList<>(Arrays.asList(f.listFiles()));

        for(File file : files){
            DataFile df = new DataFile(file);
            if (df.type == DataFileType.NOTIFICATION){
                notificationsFileCollection.add(file);
            }
        }
    }

    public List<Notification> getList(){
        List<Notification> list = new ArrayList<>();

        for(File file : notificationsFileCollection){
            Notification notification  = new Notification(file);
            list.add(notification);
        }
        return list;
    }
}
