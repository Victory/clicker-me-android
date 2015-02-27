package victory.sandbox.mytestapp;


import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

    public static enum Action {
        None,
    }
    protected Action isSwipe = Action.None;

    // where the down and up action take place
    protected float downX, upX, downY, upY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            processActionDownEvent(event);
            isSwipe = Action.None;
        } else if (action == MotionEvent.ACTION_MOVE) {
            processActionMoveEvent(event);
        }
        return false;
    }

    private void processActionMoveEvent(MotionEvent event)
    {
        /* TODO
        upX = event.getX();
        upY = event.getY();
        */
    }

    private void processActionDownEvent (MotionEvent event)
    {
        downX = event.getX();
        downY = event.getY();
    }
}
