package JavaQuestions;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentCollection {

    public static void main(String[] args) {

//        List<String> list = new ArrayList<String>(); // now by simple list it give concurrent modification Exception so we use CopyOnWriteArrayList<String>()
// by using CopyOnWriteArrayList class you can get performance also but if we make arraylist synchronized it will lose performance so we should use built-in classes
        List<String> list = new CopyOnWriteArrayList<>(); // CopyOnWriteArrayList is thread-safe and allows multiple threads to access and
        // modify the list concurrently without causing a ConcurrentModificationException.
        list.add("Hello");
        list.add("World");
        list.add("Java");
        list.add("physics");
        list.add("universe");

        //creating threads
        Runnable t1 = () -> {
            for (String s : list) {
                System.out.println(Thread.currentThread().getName() + " : " + s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
//                 t2 adds "Pool" and prints "Pool added".
//                Thread t1 starts iterating and takes a snapshot that includes "Pool" but not "Jokin".
//                t2 adds "Jokin" and prints "Jokin added", but it's too late for the current iteration in t1 to pick up.
        Runnable t2 = () -> {
            list.add("Pool");
            list.add("Jokin");
            System.out.println(Thread.currentThread().getName() + " added 'Pool'"); // so if i put this statement before jokin then it will be late to print by thread1
            //because thread1 will take a snapshot before adding the jokin  so i put this statement after jokin addition
            System.out.println(Thread.currentThread().getName() + " added 'Jokin'");
        };

        //starting threads
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread2.start();
        thread1.start();

        //joining threads
        try {
            thread2.join(); // Main thread waits for thread2 to complete
            thread1.join(); // Main thread waits for thread1 to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final list to show that new elements were added
        System.out.println("Final list: " + list);
        System.out.println("Both threads have finished. Main thread continues.");


    }
}
