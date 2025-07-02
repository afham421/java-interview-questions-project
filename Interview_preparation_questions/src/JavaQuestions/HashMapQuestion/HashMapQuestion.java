package JavaQuestions.HashMapQuestion;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapQuestion {
    public static void main(String[] args) {
        // hashmap contain 0.75 load factor by default for resizing of hashmap, and it has 16 buckets and 12 throughput size means when 12 buckets used
        // it will increase bucket size by 32 and throughput size by 24 automatically
        // hashmap can't provide ordering because it calculates hash on the base of key and then calculate index and different key have different hash and index
        // if two keys have same hash then the hash collision occurs, and then it store data in linked list form on this bucket and if data increases it will automatically
        // apply binary search tree algorithm
        // hashmap represent the hashtable data structures because both have key value pairs type
        // array and linked list are used to implement hashmap.
        // Hashmap is not thread safe because multiple threads can access the same hashmap simultaneously, and they can modify the data at the same time.
        // No, HashMap in Java is not thread-safe by default. If you use a HashMap in a multithreaded environment where multiple threads might modify the map concurrently,
        // it can lead to issues like data corruption, infinite loops, or other unexpected behavior.
        // If you want to make thread safe, then
        // 1.ConcurrentHashMap:
        // Designed for concurrent use, ConcurrentHashMap allows multiple threads to read and write without locking the entire map.
        // It achieves thread safety by dividing the map into segments and locking only the necessary segments during updates.
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        // 2.Collections.synchronizedMap():
        // Wraps a regular HashMap in a synchronized map, making all its methods thread-safe by adding synchronization.
        // While this makes it safe for concurrent access, it can still be less efficient than ConcurrentHashMap because it locks the entire map for each operation.
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
//        When to Use Which:
//        Use ConcurrentHashMap when you need a thread-safe map with high concurrency and performance.
//        Use Collections.synchronizedMap() if you have a legacy system that needs thread safety, but the performance requirements are not critical.
//        In summary, HashMap itself is not thread-safe, but you can use ConcurrentHashMap or Collections.synchronizedMap() if you need thread safety in a multithreaded environment.

        HashMap<Integer, String> map = new HashMap<Integer, String>();

        map.put(12, "phy");
        map.put(13, "math");
        map.put(14, "chem");
        map.put(15, "bio");
        map.put(0, "bio");

        System.out.println(map);
        // Get the size of the HashMap using size()
        int size = map.size();    // capacity means how many elements a map can store and size means how many elements are present in the map
        long size1 = map.size(); // size can be got in long also
        System.out.println("The size of the HashMap is: " + size);
        System.out.println("The size of the HashMap is: " + size1);


        System.out.println(map.get(12));

        // Iterate over keys
        for (Integer key : map.keySet()) {
            // so you will use iterator.remove() in loop otherwise simple remove give exception
//            if (key.equals(12)) {
//                map.remove(key); // This can cause ConcurrentModificationException
//            }
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }
        // Use an iterator to safely remove elements
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            // Check the condition and remove if it matches
            if (key.equals(12)) {
                iterator.remove(); // Safe removal during iteration
            } else {
                System.out.println("Key: " + key + ", Value: " + map.get(key));
            }
        }
//⚠️ Important Notes:
//You must call iterator.next() before iterator.remove().
//
//You should not call map.remove() inside iterator loop — it's like pulling the rug out from under the loop.

        //✅ Simple Analogy:
        //Think of a classroom roll call:
        //
        //🔁 for-each loop is like a teacher reading names off a list.
        //You can’t erase names while reading — it confuses the teacher.
        //
        //✅ Iterator is like a teacher checking each student and crossing out names safely as they go.


        // Iterate over entries
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        HashMap<Emp, Integer> emp = new HashMap<Emp, Integer>();

        Emp e1 = new Emp(1, "John");
        Emp e2 = new Emp(2, "Jane");
        Emp e3 = new Emp(3, "Lala");

        emp.put(e1, 1000);
        emp.put(e2, 2000);
        emp.put(e3, 3000);

        System.out.println(emp);

        for (Emp e : emp.keySet()) {
            System.out.println(e.name + "'s salary " + emp.get(e));
        }
        for (Map.Entry<Emp, Integer> e : emp.entrySet()) {

            System.out.println(e.getKey().name + "    " + e.getValue());
        }


//        When to Use:
//        Use keySet() when you only need the keys, for example, when you’re just checking for the presence of keys or want to delete entries by keys.
//        Use entrySet() when you need to work with both keys and their corresponding values, especially if you plan to modify the values.
//        Key Differences:
//        Content:
//                keySet(): Provides a set of all keys in the map.
//                entrySet(): Provides a set of all key-value pairs (Map.Entry objects) in the map.
//        Iteration:
//                keySet(): Use when you only need to work with keys.
//                entrySet(): Use when you need to work with both keys and values simultaneously.
//        Modification:
//                  Removing Elements:
//                  Both keySet() and entrySet() allow modification of the underlying map by removing elements during iteration.
//                  Value Update:
//                  In entrySet(), you can directly modify the value of a Map.Entry using entry.setValue().
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            System.out.println("Key: " + key);
        }

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }


//        Hashtable
//        Thread Safety: Hashtable is synchronized, which means it is thread-safe. All methods are synchronized to ensure that only one thread can access a method at a time.
//        It means it locks in that way only one thread can access a method at a time
//        Performance: Because of its synchronization, Hashtable can be slower than other map implementations when used in a multithreaded environment.
//        Hashtable: You cannot modify the data while iterating. Doing so will throw a ConcurrentModificationException.
        Hashtable<String, Integer> hashtable = new Hashtable<>();
//      ConcurrentHashMap
//      Thread Safety: ConcurrentHashMap is also thread-safe but is designed for better concurrency. It allows multiple threads to access and modify
//      the map concurrently without blocking each other. It means that it holds or locks some portion or segment but hashtable locks full portion
//      Performance: It typically provides better performance than Hashtable in a multithreaded environment because it uses finer-grained locking
//      (i.e., segment-based locking) rather than locking the entire map.
//      Null Keys and Values: ConcurrentHashMap does not allow null keys or values. Like Hashtable, attempting to insert null will throw a NullPointerException.
//        ConcurrentHashMap: You can modify the data while iterating. It is designed to handle concurrent modifications safely, but the iterator may not reflect changes made after the iterator was created.
        ConcurrentHashMap<String, Integer> conMap = new ConcurrentHashMap<>();

        // when multiple threads are modifying the hashmap concurrently, then concurrentmodificationsexception occurs

    }

}

class Emp {
    int id;
    String name;

    Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "JavaQuestions.HashMapQuestion.Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
