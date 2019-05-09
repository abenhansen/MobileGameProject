package mygame.gameengine19;

public class TouchEventPool extends Pool<TouchEvent> {

    @Override
    protected TouchEvent newItem() {
        return new TouchEvent();
    }
}
