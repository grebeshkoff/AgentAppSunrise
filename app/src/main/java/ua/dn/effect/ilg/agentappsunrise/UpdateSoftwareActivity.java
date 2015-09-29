package ua.dn.effect.ilg.agentappsunrise;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.File;

import ua.dn.effect.ilg.agentappsunrise.R;
import ua.dn.effect.ilg.agentappsunrise.util.AgentAppLogger;
import ua.dn.effect.ilg.agentappsunrise.util.UpdateApplication;

/**
 * User: igrebeshkov
 * Date: 12.11.13
 * Time: 16:49
 */
public class UpdateSoftwareActivity extends Activity {
    SoftUpdateTask task;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setFinishOnTouchOutside(false);

        setContentView(R.layout.update_software);

        task = (SoftUpdateTask) getLastNonConfigurationInstance();
        if(task == null){
            task = new SoftUpdateTask();
            task.execute();
        }
        task.link(this);
    }

    public Object onRetainNonConfigurationInstance() {
        task.unLink();
        return task;
    }

    @Override
    public void onBackPressed() {
    }

    public class SoftUpdateTask extends AsyncTask<Void, String, Void> {

        UpdateSoftwareActivity activity;

        void link (UpdateSoftwareActivity act){
            activity = act;
        }

        void unLink(){
            activity = null;
        }

        @Override
        protected Void doInBackground (Void... params) {
            UpdateApplication app = new UpdateApplication();
            app.setContext(getApplicationContext());

            try {
                app.Update("https://www.dropbox.com/s/rtpfe4wq7oo70f7/AgentApp.apk?raw=1");
            } catch (Exception e){
                AgentAppLogger.Error(e);
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File("/mnt/sdcard/Download/update.apk")), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
                activity.startActivity(intent);
            }catch (Exception e){
                AgentAppLogger.Error(e);
            }
        }
    }


}