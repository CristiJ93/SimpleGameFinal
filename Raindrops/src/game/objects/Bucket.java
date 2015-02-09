package game.objects;

import com.badlogic.gdx.math.Rectangle;

public class Bucket {

    private Rectangle bucket = new Rectangle();

    public Bucket(float x, float y, float width, float height) {
        bucket.x = x;
        bucket.y = y;
        bucket.width = width;
        bucket.height = height;
    }

    public void moveLeft(float value) {
        bucket.x -= value;
    }

    public void moveRight(float value) {
        bucket.x += value;
    }

    public void checkBounds(float width) {
        if (bucket.x < 0) {
            bucket.x = 0;
        }
        if (bucket.x > width - bucket.width) {
            bucket.x = width - bucket.width;
        }
    }

    public float getX() {
        return bucket.x;
    }

    public void setX(float x) {
        bucket.x = x;
    }

    public float getY() {
        return bucket.y;
    }

    public void setY(float y) {
        bucket.y = y;
    }

    public float getWidth() {
        return bucket.width;
    }

    public void setWidth(float width) {
        bucket.width = width;
    }

    public float getHeight() {
        return bucket.height;
    }

    public void setHeight(float height) {
        bucket.height = height;
    }

    public Rectangle getRectangle() {
        return bucket;
    }

}
