package game.simplegame.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextWriter {

    private SpriteBatch batch;
    private BitmapFont font;

    public TextWriter(SpriteBatch batch) {
        font = new BitmapFont();
        this.batch = batch;

    }

    public void write(String text, int x, int y) {
        batch.begin();
        font.draw(batch, text, x, y);
        batch.end();
    }

    public void setColor(Color color) {
        font.setColor(color);
    }
}
