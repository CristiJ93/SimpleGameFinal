package game.objects;

import com.badlogic.gdx.math.Rectangle;

public class Raindrop {

    private Rectangle drop;

    public Raindrop(float x, float y, int width, int height) {
        drop = new Rectangle();
        drop.x = x;
        drop.y = y;
        drop.width = width;
        drop.height = height;
    }

    public boolean isOverlapping(Rectangle rectangle) {
        return drop.overlaps(rectangle);
    }

    public boolean isInRectangle(int width, int height) {
        return (drop.x > 0) && (drop.x < (width - drop.width))
                && ((drop.y + drop.height) > 0) && (drop.y < height);
    }

    public float getX() {
        return drop.x;
    }

    public void setX(float x) {
        drop.x = x;
    }

    public float getY() {
        return drop.y;
    }

    public void setY(float y) {
        drop.y = y;
    }

    public float getWidth() {
        return drop.x;
    }

    public void setWidth(float x) {
        drop.x = x;
    }

    public float getHeight() {
        return drop.y;
    }

    public void setHeight(float y) {
        drop.y = y;
    }

    public Rectangle getRectangle() {
        return drop;
    }
}
