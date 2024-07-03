/* Автобусные остановки. На маршруте несколько остановок. На одной
остановке может останавливаться несколько автобусов одновременно, но
не более заданного числа*/

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BusStop {
    private final Semaphore semaphore;

    public BusStop(int maxBuses) {
        semaphore = new Semaphore(maxBuses);
    }

    public void enterBusStop(String busName) throws InterruptedException {
        semaphore.acquire();
        System.out.println(busName + " остановился на остановке.");
    }

    public void leaveBusStop(String busName) {
        System.out.println(busName + " покидает остановку.");
        semaphore.release();
    }
}

class Bus implements Runnable {
    private final String name;
    private final BusStop busStop;

    public Bus(String name, BusStop busStop) {
        this.name = name;
        this.busStop = busStop;
    }

    @Override
    public void run() {
        try {
            busStop.enterBusStop(name);
            TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            busStop.leaveBusStop(name);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int maxBusesAtStop = 3;
        int totalBuses = 10;
        BusStop busStop = new BusStop(maxBusesAtStop);

        ExecutorService executorService = Executors.newFixedThreadPool(totalBuses);

        for (int i = 1; i <= totalBuses; i++) {
            executorService.submit(new Bus("Автобус " + i, busStop));
        }

        executorService.shutdown();
    }
}