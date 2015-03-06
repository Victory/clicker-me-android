package victory.sandbox.mytestapp;


import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

    public static enum Action {
        LeftToRight,
        RightToLeft,
        UpToDown,
        DownToUp,
        None,
    }

    private static class ActionPair {
        Action positive;
        Action negative;
        ActionPair (Action pos, Action neg) {
            positive = pos;
            negative = neg;
        }
    }

    private static final ActionPair verticalActions =
            new ActionPair(Action.UpToDown, Action.DownToUp);
    private static final ActionPair horizontalActions =
            new ActionPair(Action.LeftToRight, Action.RightToLeft);


    protected Action swipeVertical = Action.None;
    protected Action swipeHorizontal = Action.None;


    private float MIN_MOTION = 30;

    // where the down and up action take place
    protected float downX, upX, downY, upY, deltaX, deltaY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            processActionDownEvent(event);
        } else if (action == MotionEvent.ACTION_MOVE) {
            processActionMoveEvent(event);
        }
        return !swipeHorizontal.equals(Action.None) || !swipeVertical.equals(Action.None);
    }

    public float getMinMotion() {
        return MIN_MOTION;
    }

    public void setMinMotion(float MIN_MOTION) {
        this.MIN_MOTION = Math.abs(MIN_MOTION);
    }

    public Action getSwipeVertical() {
        return swipeVertical;
    }

    public Action getSwipeHorizontal() {
        return swipeHorizontal;
    }

    public boolean isRightToLeft () {
        return swipeHorizontal.equals(Action.RightToLeft);
    }

    public boolean isLeftToRight () {
        return swipeHorizontal.equals(Action.LeftToRight);
    }

    public boolean isUpToDown () {
        return swipeVertical.equals(Action.UpToDown);
    }

    public  boolean isDownToUp () {
        return swipeVertical.equals(Action.DownToUp);
    }


    private void processActionMoveEvent(MotionEvent event)
    {
        upX = event.getX();
        upY = event.getY();
        deltaX = upX - downX;
        deltaY = upY - downY;

        processHorizontal(deltaX);
        processVertical(deltaY);

    }

    private Action processDelta(float delta, ActionPair actions) {
        if (Math.abs(delta) < getMinMotion()) {
            return Action.None;
        } else if (delta > 0) {
            return actions.positive;
        } else {
            return actions.negative;
        }
    }

    private void processVertical(float deltaY) {
        swipeVertical = processDelta(deltaY, verticalActions);
    }

    private void processHorizontal(float deltaX) {
        swipeHorizontal = processDelta(deltaX, horizontalActions);
    }

    private void processActionDownEvent (MotionEvent event)
    {
        downX = event.getX();
        downY = event.getY();
        swipeVertical = Action.None;
        swipeHorizontal = Action.None;
    }
}
