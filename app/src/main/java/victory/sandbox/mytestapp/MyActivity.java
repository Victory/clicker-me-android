package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class MyActivity extends Activity {

    Button clickToList;
    ListView myListView;
    private ArrayAdapter<ListItemModel> modelAdapter;
    private ArrayList<ListItemModel> modelList;
    private SwipeItemTouchListener swipeItemTouchListener = new SwipeItemTouchListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        clickToList = (Button) findViewById(R.id.clickToList);
        myListView = (ListView) findViewById(R.id.listView);

        modelList = new ArrayList<ListItemModel>();
        modelAdapter = new ModelListAdapter (
                this,
                R.layout.list_layout,
                R.id.listTextView,
                modelList,
                swipeItemTouchListener
        );

        swipeItemTouchListener.setModelAdapter(modelAdapter);

        myListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        myListView.setAdapter(modelAdapter);

        clickToList.setOnClickListener(new View.OnClickListener() {
            Integer counter = 1;

            @Override
            public void onClick(View v) {
                ListItemModel model = new ListItemModel(
                        "Row: " + counter.toString() +
                        " Swipe Number: " + swipeItemTouchListener.getSwipeNumber());

                modelList.add(model);
                counter += 1;
                modelAdapter.notifyDataSetChanged();

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
