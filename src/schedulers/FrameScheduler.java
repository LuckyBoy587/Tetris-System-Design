package schedulers;

import java.util.ArrayList;
import java.util.List;

public class FrameScheduler {
    private final int fps;
    List<ScheduledTask> scheduledTasks = new ArrayList<>();

    public FrameScheduler(int fps) {
        this.fps = fps;
    }

    public void scheduleEvery(int frameInterval, GameTask task) {
        scheduledTasks.add(new ScheduledTask(frameInterval, task));
    }

    public void update(long currentFrame) {
        for (ScheduledTask task : scheduledTasks) {
            task.executeIfDue(currentFrame);
        }
    }

    public void scheduleEvery(int interval, Interval intervalType, GameTask task) {
        int frameInterval = switch (intervalType) {
            case SECONDS -> interval * fps;
            case FRAMES -> interval;
            case MILLISECONDS -> Math.max(1, (int) Math.round((interval / 1000.0) * fps));
        };
        scheduleEvery(frameInterval, task);
    }
}
