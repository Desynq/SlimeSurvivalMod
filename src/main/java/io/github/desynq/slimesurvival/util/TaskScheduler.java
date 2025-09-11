package io.github.desynq.slimesurvival.util;

import io.github.desynq.slimesurvival.SlimeSurvival;
import net.minecraft.server.MinecraftServer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.PriorityQueue;
import java.util.UUID;

@EventBusSubscriber(modid = SlimeSurvival.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
public class TaskScheduler {
    private static final PriorityQueue<DelayedTask> taskQueue = new PriorityQueue<>();

    public static void scheduleTask(MinecraftServer server, float delay, Runnable task) {
        TaskScheduler.scheduleTask(server, delay, task, UUID.randomUUID());
    }

    public static void scheduleTask(MinecraftServer server, float delay, Runnable task, UUID taskId) {
        long currentTick = server.getTickCount();
        long executionTick = currentTick + (long)delay;

        if (delay == 0) {
            task.run();
        }
        else {
            DelayedTask delayedTask = new DelayedTask(executionTick, task, taskId);
            taskQueue.add(delayedTask);
        }
    }

    public static void unscheduleTask(UUID taskId) {
        taskQueue
                .stream()
                .filter(t -> t.id.equals(taskId))
                .toList()
                .forEach(taskQueue::remove);
    }

    public static void repeatTask(final MinecraftServer server, final float delay, final Runnable task, final UUID taskId) {
        Runnable repeatedTask = new Runnable() {
            @Override
            public void run() {
                task.run();
                TaskScheduler.scheduleTask(server, delay, this, taskId);
            }
        };

        TaskScheduler.scheduleTask(server, delay, repeatedTask, taskId);
    }

    @SubscribeEvent
    public static void onTick(ServerTickEvent.Post event) {
        synchronized (taskQueue) {
            if (!taskQueue.isEmpty()) {
                long currentTick = event.getServer().getTickCount();

                while (!taskQueue.isEmpty() && taskQueue.peek().executionTick <= currentTick) {
                    DelayedTask taskToRun = taskQueue.poll();
                    taskToRun.task.run();
                }
            }
        }
    }


    private record DelayedTask(long executionTick, Runnable task, UUID id) implements Comparable<DelayedTask> {

        @Override
        public int compareTo(DelayedTask otherTask) {
            return Long.compare(this.executionTick, otherTask.executionTick);
        }
    }
}
