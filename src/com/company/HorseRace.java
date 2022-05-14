package com.company;

import java.time.LocalTime;
import java.util.Random;

public class HorseRace {
    private static class FinishingLine {
        public void arrive(Horse h) {
            System.out.printf("[%s] %s%n", LocalTime.now(), h.getId());
        }
    }

    private static class Horse implements Runnable {
        private final String id;
        private final FinishingLine f;
        private int distance;

        public Horse(String id, FinishingLine f, int distance) {
            this.id = id;
            this.f = f;
            this.distance = distance;
        }

        @Override
        public void run() {
            Random rnd = new Random();
            while (this.distance > 0) {
                this.distance -= (1 + rnd.nextInt(9));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    /* empty */
                }
            }
            f.arrive(this);
        }

        public String getId() {
            return id;
        }
    }

    public static void main(String[] args) {
        FinishingLine f = new FinishingLine();
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) // init all horses
            threads[i] = new Thread(new Horse("Horse " + i, f, 100));

        System.out.printf("[%s] Race Started%n", LocalTime.now());
        for (Thread thread : threads) // start all horses
            thread.start();

        for (Thread thread : threads) { // wait for all of them to finish
            try {
                thread.join();
            } catch (InterruptedException e) {
                /* empty */
            }
        }
        System.out.printf("[%s] Race finished%n", LocalTime.now());
    }
}
