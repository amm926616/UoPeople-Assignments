package com.uopeople.clock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Main class to initialize and run the clock application.
 */
public class Main {
    public static void main(String[] args) {
        AtomicBoolean running = new AtomicBoolean(true);
        Clock clock = new Clock();

        // Initialize threads
        ClockPrinter clockPrinter = new ClockPrinter(clock, running);
        TimeUpdater timeUpdater = new TimeUpdater(running);

        // Start threads
        clockPrinter.start();
        timeUpdater.start();

        // Run for 30 seconds for demonstration
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        } finally {
            // Stop threads
            running.set(false);
        }

        System.out.println("Clock application terminated.");
    }
}
