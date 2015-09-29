package ua.dn.effect.ilg.agentappsunrise.data.exchenge;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import ua.dn.effect.ilg.agentappsunrise.AgentApplication;
import ua.dn.effect.ilg.agentappsunrise.util.AgentAppLogger;

/**
 * User: igrebeshkov
 * Date: 20.11.13
 * Time: 9:42
 */
public class FtpUploader {
    FTPClient ftp = null;
    FtpConnection connection = AgentApplication.ftpConnection;

    public void run(){
        try {

            //TODO Production

            ftp = new FTPClient();
            try  {
                Socket socket = new Socket(connection.Host, connection.Port);
            } catch (IOException ex) {
                return;
            }
            //ftp = new FTPHTTPClient("192.168.1.4", 3128, "igrebeshkov", "stivisokay");

            //ftp.setConnectTimeout(600000);
            ftp.setRemoteVerificationEnabled(false);

            ftp.connect(connection.Host, connection.Port);

            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            ftp.enterLocalPassiveMode();
            ftp.login(connection.UserName, connection.UserPassword);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            String orderPath = "/data/data/ua.dn.effect.ilg.agentappsunrise/files/orders";
            File ordersDir = new File(orderPath);
            boolean commandOK;

            for(File f : ordersDir.listFiles()){
                if(f.getName().endsWith("txt")){
                    try {
                        FileInputStream fis;
                        fis = new FileInputStream(f.getAbsolutePath());

                        OutputStream fos = this.ftp.storeFileStream(f.getName());
                        if (fos == null){
                            throw new Exception("OutputStream on ftp is null");
                        }

                        IOUtils.copy(fis, fos);
                        fos.flush();
                        IOUtils.closeQuietly(fos);
                        IOUtils.closeQuietly(fis);
                        commandOK = ftp.completePendingCommand();

                        String oldName = f.getAbsolutePath();
                        String newName = oldName + "_";

                        AgentAppLogger.Text("!!!!!!!!!!!!!!" +newName +" UPLOADED");
                        File newFile = new File(newName);
                        f.renameTo(newFile);
                    }catch (Exception e){
                        AgentAppLogger.Error(e);
                    }
                }
            }
            ftp.logout();
            ftp.disconnect();
            //AgentNotificationManager nm = new AgentNotificationManager()

        }catch (IOException e){
            AgentAppLogger.Error(e);
        }

    }
}
