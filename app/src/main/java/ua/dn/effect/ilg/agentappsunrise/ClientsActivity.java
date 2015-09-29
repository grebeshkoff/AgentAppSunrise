package ua.dn.effect.ilg.agentappsunrise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ua.dn.effect.ilg.agentappsunrise.R;
import ua.dn.effect.ilg.agentappsunrise.data.model.Client;
import ua.dn.effect.ilg.agentappsunrise.data.reader.ClientsReader;
import ua.dn.effect.ilg.agentappsunrise.data.store.InternalStorage;
import ua.dn.effect.ilg.agentappsunrise.data.store.StorageException;
import ua.dn.effect.ilg.agentappsunrise.util.ApplicationState;

/**
 * User: igrebeshkov
 * Date: 26.09.13
 * Time: 11:36
 */
public class ClientsActivity extends Activity {

    Activity activity =this;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients);

        InternalStorage storage = null;
        try {
            storage = new InternalStorage(this);
        } catch (StorageException e) {
            e.printStackTrace();
        }

        ClientsReader reader = null;
        try {
            reader = new ClientsReader(storage);
        } catch (StorageException e) {
            e.printStackTrace();
        }

        AgentApplication.ClientsList = reader.getList();

        ListView lvMain = (ListView) findViewById(R.id.listViewClients);

        ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this,
                android.R.layout.simple_list_item_1, AgentApplication.ClientsList);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Client c = AgentApplication.ClientsList.get(position);
                if((AgentApplication.state == ApplicationState.ORDER_ADDING)||(AgentApplication.state == ApplicationState.ORDER_EDITING)){
                    AgentApplication.currentOrder.tempClient = c;
                }
                if((AgentApplication.state == ApplicationState.CHECK_ADDING)||(AgentApplication.state == ApplicationState.CHECK_EDITING)){
                    AgentApplication.currentCheck.tempClient = c;
                }
                Intent intent = new Intent(activity, ClientDetailActivity.class);
                startActivity(intent);
            }
        });

        lvMain.setAdapter(adapter);
    }
}