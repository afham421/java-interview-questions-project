package JavaQuestions.ThreadImplementation;
import java.util.concurrent.*;
public class ThreadImplementation {
    //in java, you can create threads by two basic ways  1.Runnable interface and 2.extending Thread class //Thread class implement Runnable interface means it is parent
//    Summary of Thread States:
//    NEW: Thread has been created but not started.
//    RUNNABLE: Thread is either running or ready to run.
//    BLOCKED: Thread is blocked waiting for a monitor lock.
//    WAITING: Thread is waiting indefinitely for another thread to perform an action.
//    TIMED_WAITING: Thread is waiting for a specified period of time.
//    TERMINATED: Thread has completed its execution.
    public static void main(String[] args) throws ExecutionException, InterruptedException {// here 3 threads are created 2 our custom and 1 main and main do his work
        // and kill himself after doing his work and its work is to start other tasks or threads that are in main method
        // by runnable interface
        MyRunnable myRunnable = new MyRunnable();//we pass myRunnable in Thread because Runnable has not start() method so we pass myRunnable into Thread() and start() thread
        Thread thread = new Thread(myRunnable);  // this line is same as above line. Both lines are creating a thread and starting it.
        thread.start(); // Start the thread //Thread.start() start a thread that call the run() method and Runnable.run() just call the run() method on the current thread
        // everytime you should use start() because it creates new thread but the run() method not creates new thread it only work on current thread
        System.out.println("Main thread is running by implementing runnable...");
        // by Thread class
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("Main thread is running by extending thread...");  // this will be executed first as main thread is not blocked by thread execution

        //by Anonymous class
        //An anonymous class in Java is a class that is declared and instantiated all at once, usually in a single expression, without explicitly naming the class.
        // It's a way to create a one-time-use class on the fly, typically to override or implement methods in a specific context.
        // Anonymous classes are often used in situations where a simple implementation is needed and defining a separate named class would be overkill.
        //No Name: As the name suggests, anonymous classes do not have a name. They are declared and instantiated in a single expression.
        //Single Use: They are often used when you need to provide a specific implementation of an interface or override methods in a class just once.
        //Local Scope: Anonymous classes are usually defined within a method or an initialization block, and their scope is local to where they are defined.
        //Extending a Class or Implementing an Interface: Anonymous classes can either extend a class or implement an interface, but not both simultaneously.
        //for example:
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread is running...");
            }
        });
        thread1.start(); // Start the thread
        //for example:// Create an anonymous class extending JavaQuestions.ThreadImplementation.Animal
                Animal dog = new Animal() {
                    @Override
                    void sound() {
                        System.out.println("Bark!");
                    }
                };

                dog.sound(); // Output: Bark!

        //by Lambda expression
        Thread thread2 = new Thread(() -> System.out.println("Thread is running..."));
        thread2.start(); // Start the thread

        //using executor
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new MyRunnable());
        executor.shutdown();
//        or
        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        executor1.submit(() -> {
            System.out.println("Thread is running...");
        });
        executor1.shutdown(); // Shut down the executor

        //6. Using Callable and Future (For Tasks That Return a Result)
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        Callable<String> task = () -> {
            return "Thread result";
        };
        Future<String> future = executor2.submit(task);
        System.out.println(future.get()); // Get the result from the future
        executor2.shutdown(); // Shut down the executor


    }

}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread is running...");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running...");
    }
}

abstract class Animal {
    abstract void sound();
}