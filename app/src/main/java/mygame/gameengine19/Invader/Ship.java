package mygame.gameengine19.Invader;

public class Ship {
    public static final int WIDTH = 48;
    public static final int HEIGHT =48;
    public int x = 0;
    public int y = 0;
    public int lives = 3;


    public Ship(){
        x = 160 - WIDTH/2;;
        y = (int)World.MAX_Y-HEIGHT;
    }

}
