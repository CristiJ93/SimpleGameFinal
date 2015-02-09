package game.objects;

public final class Properties {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    public static final int DROP_WIDTH = 32;
    public static final int DROP_HEIGHT = 32;

    public static final int BUCKET_WIDTH = 64;
    public static final int BUCKET_HEIGHT = 64;
    public static final int BUCKET_X = WIDTH / 2 - BUCKET_WIDTH / 2;
    public static final int BUCKET_Y = 20;

    public static final int LEFT_LIMIT = 0;
    public static final int RIGHT_LIMIT = WIDTH - DROP_WIDTH;

    public static final int INITIAL_DROP_SPEED = 200;
    public static final long INITIAL_DROP_INTERVAL = 1000000000;
}
