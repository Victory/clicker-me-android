package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class MyActivity extends Activity {

    Button clickToList;
    ListView myListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    private class SwipeListener implements AdapterView.OnItemClickListener {
        private SwipeDetector swipeDetector = null;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (swipeDetector == null) {
                return;
            }
            swipeDetector.getSwipeHorizontal();
        }

        public void setSwipeDetector(SwipeDetector swipeDetector) {
            this.swipeDetector = swipeDetector;
        }

        public SwipeDetector getSwipeDetector () {
            return swipeDetector;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        clickToList = (Button) findViewById(R.id.clickToList);
        myListView = (ListView) findViewById(R.id.listView);

        SwipeListener swipeListener = new SwipeListener();
        swipeListener.setSwipeDetector(new SwipeDetector());

        myListView.setOnItemClickListener(swipeListener);
        myListView.setOnTouchListener(swipeListener.getSwipeDetector());

        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.list_layout,
                R.id.listTextView,
                arrayList);

        myListView.setAdapter(adapter);

        clickToList.setOnClickListener(new View.OnClickListener() {
            private Integer counter = 0;
            private int maxClicks = 50;

            @Override
            public void onClick(View v) {
                if (counter >= maxClicks) {
                    return;
                }
                arrayList.add("Row: " + counter.toString());
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
