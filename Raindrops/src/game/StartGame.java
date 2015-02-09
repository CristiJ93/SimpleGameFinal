package game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.simplegame.GameScreen;
import static game.objects.Properties.WIDTH;

public class StartGame {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Drops";
        config.width = WIDTH;
        config.height = 500;
        config.useGL20 = false;
        config.resizable = true;

        new LwjglApplication(new GameScreen(), config);
    }
}