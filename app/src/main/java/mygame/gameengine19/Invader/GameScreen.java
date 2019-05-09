package mygame.gameengine19.Invader;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import mygame.gameengine19.GameEngine;
import mygame.gameengine19.Music;
import mygame.gameengine19.Screen;
import mygame.gameengine19.Sound;

public class GameScreen extends Screen {

    enum State {
        Paused,
        Running,
        GameOver,
    }
    State state = State.Running;
    Bitmap background;
    Bitmap paused;
    Bitmap pauseButton;
    Bitmap gameOver;
    Bitmap youWin;
    Sound hitSound;
    Typeface font;
    WorldRenderer renderer;
    World world;
     float passedTime = 0;
    public static Music music;



    public GameScreen(GameEngine gameEngine) {
        super(gameEngine);
        music = this.gameEngine.loadMusic("InvadersAssets/music.ogg");
        music.setLooping(true);
        music.play();
        background = gameEngine.loadBitmap("InvadersAssets/spacebackground.png");
        paused = gameEngine.loadBitmap("InvadersAssets/paused.png");
        gameOver = gameEngine.loadBitmap("InvadersAssets/gameOver.png");
        youWin = gameEngine.loadBitmap("InvadersAssets/youWin.png");
        pauseButton = gameEngine.loadBitmap("InvadersAssets/pause.png");
        font = gameEngine.loadFont("InvadersAssets/Adore64.ttf");
        hitSound = gameEngine.loadSound("InvadersAssets/hitsound.wav");

        world = new World(gameEngine,new CollisionListener() {
            @Override
            public void collisionShip() {
                hitSound.play(1);
            }

        });
        renderer = new WorldRenderer(gameEngine, world);
    }

    public void update(float deltaTime) {
        if (world.lostLife) {
            world.lostLife = false;
        }
        passedTime = passedTime + deltaTime;
        if (world.gameOver){
            state = State.GameOver;
        }
        if (state == State.GameOver)  {  //&gameEngine.isTouchDown(0))

                if (gameEngine.isTouchDown(0) && ( (passedTime - (int)passedTime) > 0.7f) ){
                    gameEngine.setScreen(new MainMenuScreen(gameEngine));
                    return;
            }

        }
        if (state == State.Paused && gameEngine.isTouchDown(0)){
            state = State.Running;
            resume();
        }


        if (state == State.Running && gameEngine.getTouchY(0)< 30 && gameEngine.getTouchX(0)> 320-33) {
            state = State.Paused;
            pause();
            return;
        }

        if (state == state.Running) {
            world.update(deltaTime, gameEngine.getAccelerometer()[0], gameEngine.isTouchDown(0), gameEngine.getTouchX(0));
        }
        gameEngine.drawBitmap(background,0,0);
        gameEngine.drawBitmap(pauseButton,270,0);
        renderer.render();
        gameEngine.drawText(font,"HP: "+ world.ship.lives,5,15, Color.GREEN,12);


        if (state == State.Paused){
            pause();
            gameEngine.drawBitmap(paused, 160- paused.getWidth()/2, 240- paused.getHeight()/2);

        }

        if (state == State.GameOver){
            pause();
            if(world.enemyBoss.bossDead){
                gameEngine.drawBitmap(youWin, 160-youWin.getWidth()/2, 240-youWin.getHeight()/2);
            }
            else {
                gameEngine.drawBitmap(gameOver, 160-gameOver.getWidth()/2, 240-gameOver.getHeight()/2);
            }
        }
    }

    @Override
    public void pause() {
        if (state == State.Running) state = State.Paused;
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
