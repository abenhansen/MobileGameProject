package mygame.gameengine19.Invader;

public class Beam {

//    public static float initialSpeed = 150;
    public static final int WIDTH = 32;
    public static final int HEIGHT =32;
    public float x = 160 - WIDTH/2+60;
    public float y = (int)World.MAX_Y-HEIGHT-40;
    public float vy = -150;

}
