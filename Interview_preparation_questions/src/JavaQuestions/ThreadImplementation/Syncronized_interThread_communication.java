package JavaQuestions.ThreadImplementation;

public class Syncronized_interThread_communication {
//    In Java, the synchronized keyword and inter-thread communication mechanisms like wait(), notify(), and notifyAll() are commonly used to coordinate
//    the actions of multiple threads, especially in scenarios like the producer-consumer problem.

//    1. The synchronized Keyword:
//    Purpose: Ensures that only one thread can access a block of code or method at a time, preventing race conditions.
//    Usage:
//    Method-level synchronization:
//    public synchronized void synchronizedMethod() {
//        // code
//    }
//    Block-level synchronization:
//    public void someMethod() {
//        synchronized(this) {
//            // code
//        }
//    }

//2. Inter-thread Communication (wait(), notify(), notifyAll()):
//    Purpose: Allows threads to communicate with each other by waiting for certain conditions to be met and notifying others when they can proceed.
//    wait(): Makes the current thread release the lock and enter the waiting state until another thread calls notify() or notifyAll() on the same object.
//    notify(): Wakes up one thread waiting on the object's monitor (lock).
//    notifyAll(): Wakes up all threads waiting on the object's monitor.

    int n;
    Boolean chance = false;// we will use this as a inter-thread communication
    //chance false means the turn is producer
    //chance true means the turn is consumer
    synchronized public void produceThread(int n){// synchronized means only one thread can access at a time
        if (chance){ // true hy tu
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.n = n;
        System.out.println("produced : " + this.n);
        chance = true;
        notify(); // it will notify the other thread who is waiting state means producer say i produced now consumer consume and vise versa
    }
    public synchronized int consumeThread(){
        if (!chance){ // false hy tu
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("consumed : " + this.n);
        chance = false;
        notify();
        return this.n;
    }

    public static void main(String[] args) {
        Syncronized_interThread_communication sharedResource = new Syncronized_interThread_communication();
        Producer producer = new Producer(sharedResource);
        Consumer consumer = new Consumer(sharedResource);

        producer.start();
        consumer.start();
    }

}

class Producer extends Thread{
    Syncronized_interThread_communication sharedResource;
    public Producer(Syncronized_interThread_communication sharedResource){
        this.sharedResource = sharedResource;
    }
    public void run(){
        int i = 0;
        while (true){
            this.sharedResource.produceThread(i);
            try {
                Thread.sleep(1000);// if time of sleep is same as sleep time of consumer then it's fine but if change then problem consumer not consume every
                //producer value and vise versa// now producer produce value in every sec but consumer consume value after 2 sec so it miss every 2nd value to consume
                // so its handle with inter-threads communication
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }
}

class Consumer extends Thread{
    Syncronized_interThread_communication sharedResource;
    public Consumer(Syncronized_interThread_communication sharedResource){
        this.sharedResource=sharedResource;
    }
    public void run(){
        while (true){
            this.sharedResource.consumeThread();
            try {
                Thread.sleep(2000);// if time of sleep is same as sleep time of consumer then it's fine but if change then problem consumer not consume every
                //producer value and vise versa // now producer produce value in every sec but consumer consume value after 2 sec so it miss every 2nd value to consume
                // so its handle with inter-threads communication
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}