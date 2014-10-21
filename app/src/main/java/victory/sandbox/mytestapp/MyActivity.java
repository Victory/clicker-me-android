package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    boolean isFirstTabClick = true;
    List<ListfulContent> listfulContent = new ArrayList<ListfulContent>();
    ListView listfulContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        populateListfulContent();
        ArrayAdapter<ListfulContent> mla = new MyListAdapter();
        listfulContentView = (ListView) findViewById(R.id.listView);
        listfulContentView.setAdapter(mla);

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

        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("First Tab");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tab2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Second Tab");
        tabHost.addTab(tabSpec);

        final Button clickToDoBtn = (Button) findViewById(R.id.clickToDoBtn);
        final EditText nameInput = (EditText) findViewById(R.id.nameInput);
        final EditText infoInput = (EditText) findViewById(R.id.infoInput);


        clickToDoBtn.setEnabled(false);

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String name = nameInput.getText().toString().trim();
                clickToDoBtn.setEnabled(!name.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        clickToDoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String info = infoInput.getText().toString().trim();


                ListfulContent lc = new ListfulContent(name, info);
                listfulContent.add(lc);

                nameInput.setText("");
                infoInput.setText("");

            }
        });

    }


    private void populateListfulContent () {
        ListfulContent lc = new ListfulContent("Joe", "I am good a putting stuff in things");
        listfulContent.add(lc);
    }
    private class MyListAdapter extends ArrayAdapter<ListfulContent>
    {
        public MyListAdapter() {
            super(MyActivity.this, R.layout.lisful_layout, listfulContent);
        }

        @Override
        public View getView(int ii, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.lisful_layout, viewGroup, false);
            }

            ListfulContent currentItem = listfulContent.get(ii);
            TextView name = (TextView) view.findViewById(R.id.listfulName);
            TextView info = (TextView) view.findViewById(R.id.listfulInfo);
            name.setText("Your Name: " + currentItem.getName());
            info.setText(currentItem.getInfo());

            return view;
        }
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
