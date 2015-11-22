package ua.dn.effect.ilg.agentappsunrise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ua.dn.effect.ilg.agentappsunrise.data.model.Notification;
import ua.dn.effect.ilg.agentappsunrise.util.ApplicationState;

public class NotificationsActivity extends Activity {

    private Activity ctx;
    private ListView lvNotifications;
    private SimpleAdapter adapter;
    private List<Notification> notificationsList;
    private ArrayList<HashMap<String, String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_list);
    }

    private void Init() {
        ctx = this;
        lvNotifications = (ListView)findViewById(R.id.listViewNotifications);
        notificationsList = AgentApplication.notificationsList;

        list.clear();
        for (Notification n: notificationsList){
            list.add(item);
        }

        adapter = new SimpleAdapter(this, list, R.layout.notifications_list_item,
                new String[] {"SubTitle", "CreatedDate"},
                new int[] {
                        R.id.textViewNotificationSubTitle,
                        R.id.textViewNotificationCreatedDate });
        lvNotifications.setAdapter(adapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Init();
    }

    @Override
    public void onBackPressed() {
        AgentApplication.state = ApplicationState.NAN;
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
