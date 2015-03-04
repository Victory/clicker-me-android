package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class MyActivity extends Activity {

    Button clickToList;
    ListView myListView;
    private ArrayAdapter<ListItemModel> modelAdapter;
    private ArrayList<ListItemModel> modelList;
    private int lastClickedItem;

    private int swipeNumber;

    private class SwipeItemTouchListener extends SwipeDetector {
        private long minTime = 0;
        private int debounceLength = 50;
        private SwipeDetector.Action lastAction = Action.None;

        private boolean isBounce () {
            long toc = System.currentTimeMillis();
            boolean result;

            if (toc < minTime) { // too soon
                result = true;
            } else {
                minTime = toc + debounceLength;
                result = false;
            }

            return result;
        }

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            super.onTouch(view, event);

            //if (!isBounce() && isLeftToRight()) {
                if (lastAction.equals(Action.None)) {
                    ListItemModel item = modelAdapter.getItem(lastClickedItem);
                    item.setMain("swiped");
                    modelAdapter.notifyDataSetChanged();
                    view.setBackgroundColor(0xffff0000);
                    swipeNumber += 1;
                }
            //}
            lastAction = swipeHorizontal;

            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        clickToList = (Button) findViewById(R.id.clickToList);
        myListView = (ListView) findViewById(R.id.listView);
        myListView.setTag("theWholeList");

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastClickedItem = position;
            }
        });

        
        
        modelList = new ArrayList<ListItemModel>();
        modelAdapter = new ModelListAdapter (
                this,
                R.layout.list_layout,
                R.id.listTextView,
                modelList,
                new SwipeItemTouchListener()
        );

        myListView.setAdapter(modelAdapter);

        clickToList.setOnClickListener(new View.OnClickListener() {
            Integer counter = 1;

            @Override
            public void onClick(View v) {
                ListItemModel model = new ListItemModel(
                        "Row: " + counter.toString() + " Swipe Number: " + swipeNumber);
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
