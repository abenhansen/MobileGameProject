package mygame.gameengine19;

public class TouchEvent {
    public enum TouchEventType {
        Down,
        Up,
        Dragged
    }

    public  TouchEventType type; //The type of the event
    public int x;                // The x-Coordinate of the event
    public int y;               // The y-Coordinate of the event
    public int pointer;         // The pointer id (from the Android system)


}
