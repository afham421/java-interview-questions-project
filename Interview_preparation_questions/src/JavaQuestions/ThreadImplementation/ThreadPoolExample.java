package JavaQuestions.ThreadImplementation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a thread pool with 10 threads
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        In this example, a thread pool with 10 threads is created. Even though 20 tasks are submitted, only 10 threads are active at any time, and they are reused to handle the tasks.
        for (int i = 0; i < 20; i++) {
            // Submit tasks to the thread pool
            executorService.submit(new Task());
        }

        // Shut down the executor service
        executorService.shutdown();
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Task executed by thread: " + Thread.currentThread().getName());
    }
}
