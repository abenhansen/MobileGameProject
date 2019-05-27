package mygame.gameengine19.Invader;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mygame.gameengine19.GameEngine;

public class WorldRenderer {

    Bitmap shipImage;
    Bitmap beamImage;
    Bitmap enemyImage;
    Bitmap enemyBeamImage;
    Bitmap enemyBossImage;
    Bitmap enemyMiddle;
    Bitmap enemyBottom;
    Bitmap enemyHP;
    Bitmap bossBeam;
    Typeface font;
    GameEngine gameEngine;
    World world;
    int hpcounter;
    Random rand  = new Random();



    public WorldRenderer(GameEngine gameEngine, World world){
        this.gameEngine = gameEngine;
        this.world = world;
        shipImage = gameEngine.loadBitmap("InvadersAssets/spaceship.png");
        beamImage = gameEngine.loadBitmap("InvadersAssets/beam2.png");
        enemyImage = gameEngine.loadBitmap("InvadersAssets/enemy01.png");
        enemyBossImage = gameEngine.loadBitmap("InvadersAssets/enemybossTop.png");
        enemyMiddle = gameEngine.loadBitmap("InvadersAssets/enemybossMiddle.png");
        enemyBottom = gameEngine.loadBitmap("InvadersAssets/enemybossBottom.png");
        enemyBeamImage= gameEngine.loadBitmap("InvadersAssets/smallEnemybeam.png");
        bossBeam= gameEngine.loadBitmap("InvadersAssets/enemybeam.png");
        enemyHP= gameEngine.loadBitmap("InvadersAssets/Enemyhp.png");
        font = gameEngine.loadFont("InvadersAssets/Adore64.ttf");


    }

    public void render()
    {
        gameEngine.drawBitmap(shipImage,world.ship.x,world.ship.y);
        for (int i = 0; i < world.beamList.size(); i++){
            gameEngine.drawBitmap(beamImage, (int)world.beamList.get(i).x, (int)world.beamList.get(i).y);
        }
        for (int i = 0; i < world.enemyList.size(); i++){
            gameEngine.drawBitmap(enemyImage, world.enemyList.get(i).x, world.enemyList.get(i).y);
        }
            for (int i = 0; i < world.enemyBeamList.size(); i++){
                if(!world.enemyBoss.hasSpawned) {
                    gameEngine.drawBitmap(enemyBeamImage, (int) world.enemyBeamList.get(i).x, (int) world.enemyBeamList.get(i).y);
                }
                if(world.enemyBoss.hasSpawned) {
                    gameEngine.drawBitmap(enemyBeamImage, (int) world.enemyBeamList.get(i).x, (int) world.enemyBeamList.get(i).y);

//      gameEngine.drawBitmap(enemyBeamImage, (int) world.enemyBeamList.get(i).x, (int) world.enemyBeamList.get(i).y);

                }}
        for (int j = 0; j < world.enemyBeamList2.size(); j++) {
//            world.enemyBeamList.get(j).WIDTH = 14;
//            world.enemyBeamList.get(j).HEIGHT = 26;
            gameEngine.drawBitmap(bossBeam, (int) world.enemyBeamList2.get(j).x, (int) world.enemyBeamList2.get(j).y);
        }

        if(world.enemyList.size()==0){
            hpcounter = (256/world.enemyBoss.maxHP)*world.enemyBoss.lives;
            gameEngine.drawBitmap(enemyHP,160-(256/2),0,160-(256/2),0,hpcounter,32);
            gameEngine.drawText(font,"HP: "+world.enemyBoss.lives,130,20, Color.WHITE,8 );
            gameEngine.drawBitmap(enemyBossImage,world.enemyBoss.x,world.enemyBoss.y);
            gameEngine.drawBitmap(enemyMiddle,world.enemyBoss.x2, world.enemyBoss.y2);
            gameEngine.drawBitmap(enemyBottom,world.enemyBoss.x3,world.enemyBoss.y3);
//            for (int i = 0; i < world.enemyBeamList.size(); i++) {
//                gameEngine.drawBitmap(enemyBeamImage, (int) world.enemyBeamList.get(i).x, (int) world.enemyBeamList.get(i).y);
//            }
            world.enemyBoss.hasSpawned=true;

        }

    }

}
