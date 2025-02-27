package com.uopeople.clock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ClockPrinter class handles the printing of the clock
 * in a separate thread with higher priority.
 */
public class ClockPrinter extends Thread {
    private final Clock clock;
    private final AtomicBoolean running;

    public ClockPrinter(Clock clock, AtomicBoolean running) {
        this.clock = clock;
        this.running = running;
        this.setName("ClockPrinterThread");
        this.setPriority(MAX_PRIORITY); // Higher priority for printing
    }

    @Override
    public void run() {
        while (running.get()) {
            System.out.println("Current Time: " + clock.getCurrentTime());
            try {
                // Print every second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("ClockPrinter thread interrupted: " + e.getMessage());
            }
        }
    }
}
