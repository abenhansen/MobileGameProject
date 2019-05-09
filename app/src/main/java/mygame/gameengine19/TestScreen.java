package mygame.gameengine19;

import android.graphics.Bitmap;
import android.graphics.Color;

public class TestScreen extends Screen{

    float x  = 0;
    float y = 240;
    private Bitmap bitmap;
    Sound sound;
    Music backgroundMusic;
    boolean isPlaying = false;

    public TestScreen (GameEngine gameEngine) {
        super(gameEngine);
        bitmap = gameEngine.loadBitmap("BreakoutAssets/bob.png");
        sound = gameEngine.loadSound("BreakoutAssets/blocksplosion.wav");
        backgroundMusic = gameEngine.loadMusic("BreakoutAssets/music.ogg");
        isPlaying = true;
    }

    @Override
    public void update(float deltaTime) {
//        Log.d("TestScreen", "FPS: " + gameEngine.getFramesPerSecound());
        gameEngine.clearFrameBuffer(Color.RED);

        x = x+100* deltaTime;
        if(x>320+bitmap.getHeight()) x = 0-bitmap.getWidth();

//        for(int i =0; i<2; i++) {
//            if (gameEngine.isTouchDown(i)) {
//                x = gameEngine.getTouchX(i);
//                y = gameEngine.getTouchY(i);
//                sound.play(1);
//
//                if (backgroundMusic.isPlaying()) {
//                    backgroundMusic.pause();
//                    isPlaying = false;
//                } else {
//                    backgroundMusic.play();
//                    isPlaying = true;
//                }
//            }
//            gameEngine.drawBitmap(bitmap, x, y);
//        }

            if (gameEngine.isTouchDown(0)) {
                x = gameEngine.getTouchX(0);
                y = gameEngine.getTouchY(0);
                sound.play(1);

                if (backgroundMusic.isPlaying()) {
                    backgroundMusic.pause();
                    isPlaying = false;
                } else {
                    backgroundMusic.play();
                    isPlaying = true;
                }
            }


//            gameEngine.drawBitmap(bitmap, x, y);

//        float x = gameEngine.getAccelerometer()[0];
//        float y =-1* gameEngine.getAccelerometer()[1];
//        x = gameEngine.getFrameBufferWidth()/2 - ((x/10)* gameEngine.getFrameBufferWidth()/2);
//        y = gameEngine.getFrameBufferHeight()/2 - ((y/10)* gameEngine.getFrameBufferHeight()/2);
        gameEngine.drawBitmap(bitmap, (int) x-64, (int) y-64);

//        gameEngine.drawBitmap(bitmap, 200,300, 64,64, 64, 64);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
