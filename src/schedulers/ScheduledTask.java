package schedulers;

public class ScheduledTask {
    private long prevRanFrame = 0;
    private final int frameInterval;
    private final GameTask task;

    public ScheduledTask(int frameInterval, GameTask task) {
        this.frameInterval = frameInterval;
        this.task = task;
    }

    public void executeIfDue(long currentFrame) {
        if (currentFrame - prevRanFrame >= frameInterval) {
            task.run();
            prevRanFrame = currentFrame;
        }
    }
}
