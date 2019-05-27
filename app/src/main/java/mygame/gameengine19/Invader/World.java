package mygame.gameengine19.Invader;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mygame.gameengine19.GameEngine;

public class World {
    public static float MIN_X = 0;
    public static float MAX_X = 319;

    public static float MIN_Y = 36;
    public static float MAX_Y = 479;

    GameEngine gameEngine;
    Ship ship = new Ship();
//    int lives = 3;
    boolean gameOver = false;
    boolean lostLife = false;
    List<Beam> beamList = new ArrayList<>();
    int maxBeams = 1;
    List<Enemy> enemyList = new ArrayList<>();
    int maxEnemies = 5* (Enemy.WIDTH+2); //the amount of enemies
    List<EnemyBeam> enemyBeamList = new ArrayList<>();
    List<EnemyBeam> enemyBeamList2 = new ArrayList<>();
    float passedTime = 0;
    long startTime = System.currentTimeMillis();
    EnemyBoss enemyBoss = new EnemyBoss();
    CollisionListener collisionListener;


    public World(GameEngine gameEngine, CollisionListener collisionListener){
        this.collisionListener = collisionListener;
        this.gameEngine = gameEngine;
        spawnEnemies();
    }

    public void update(float deltaTime, float accelX, boolean isTouch, int touchX){

        ship.x = (int)(ship.x - accelX * 50 * deltaTime);

        if (ship.x < MIN_X){
            ship.x = (int)MIN_X;
        }
        if (ship.x + Ship.WIDTH > MAX_X){
            ship.x = (int)MAX_X - Ship.WIDTH;
        }
        collideEnemyBeam(enemyBeamList);
        collideEnemyBeam(enemyBeamList2);
        clickBeams(isTouch, deltaTime);
        collideBeamOnEnemy();
        collideBeamOnBoss();
        spawnEnemyBeams(deltaTime);
        if(enemyBoss.hasSpawned ) {
            spawnEnemyBossBeams(deltaTime);
        }

    }

    private void clickBeams(boolean isTouch, float deltaTime){
            if (isTouch){
                Beam beam = new Beam();
                beam.x = ship.x+7;
                beamList.add(beam);
            }
        Beam beam = null;
        for (int i=0; i<beamList.size(); i++) {
            beam = beamList.get(i);
            beam.y = beam.y + beam.vy * deltaTime;
        }

    }
    private void spawnEnemyBeams(float deltaTime){ ;
        passedTime += deltaTime;

//        if ((passedTime-(int)passedTime) > 0.93f ) {
        if (passedTime >= 1 ) {

            for (int i = 0; i < enemyList.size(); i++) {
                EnemyBeam enemyBeam = new EnemyBeam();
                enemyBeam.x = enemyList.get(i).x+10;
                enemyBeam.y = enemyList.get(i).y+20;
                enemyBeamList.add(enemyBeam);
                Log.d("world", passedTime + "");
                passedTime -=1f;
            }
        }
        EnemyBeam enemyBeam = null;
        for (int j=0; j<enemyBeamList.size(); j++) {
            enemyBeam = enemyBeamList.get(j);
            enemyBeam.y = enemyBeam.y + enemyBeam.vy * deltaTime;
        }

    }

    private void spawnEnemyBossBeams(float deltaTime){
        passedTime += deltaTime;
        Random rand = new Random();
        int beamType = rand.nextInt(3);
        if (passedTime >= 1) {
            EnemyBeam enemyBeam = new EnemyBeam();
            enemyBeam.x = enemyBoss.x3;
            enemyBeam.y = enemyBoss.y3;
            if (beamType==1){
                enemyBeamList.add(enemyBeam);

            }
            if (beamType==2){
                enemyBeam.WIDTH = 14;
                enemyBeam.HEIGHT = 26;
                enemyBeamList2.add(enemyBeam);

            }
            passedTime -=1f;
        }
        EnemyBeam enemyBeam = null;
        for (int i=0; i<enemyBeamList2.size(); i++) {
            enemyBeam = enemyBeamList2.get(i);
            enemyBeam.y = enemyBeam.y + enemyBeam.vy * deltaTime;
        }
    }

    private void spawnEnemies() {
        int x = 0;
        int y = 30;
        for (int i = 34; i <= maxEnemies; i+=34) {
            Enemy enemy = new Enemy(x+i,y);
            enemyList.add(enemy);
        }
    }


    private boolean collideRects(float x, float y, float width, float height,
                                 float x2, float y2, float width2, float height2 ) {
        if(x < x2+width2 && x + width > x2 && y < y2+height2 && y+height >y2) {
            return true;
        }
        return false;
    }

    private void collideBeamOnEnemy() {
        Enemy enemy = null;
        Beam beam = null;
        int totalBeams = beamList.size();
        for (int j = 0; j<beamList.size(); j++) {
            for (int i = 0; i < enemyList.size(); i++) {
            enemy = enemyList.get(i);
            beam = beamList.get(j);
                if (collideRects(beam.x, beam.y, Beam.WIDTH, Beam.HEIGHT, enemy.x, enemy.y, Enemy.WIDTH, Enemy.HEIGHT)) {
                enemyList.remove(i);
                Log.d("World", "beam just killed an enemy");
            }
        }
        }
    }


    private void collideEnemyBeam(List enemyBeamList) {
        EnemyBeam enemyBeam = null;
        for (int j = 0; j<enemyBeamList.size(); j++) {
                enemyBeam = (EnemyBeam) enemyBeamList.get(j);
                if (collideRects(ship.x, ship.y, Ship.WIDTH, Ship.HEIGHT, enemyBeam.x, enemyBeam.y, enemyBeam.WIDTH, enemyBeam.HEIGHT
                 )) {
                    ship.lives = ship.lives-1;
                    collisionListener.collisionShip();
                    if (ship.lives == 0) {
                        gameOver = true;

                    }
                    lostLife = true;
                    enemyBeamList.remove(j);
                    Log.d("World", "Player got hit by enemey and lost a life!");
                }
        }


    }

    public void enemyMovement(){

    }

    public void collideBeamOnBoss() {
        Beam beam = null;
        if (enemyBoss.hasSpawned) {
            for (int i = 0; i < beamList.size(); i++) {
                beam = beamList.get(i);
                if (collideRects(beam.x, beam.y, Beam.WIDTH, Beam.HEIGHT, enemyBoss.x, enemyBoss.y, EnemyBoss.WIDTH,
                        EnemyBoss.HEIGHT)||collideRects(beam.x, beam.y, Beam.WIDTH, Beam.HEIGHT, enemyBoss.x2, enemyBoss.y2,
                        EnemyBoss.WIDTH_Middle,
                        EnemyBoss.HEIGHT_Middle)|| collideRects(beam.x, beam.y, Beam.WIDTH, Beam.HEIGHT, enemyBoss.x3, enemyBoss.y3,
                        EnemyBoss.WIDTH_Bottom-20,
                        EnemyBoss.HEIGHT_Bottom-15)) {
                    enemyBoss.lives = enemyBoss.lives - 1;
                    if (enemyBoss.lives == 0) {
                        enemyBoss.bossDead = true;
                        gameOver=true;
                    }
                    beamList.remove(i);
                    Log.d("World", "beam just hit the boss");
                }

            }
        }
    }


}
