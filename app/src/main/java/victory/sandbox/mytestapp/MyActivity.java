package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class MyActivity extends Activity {

    Button clickToList;
    ListView myListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        clickToList = (Button) findViewById(R.id.clickToList);
        myListView = (ListView) findViewById(R.id.listView);


        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        myListView.setAdapter(adapter);

        clickToList.setOnClickListener(new View.OnClickListener() {
            private Integer counter = 0;

            @Override
            public void onClick(View v) {
                arrayList.add("Some Cool String " + counter.toString());
                counter += 1;
                adapter.notifyDataSetChanged();
                myListView.setBackgroundColor(
                        getResources().getColor(android.R.color.background_dark));
            }
        });


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
