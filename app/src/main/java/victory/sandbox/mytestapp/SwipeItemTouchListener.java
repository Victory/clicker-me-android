package victory.sandbox.mytestapp;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

class SwipeItemTouchListener extends SwipeDetector {
    private long minTime = 0;
    private int debounceLength = 150;
    private SwipeDetector.Action lastAction = Action.None;
    private int lastClickedItem;
    private ArrayAdapter<ListItemModel> modelAdapter;

    public int getSwipeNumber() {
        return swipeNumber;
    }

    private int swipeNumber = 0;

    public void setModelAdapter (ArrayAdapter<ListItemModel> modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

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

        if (Math.abs(deltaX) > 20) {
            view.setBackgroundColor(view.getResources().getColor(R.color.warning));
        }

        view.setPadding((int) deltaX, 0, 0, 0);

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