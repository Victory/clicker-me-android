package victory.sandbox.mytestapp;

import android.os.Looper;
import android.test.mock.MockContext;
import android.view.MotionEvent;
import android.view.ViewStub;

import junit.framework.TestCase;

public class SwipeDetectorTest extends TestCase {

    private ViewStub mockView;
    private int x;
    private int y;
    MotionEvent event;
    SwipeDetector swipeDetector;

    public void setUp() throws Exception {
        super.setUp();

        class MyMockContext extends MockContext {
            public Looper getMainLooper() {
                return Looper.getMainLooper();
            }
        }
        MyMockContext context = new MyMockContext();
        x = 0; y = 0;
        mockView = new ViewStub(context);
        swipeDetector = new SwipeDetector();
    }

    public void testHorizontal() throws Exception {

        // run a RightToLeft Swipe
        x = (int) swipeDetector.getMinMotion();
        y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_DOWN, x, y, 0);
        assertFalse(swipeDetector.onTouch(mockView, event));
        x = 0; y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_MOVE, x, y, 0);
        boolean isAction = swipeDetector.onTouch(mockView, event);
        assertTrue(isAction);
        boolean result = swipeDetector.getSwipeHorizontal().equals(SwipeDetector.Action.RightToLeft);
        assertTrue(result);

        // run a too small swipe
        x = 0; y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_DOWN, x, y, 0);
        isAction = swipeDetector.onTouch(mockView, event);
        assertFalse(isAction);

        x = (int) swipeDetector.getMinMotion() - 1;
        y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_MOVE, x, y, 0);
        isAction = swipeDetector.onTouch(mockView, event);
        assertFalse(isAction);

        // run LeftToRight
        x = 0; y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_DOWN, x, y, 0);
        isAction = swipeDetector.onTouch(mockView, event);
        assertFalse(isAction);
        x = (int) swipeDetector.getMinMotion();
        y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_MOVE, x, y, 0);
        isAction = swipeDetector.onTouch(mockView, event);
        assertTrue(isAction);
        result = swipeDetector.getSwipeHorizontal().equals(SwipeDetector.Action.LeftToRight);
        assertTrue(result);

    }

    public void testVertical() throws Exception {

        // run a DownToUp Swipe
        x = 0;
        y = (int) swipeDetector.getMinMotion();
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_DOWN, x, y, 0);
        assertFalse(swipeDetector.onTouch(mockView, event));
        x = 0; y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_MOVE, x, y, 0);
        boolean isAction = swipeDetector.onTouch(mockView, event);
        assertTrue(isAction);
        boolean result = swipeDetector.getSwipeVertical().equals(SwipeDetector.Action.DownToUp);
        assertTrue(result);

        // run a UpToDown Swipe
        x = 0; y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_DOWN, x, y, 0);
        assertFalse(swipeDetector.onTouch(mockView, event));
        x = 0;
        y = (int) swipeDetector.getMinMotion();
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_MOVE, x, y, 0);
        isAction = swipeDetector.onTouch(mockView, event);
        assertTrue(isAction);
        result = swipeDetector.getSwipeVertical().equals(SwipeDetector.Action.UpToDown);
        assertTrue(result);

    }

    public void testOnTouch() throws Exception {
        x = 0; y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_DOWN, x, y, 0);
        assertFalse(swipeDetector.onTouch(mockView, event));

        x = 90; y = 0;
        event = MotionEvent.obtain(1, 1, MotionEvent.ACTION_MOVE, x, y, 0);

        boolean isAction = swipeDetector.onTouch(mockView, event);
        assertTrue(isAction);

        boolean result =
                swipeDetector.getSwipeHorizontal().equals(SwipeDetector.Action.LeftToRight);
        assertTrue(result);

        // check we don't have a stray vertical action
        result = swipeDetector.getSwipeVertical().equals(SwipeDetector.Action.None);
        assertTrue(result);

        // start a new event
        x = 0; y = 0;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_DOWN, x, y, 0);
        swipeDetector.onTouch(mockView, event);
        x = 0; y = 90;
        event = MotionEvent.obtain(1, 0, MotionEvent.ACTION_MOVE, x, y, 0);
        isAction = swipeDetector.onTouch(mockView, event);
        assertTrue(isAction);
        result = swipeDetector.getSwipeVertical().equals(SwipeDetector.Action.UpToDown);
        assertTrue(result);
    }

    public void testGetSetMinMotion() throws Exception {
        swipeDetector.setMinMotion(30);
        assertTrue(swipeDetector.getMinMotion() == 30);
    }
}