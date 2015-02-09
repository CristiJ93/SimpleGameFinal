package game.simplegame.utils;

import com.badlogic.gdx.math.MathUtils;

import game.objects.Bucket;
import game.objects.Raindrop;
import game.objects.Score;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DropGenerator {

    private List<Raindrop> raindrops;
    private Score score;

    public DropGenerator(Score score) {
        this.score = score;
        raindrops = new LinkedList<Raindrop>();
    }

    public void addRandomDrop(int dropWidth, int dropHeight, int leftXLimit, int rightXLimit, int y) {
        int x = MathUtils.random(leftXLimit, rightXLimit);
        Raindrop drop = new Raindrop(x, y, dropWidth, dropHeight);
        raindrops.add(drop);
    }

    public List<Raindrop> getDrops() {
        return raindrops;
    }

    public void updateDrops(float speed, int screenW, int screenH, Bucket bucket) {
        Iterator<Raindrop> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Raindrop drop = iter.next();
            float newY = drop.getY();
            newY -= speed;
            drop.setY(newY);
            if (!drop.isInRectangle(screenW, screenH)) {
                score.decrementScore();
                iter.remove();
            }
            if (drop.isOverlapping(bucket.getRectangle())) {
                score.incrementScore();
                iter.remove();
            }
        }
    }

    public void resetDrops() {
        raindrops.clear();
    }

}
