package com.uopeople.clock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * TimeUpdater class simulates a background task
 * that updates the clock state in a separate thread.
 */
public class TimeUpdater extends Thread {
    private final AtomicBoolean running;

    public TimeUpdater(AtomicBoolean running) {
        this.running = running;
        this.setName("TimeUpdaterThread");
        this.setPriority(MIN_PRIORITY); // Lower priority for background task
    }

    @Override
    public void run() {
        while (running.get()) {
            // Simulate background work
            try {
                Thread.sleep(500); // Background task with a lower priority
            } catch (InterruptedException e) {
                System.err.println("TimeUpdater thread interrupted: " + e.getMessage());
            }
        }
    }
}
