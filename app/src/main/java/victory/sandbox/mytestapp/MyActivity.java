package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
    private int lastClickedItem;

    private int swipeNumber;

    private class SwipeItemTouchListener extends SwipeDetector {
        private long minTime = 0;
        private int debounceLength = 150;
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

            // debuging
            if (event.getAction() != MotionEvent.ACTION_DOWN &&
                    event.getAction() != MotionEvent.ACTION_MOVE) {
                view.setBackgroundColor(view.getResources().getColor(R.color.wow));
            }

            if (lastAction.equals(Action.LeftToRight)) {
                view.setBackgroundColor(view.getResources().getColor(R.color.black));
                if (event.getAction() != MotionEvent.ACTION_DOWN) {
                    return false;
                }
            }
            super.onTouch(view, event);

            if (Math.abs(deltaX) > 20) {
                view.setBackgroundColor(view.getResources().getColor(R.color.warning));
            }

            view.setPadding((int) deltaX, 0, 0, 0);

            if (event.getAction() == MotionEvent.ACTION_UP) {
                view.setBackgroundColor(view.getResources().getColor(R.color.black));
                view.setPadding(0, 0, 0, 0);
                return false;
            }

            if (isLeftToRight()) {
                if (lastAction.equals(Action.None)) {
                    lastClickedItem = (Integer) view.getTag();
                    ListItemModel item = modelAdapter.getItem(lastClickedItem);
                    modelAdapter.remove(item);
                    modelAdapter.notifyDataSetChanged();
                    swipeNumber += 1;
                    lastAction = swipeHorizontal;
                    minTime = System.currentTimeMillis() + 1500;
                }
                return true;
            }

            lastAction = Action.None;
            return true;
        }
    }

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
                new SwipeItemTouchListener()
        );

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
