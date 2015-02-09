package game.simplegame;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import game.objects.Bucket;
import game.objects.Raindrop;
import game.objects.Score;
import game.simplegame.utils.DropGenerator;
import game.simplegame.utils.GameStates;
import game.simplegame.utils.TextWriter;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static game.objects.Properties.*;
import static game.simplegame.utils.GameStates.*;

public class GameScreen implements ApplicationListener {

    private Texture dropImage;
    private Texture bucketImage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Bucket bucket;
    private DropGenerator dropFactory;
    private Score score;
    private TextWriter textWriter;
    private GameStates gameState = INIT;
    private float[] backgroundColor = {0, 0, 0.2f, 1};
    private long lastDropTime;
    private long dropInterval = INITIAL_DROP_INTERVAL;
    private int dropSpeed = INITIAL_DROP_SPEED;

    @Override
    public void create() {
        setUpImages();
        setUpCamera();
        batch = new SpriteBatch();
        textWriter = new TextWriter(batch);
        bucket = new Bucket(BUCKET_X, BUCKET_Y, BUCKET_WIDTH, BUCKET_HEIGHT);
        score = new Score();
        dropFactory = new DropGenerator(score);
        dropFactory.addRandomDrop(DROP_WIDTH, DROP_HEIGHT, LEFT_LIMIT, RIGHT_LIMIT, HEIGHT);
    }

    private void setUpImages() {
        dropImage = new Texture(Gdx.files.internal("resources/droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("resources/bucket.png"));
    }

    private void setUpCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
    }

    @Override
    public void render() {
        switch (gameState) {
            case INIT:
                initScreen();
                break;
            case RUN:
                updateGame();
                break;
            case GAME_OVER:
                endGame();
                break;
        }
    }

    public void initScreen() {
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        int x = WIDTH / 2 - 50;
        int y = HEIGHT / 2;
        textWriter.write("Click to start the game ", x, y);
        textWriter.write("Hold left click and move the mouse ", x, y - 20);
        if (Gdx.input.isTouched()) {
            gameState = RUN;
        }
    }

    public void updateGame() {
        clearGL();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        drawComponents();
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.setX(touchPos.x - bucket.getWidth() / 2);
        }
        bucket.checkBounds(WIDTH);
        updateRainDrops();
        updateSpeed(5);
        score.updateScore();
        if (score.isGameOver())
            gameState = GAME_OVER;
        textWriter.write("Score " + score.getCurrentScore() + " ", WIDTH - 120, HEIGHT - 20);
    }

    private void clearGL() {
        Gdx.gl.glClearColor(backgroundColor[0], backgroundColor[1], backgroundColor[2], backgroundColor[3]);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void drawComponents() {
        batch.begin();
        batch.draw(bucketImage, bucket.getX(), bucket.getY());
        Raindrop drop;
        for (int i = 0; i < dropFactory.getDrops().size(); i++) {
            drop = dropFactory.getDrops().get(i);
            batch.draw(dropImage, drop.getX(), drop.getY());
        }
        batch.end();
    }

    public void updateRainDrops() {
        if ((TimeUtils.nanoTime() - lastDropTime) > dropInterval) {
            dropFactory.addRandomDrop(DROP_WIDTH, DROP_HEIGHT, LEFT_LIMIT, RIGHT_LIMIT, HEIGHT);
            lastDropTime = TimeUtils.nanoTime();
        }
        float speed = dropSpeed * Gdx.graphics.getDeltaTime();
        dropFactory.updateDrops(speed, WIDTH, HEIGHT, bucket);
    }

    public void updateSpeed(int scoreInterval) {
        if ((score.getCurrentScore() / scoreInterval) > (score.getPrevScore() / scoreInterval)) {
            dropSpeed += 20 * score.getCurrentScore() / scoreInterval;
            dropInterval /= 1.2;
        }
    }

    public void endGame() {
        dropFactory.resetDrops();
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        int x = WIDTH / 3;
        int y = HEIGHT / 2;
        textWriter.write("HighScore " + score.getHighScore(), x, y);
        textWriter.write("Click to try again ", x, y - 50);
        if (Gdx.input.isTouched()) {
            gameState = RUN;
            dropSpeed = INITIAL_DROP_SPEED;
            dropInterval = INITIAL_DROP_INTERVAL;
            score.resetScore();
        }
    }

    @Override
    public void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}