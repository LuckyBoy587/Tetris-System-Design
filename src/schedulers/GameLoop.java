package schedulers;

public class GameLoop implements Runnable {
    public static final int FPS = 60;
    private static final long FRAME_TIME = 1000 / FPS;

    private boolean running = true;
    private long frameCount = 0;

    private final FrameScheduler scheduler = new FrameScheduler(FPS);

    public FrameScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void run() {
        while (running) {
            long start = System.currentTimeMillis();
            frameCount++;

            scheduler.update(frameCount);

            long elapsed = System.currentTimeMillis() - start;
            long sleep = FRAME_TIME - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (InterruptedException ignored) {}
            }
        }
    }

    public void stop() {
        running = false;
    }
}
