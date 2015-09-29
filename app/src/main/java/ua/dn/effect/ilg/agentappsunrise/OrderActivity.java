package ua.dn.effect.ilg.agentappsunrise;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TabHost;

import ua.dn.effect.ilg.agentappsunrise.R;

/**
 * User: igrebeshkov
 * Date: 23.09.13
 * Time: 9:32
 */
public class OrderActivity extends TabActivity {

    Activity activity;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.order);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Реквизиты");
        tabSpec.setContent(new Intent(this, OrderHeaderActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Прайс");
        tabSpec.setContent(new Intent(this, OrderPriceActivityGroup.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("Заказ");
        tabSpec.setContent(new Intent(this, OrderContentActivityGroup.class));
        tabHost.addTab(tabSpec);

        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        setTitle(R.string.app_name);
    }
}