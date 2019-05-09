package mygame.gameengine19.Invader;

import android.graphics.Bitmap;

import mygame.gameengine19.GameEngine;
import mygame.gameengine19.Music;
import mygame.gameengine19.Screen;

public class MainMenuScreen extends Screen {

    Bitmap background;
    Bitmap title;
    Bitmap startGame;
   public  long startTime;
     public float passedTime = 0;
    public static Music music;

    public MainMenuScreen(GameEngine gameEngine){
        super(gameEngine);
        music = this.gameEngine.loadMusic("InvadersAssets/music.ogg");
        music.play();
        title = gameEngine.loadBitmap("InvadersAssets/gametitle.png");
        background = gameEngine.loadBitmap("InvadersAssets/spacebackground.png");
        startGame = gameEngine.loadBitmap("InvadersAssets/startgame.png");
//        startTime = System.nanoTime();
                startTime = System.currentTimeMillis();

    }

    @Override
    public void update(float deltaTime) {
        if (gameEngine.isTouchDown(0) && (passedTime) >0.5f){
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }
        gameEngine.drawBitmap(background, 0 ,0);
        gameEngine.drawBitmap(title, 0,30);
        passedTime = passedTime + deltaTime;
        if ( (passedTime - (int)passedTime) > 0.5f) {
            gameEngine.drawBitmap(startGame,160 - startGame.getWidth()/2, 380);
        }
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void dispose() {
    music.dispose();
    }
}
