package JavaQuestions.MapSetHashMapHashSetListArray;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap interview demo notes:
 *
 * Internals:
 * - Default capacity = 16 buckets, loadFactor = 0.75
 * - Resize when SIZE reaches threshold = capacity * loadFactor (16 * 0.75 = 12 entries)
 *   (not "12 buckets used") → new capacity 32, new threshold 24
 * - No insertion/sorted order (hash-based index)
 * - Collision: same bucket → linked list; if bin grows (>=8) and table capacity >= 64 → treeify (Red-Black Tree)
 * - Built from array of buckets + list/tree nodes
 *
 * Thread-safety:
 * - HashMap is NOT thread-safe
 * - Prefer ConcurrentHashMap (Java 8+: CAS + bin lock) over Hashtable / synchronizedMap for concurrency
 * - Collections.synchronizedMap locks the WHOLE map per call
 */
public class HashMapQuestion {
    public static void main(String[] args) {
        // ---- Thread-safe alternatives (HashMap itself is not) ----
        // 1.ConcurrentHashMap:
        // Designed for concurrent use, ConcurrentHashMap allows multiple threads to read and write without locking the entire map.
        // It uses techniques such as CAS (Compare-And-Swap) and synchronized operations on individual bins instead of locking the entire map for every update.
        // It achieves thread safety by dividing the map into segments and locking only the necessary segments during updates.
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        // 2.Collections.synchronizedMap():
        // Wraps a regular HashMap in a synchronized map, making all its methods thread-safe by adding synchronization.
        // While this makes it safe for concurrent access, it can still be less efficient than ConcurrentHashMap because it locks the entire map for each operation.
        // Prefer concurrentMap for high concurrency; synchronizedMap for simple/legacy cases

        HashMap<Integer, String> map = new HashMap<>();
        map.put(12, "phy");
        map.put(13, "math");
        map.put(14, "chem");
        map.put(15, "bio");
        map.put(0, "bio");

        System.out.println(map);

        // size() = number of entries present (returns int)
        // capacity = internal bucket array length (not exposed as public size)
        int size = map.size();
        System.out.println("The size of the HashMap is: " + size);
        System.out.println(map.get(12));

        // ---- Iterate keys ----
        for (Integer key : map.keySet()) {
            // map.remove(key) here can throw ConcurrentModificationException
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // ---- Safe remove while iterating: use Iterator.remove() ----
        // Must call next() before remove(). Do NOT call map.remove(key) in this loop.
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            if (key.equals(12)) {
                iterator.remove(); // Safe removal during iteration
            } else {
                System.out.println("Key: " + key + ", Value: " + map.get(key));
            }
        }

        // ---- entrySet: best when you need key + value together ----
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            // entry.setValue("new") can update value safely via the entry
        }

        // keySet()  → only keys
        // entrySet() → key-value pairs (usually preferred for reading both)
        for (Integer key : map.keySet()) {
            System.out.println("Key: " + key);
        }
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        // ---- Custom key class: MUST override equals + hashCode ----
        HashMap<Emp, Integer> emp = new HashMap<>();
        Emp e1 = new Emp(1, "John");
        Emp e2 = new Emp(2, "Jane");
        Emp e3 = new Emp(3, "Lala");

        emp.put(e1, 1000);
        emp.put(e2, 2000);
        emp.put(e3, 3000);

        System.out.println(emp);
        // lookup with a NEW object of same id works only because equals/hashCode are based on id
        System.out.println("Salary of id=1: " + emp.get(new Emp(1, "Anyone")));

        for (Emp e : emp.keySet()) {
            System.out.println(e.name + "'s salary " + emp.get(e));
        }
        for (Map.Entry<Emp, Integer> e : emp.entrySet()) {
            System.out.println(e.getKey().name + "    " + e.getValue());
        }

        // Hashtable = legacy synchronized whole-table lock; Iterator is fail-fast
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        // ConcurrentHashMap = modern concurrent map; iterators are weakly consistent
        ConcurrentHashMap<String, Integer> conMap = new ConcurrentHashMap<>();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Emp)) return false;
        Emp emp = (Emp) o;
        return id == emp.id; // business equality by id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // MUST be consistent with equals
    }

    @Override
    public String toString() {
        return "Emp{id=" + id + ", name='" + name + "'}";
    }
}
