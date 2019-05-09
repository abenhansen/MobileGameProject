package mygame.gameengine19.Invader;
import mygame.gameengine19.GameEngine;
import mygame.gameengine19.Screen;

public class Invader extends GameEngine {

    @Override
    public Screen createStartScreen() {
//        music = this.loadMusic("carscroller/music.ogg");
        return new MainMenuScreen(this);
    }
    public void onResume(){
        super.onResume();
//        music.play();
    }

    public void onPause(){
        super.onPause();
//        music.stop();
//        music.pause();
    }
}

