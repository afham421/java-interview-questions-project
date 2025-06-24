package JavaQuestions.Logical_programs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class List_Traverse {

//    ArrayList:
//    Best for frequent access to elements and when thread safety is not a concern.
//    Faster access by index O(1), but slower for insertions and deletions.O(n)
//    LinkedList:
//    Best for scenarios where frequent insertions and deletions are required.
//    Slower access by index O(n), but faster for insertions and deletions at the ends or in the middle.O(1)
//    Vector:
//    Synchronized and thread-safe, but often replaced by ArrayList in modern applications. access time O(1) and insertion deletion time O(n)
//    Use Vector if thread safety is required, and you are dealing with legacy code.

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("kaka");
        list.add("lala");
        list.add("mama");
//we can use this for loops that are index-based
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("++++++++++++++++++++");
// we can use this enhanced loop on all like sets list collections
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("++++++++++++++++++++");

// we can use iterator methods // this is only for forward direction
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("++++++++++++++++++++");

// we can use listIterator method /// this is for forward and backward
        ListIterator listIterator = list.listIterator(); // we can apply it only list(ArrayList,linked list ,vector)
        while (listIterator.hasNext()) { // for forward
            System.out.println(listIterator.next());
        }
        ListIterator<String> stringListIterator = list.listIterator(list.size());
        while (stringListIterator.hasPrevious()) { // for backward
            System.out.println(stringListIterator.previous());
        }
        System.out.println("++++++++++++++++++++");

        System.out.println("++++++++++ with forEach ++++++++++");
        list.forEach(e -> {
            System.out.println(e);
        });

        list.forEach(System.out::println);

        System.out.println("++++++++++++++++++++++++++++++++");

        list.stream().forEach(System.out::println);

        //we can convert a list to array
        String[] array = new String[list.size()];
        list.toArray(array);
        for (String s : array) {
            System.out.println(s);
        }
        //we can convert an array to list
        List<String> listTest = Arrays.asList(array);

        //we can convert an array to stream
        Stream<String> Stream = Arrays.stream(array);

        // we can convert a list to stream
        Stream<String> stringStream = list.stream();

        // we can convert stream to list
        List<String> listTest2 = stringStream.collect(Collectors.toList());


    }

}
