package ua.dn.effect.ilg.agentappsunrise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import ua.dn.effect.ilg.agentappsunrise.R;
import ua.dn.effect.ilg.agentappsunrise.data.model.Entity;
import ua.dn.effect.ilg.agentappsunrise.util.AgentAppLogger;

/**
 * User: igrebeshkov
 * Date: 08.11.13
 * Time: 9:49
 */
public class OrderPriceGroupEntryActivity extends Activity {
    Spinner spinnerPriceType;
    ListView listViewGroupEntry;
    Activity activity = this;
    TextView tvGroupName;
    SimpleAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.order_price_group_entry, null);
        setContentView(viewToLoad);

        spinnerPriceType = (Spinner) viewToLoad.findViewById(R.id.spinnerPriceType);
        listViewGroupEntry = (ListView) findViewById(R.id.listViewGroupEntry);
        tvGroupName = (TextView) findViewById(R.id.textViewGroupName);
        tvGroupName.setText("< " + AgentApplication.currentOrder.currentGroup.name + " >");
        tvGroupName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, OrderPriceActivity.class);
                //startActivity(intent);
                //replaceContentView("tag2", intent);

                //Intent intent = new Intent(activity, OrderPriceGroupEntryActivity.class);
                //OrderPriceActivityGroup.replaceContentView("tag2", intent);

                //startActivity(intent);
                View vw = OrderPriceActivityGroup.group.getLocalActivityManager()
                        .startActivity("tag2", intent
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView();
                OrderPriceActivityGroup.group.replaceView(vw);
                //activity.finish();

            }
        });


        FillPriceTypeSpinner();
        FillGroupEntryListView();
    }



    private void FillGroupEntryListView(){

        //adapter = new ArrayAdapter<Entity>(this, R.layout.order_price_entity_item, R.id.product_name, AgentApplication.currentOrder.currentGroup.entry);
        UpdateAdapter();
        listViewGroupEntry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Entity entity = AgentApplication.currentOrder.currentGroup.entry.get(position);
                AgentApplication.currentOrder.currentEntity = entity;

                AgentAppLogger.Text("!!!!!!!!!! " + entity.name);

                Intent intent = new Intent(activity, OrderPriceEntityActivity.class);
                //startActivity(intent);
                //Intent intent = new Intent(activity, OrderPriceGroupEntryActivity.class);
                //OrderPriceActivityGroup.replaceContentView("tag2", intent);

                //startActivity(intent);
                View v = OrderPriceActivityGroup.group.getLocalActivityManager()
                        .startActivity("tag2", intent
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        .getDecorView();
                OrderPriceActivityGroup.group.replaceView(v);
            }
        });

        listViewGroupEntry.setAdapter(adapter);
    }

    private void UpdateAdapter(){
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        for (Entity e :AgentApplication.currentOrder.currentGroup.entry){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Name", e.getNameWithHistory(AgentApplication.currentOrder.client.getId(), AgentApplication.currentOrder.currentGroup.id, AgentApplication.salesHistory));
            map.put("Price", String.valueOf(e.getPriceValue(spinnerPriceType.getSelectedItemPosition())));
            list.add(map);
        }


        adapter = new SimpleAdapter(this, list, R.layout.order_price_entity_item,
                new String[] {"Name", "Price"}, new int[] {R.id.textViewEntityName, R.id.textViewEntityPrice});
    }

    private void FillPriceTypeSpinner(){
        ArrayAdapter<String> comboAdapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        AgentApplication.priceList.priceTypes);

        comboAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPriceType.setPrompt("Вид цены");
        spinnerPriceType.setAdapter(comboAdapter);
        spinnerPriceType.setSelection(AgentApplication.priceList.currentPriseTypeId);
        //AgentApplication.priceList.currentPriseTypeId = spinnerPriceType.getSelectedItemPosition();

        spinnerPriceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                AgentApplication.priceList.currentPriseTypeId = position;
                UpdateAdapter();
                listViewGroupEntry.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getParent().getParent().setTitle(AgentApplication.currentOrder.getTotalSumm());
    }

}