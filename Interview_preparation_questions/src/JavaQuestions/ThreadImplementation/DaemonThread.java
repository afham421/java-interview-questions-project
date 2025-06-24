package JavaQuestions.ThreadImplementation;
//A daemon thread in Java is a special type of thread that runs in the background and does not prevent the JVM from exiting when all non-daemon threads
//have finished executing. Daemon threads are typically used for tasks like garbage collection, background tasks,
// or housekeeping activities that should not block the termination of an application.
//
// Daemon threads are service providers of other threads     or     Daemon threads run in the background to support other threads
//
//Key Characteristics of Daemon Threads:
//Background Role: Daemon threads run in the background to support other threads, typically performing tasks that are not crucial to the application's main functionality.
//
//JVM Shutdown: The JVM automatically terminates daemon threads when all user (non-daemon) threads have completed.
//Therefore, daemon threads should not be relied upon to perform critical operations like writing to a database or saving files because they may be terminated abruptly.
//
//Setting Daemon Status: You can set a thread as a daemon thread by calling the setDaemon(true) method before the thread is started.
//
//Default Behavior: By default, threads created in Java are non-daemon (user threads).

//Example of a Daemon Thread:

public class DaemonThread {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running in background...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemonThread.setDaemon(true); // Set the thread as a daemon thread
        daemonThread.start();
        try {
            Thread.sleep(2000); // Main thread sleeps for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread finished execution");
        // The JVM will exit as soon as the main thread finishes, terminating the daemon thread
    }
}
//Explanation:
//Daemon Thread: The daemonThread runs an infinite loop that prints a message every 500 milliseconds. It’s set as a daemon thread using setDaemon(true).
//Main Thread: The main thread sleeps for 2 seconds and then prints a message indicating its completion.
//JVM Behavior: When the main thread finishes execution, the JVM exits, and the daemon thread is terminated, even if it is still running.

//Use Cases for Daemon Threads:
//Garbage Collection: Java's garbage collector thread is a daemon thread that runs in the background, cleaning up unused objects.
//Background Services: Threads that perform periodic monitoring, logging, or other background tasks that don’t need to run after the main application ends.
//Task Scheduling: Scheduling tasks that need to be executed in the background without blocking the application's exit.

//Important Considerations:
//Thread Priority: Daemon threads typically have lower priority, but this can be adjusted.
//Shutdown Hooks: If you need to perform cleanup tasks before the JVM exits, consider using shutdown hooks instead of relying on daemon threads, as they may be terminated abruptly.
//Daemon threads should be used for non-essential tasks, as they can be terminated at any time when the JVM decides to exit.