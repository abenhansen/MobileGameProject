package mygame.gameengine19.Invader;

public class EnemyBoss {

    public static final int WIDTH = 277;
    public static final int HEIGHT = 71;
    public static final int WIDTH_Middle = 165;
    public static final int HEIGHT_Middle = 49;
    public static final int WIDTH_Bottom = 88;
    public static final int HEIGHT_Bottom = 25;
    public int x = 0;
    public int y = 0;
    public int x2 = 0;
    public int y2= 0;
    public int x3 = 0;
    public int y3 = 0;
    public int vx = 0;
    public int maxHP =100;
    public int lives = maxHP;
    boolean hasSpawned = false;
    boolean bossDead = false;

    public EnemyBoss(){
        x = 160 - WIDTH/2;;
        y = (int)World.MIN_Y;
        x2 = 160 - WIDTH_Middle/2;;
        y2 = (int)World.MIN_Y+HEIGHT;
        x3 = 160 - WIDTH_Bottom/2;;
        y3 = (int)World.MIN_Y+HEIGHT_Middle+HEIGHT;

    }
}
