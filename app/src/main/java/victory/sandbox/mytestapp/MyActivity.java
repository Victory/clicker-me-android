package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;


public class MyActivity extends Activity {

    boolean isFirstTabClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab1");
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                // ignore the initial tabClick which happens automatically
                if (isFirstTabClick) {
                    isFirstTabClick = false;
                    return;
                }
                Toast.makeText(
                        getApplicationContext(),
                        "you clicked tab " + tabId,
                        Toast.LENGTH_SHORT).show();
            }
        });

        tabSpec.setContent(R.id.textView);
        tabSpec.setIndicator("First Tab");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setContent(R.id.textView2);
        tabSpec.setIndicator("Second Tab");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab3");
        tabSpec.setContent(R.id.textView3);
        tabSpec.setIndicator("Third Tab");
        tabHost.addTab(tabSpec);

        /*
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("First Tab");
        tabSpec.setContent(R.id.tab3);
        tabSpec.setIndicator("First Tab");
        */


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return (id == R.id.action_settings) || (super.onOptionsItemSelected(item));
    }
}
