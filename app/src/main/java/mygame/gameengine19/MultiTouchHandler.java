package mygame.gameengine19;

import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class MultiTouchHandler implements TouchHandler, View.OnTouchListener {
    private boolean[] isTouched = new boolean[20]; //store the first 20 touches
    private int[] touchX = new int[20];
    private int[] touchY = new int[20];

    private List<TouchEvent> touchEventBuffer; //buffer with touch events
    private TouchEventPool touchEventPool;    // pool with resuable objects

    public MultiTouchHandler(View v, List<TouchEvent> touchEventBuffer, TouchEventPool touchEventPool) {
        v.setOnTouchListener(this);
        this.touchEventBuffer = touchEventBuffer;
        this.touchEventPool = touchEventPool;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TouchEvent touchEvent = null;
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK ) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointerID = event.getPointerId(pointerIndex);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchEvent = touchEventPool.obtains();
                touchEvent.type = TouchEvent.TouchEventType.Down;
                touchEvent.pointer = pointerID;
                touchEvent.x = (int) event.getX();
                touchX[pointerID] = touchEvent.x;
                touchEvent.y = (int) event.getY();
                touchY[pointerID] = touchEvent.y;
                isTouched[pointerID] = true;
                synchronized (touchEventBuffer) {
                    touchEventBuffer.add(touchEvent);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
//                touchEvent = touchEventPool.obtains();
//                touchEvent.type = TouchEvent.TouchEventType.Up;
//                touchEvent.pointer = pointerID;
//                touchEvent.x = (int) event.getX();
//                touchX[pointerID] = touchEvent.x;
//                touchEvent.y = (int) event.getY();
//                touchY[pointerID] = touchEvent.y;
                isTouched[pointerID] = false;
//                synchronized (touchEventBuffer) {
//                    touchEventBuffer.add(touchEvent);
//
//                }
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                synchronized (touchEventBuffer) {
                    for (int i=0; i<pointerCount; i++) {
                        touchEvent = touchEventPool.obtains();
                        touchEvent.type = TouchEvent.TouchEventType.Dragged;
                        touchEvent.pointer = pointerID;
                        touchEvent.x = (int) event.getX();
                        touchX[pointerID] = touchEvent.x;
                        touchEvent.y = (int) event.getY();
                        touchY[pointerID] = touchEvent.y;
                        isTouched[pointerID] = false;
                        touchEventBuffer.add(touchEvent);
                    }
                }
//                isTouched[pointerID] = true;
                break;

        }

        return true; // telling the Android system I did handle this onTouch evnet!
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return isTouched[pointer];
    }


    @Override
    public int getTouchX(int pointer) {
        return touchX[pointer];
    }

    @Override
    public int getTouchY(int pointer) {
        return touchY[pointer];
    }


}
