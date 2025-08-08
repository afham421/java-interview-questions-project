package JavaQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//🚀 What is the Stream API?
//The Stream API lets you process collections (like Lists, Sets) in a declarative, pipeline-style — meaning what you want to do, not how to do it.
//✅ Key Features
//No storage: Stream doesn't store elements, it processes them.
//
//Functional: Uses lambdas and functional interfaces.
//
//Lazy evaluation: Operations run only when needed.
//
//Efficient: Can work in parallel (via parallelStream()).

//If you don't pass a terminal operation (like collect(), forEach(), count() etc.) in a stream pipeline, then:
//
//⚠️ Nothing will happen.
//Because streams are lazy — intermediate operations (like map(), filter()) are not executed until a terminal operation is called.
public class StreamApi {

//    Stream operations are divided into three main categories:
//    1.Source:
//    Collection, array, or generator
//    2.Intermediate Operations:
//    Return another stream, allowing multiple operations to be chained together.
//            Examples: filter, map, sorted, distinct, limit, skip.
//    3.Terminal Operations:
//    Produce a result or a side effect and terminate the stream pipeline.
//    Examples: forEach, collect, reduce, count, findFirst, findAny, allMatch, anyMatch, noneMatch.

    public static void main(String[] args) {
//Simple method to create a list of integers
        List<Integer> list = new ArrayList<Integer>();// mutable list of integers
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(11);
        System.out.println(list);

// 2nd way to create list
        List<Integer> list1 = List.of(1, 2, 3, 4); // But this .of() return list that is immutable we can't modify
//        list1.add(23);// it give error ImmutableCollections
        System.out.println(list1);
//3rd way to create list
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5); // it is also immutable
//        list2.add(23);// it give error AbstractList
        System.out.println(list2);


        // finding even numbers with old for loop
        List<Integer> list3 = new ArrayList<Integer>();
        for (Integer i : list) {
            if (i % 2 == 0) {
                list3.add(i);
            }
        }
        System.out.println(list3);

        // Using stream Api //The stream itself is immutable
//        Intermediate and Terminal Operations:
//        Intermediate operations (e.g., filter, map, sorted) return a new stream and are typically used to transform the stream or filter elements.
//        Terminal operations (e.g., collect, forEach, reduce) trigger the processing of the stream and produce a result or side effect.
        List<Integer> list4 = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
//        list4.add(12);// we can modify this stream list Collectors.toList() returning mutable list
        // by using Collectors we can use .toList() .toUnmodifiableSet() .toSet() .toUnmodifiableList etc.
        System.out.println(list4);


        //.filter(Predicate) method take predicate (means Boolean returns value function) it works on boolean
        // if predicate returns true then it will filter otherwise not
        List<String> strings = List.of("afham", "hussain");
        List<String> collected = strings.stream().filter(e -> e.startsWith("h")).toList();
//        collected.add("hadi"); // we can't modify the list with .toList() but can with Collectors.toList()
        System.out.println(collected);

        //.map(Function) method take function (means return value), and It's used for modifications it works on value
        List<Integer> collect = list.stream().map(e -> e * e).collect(Collectors.toList());
        System.out.println(collect);
        List<String> result = strings.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(result);
//Parallel stream
//        Streams can be made parallel to perform operations concurrently, which can improve performance for large datasets:
        List<Integer> evenNumbers = list.parallelStream().filter(n -> n % 2 == 0).collect(Collectors.toList());
// finding maximum element
        Integer i = list.stream().max((a, b) -> a.compareTo(b)).get();
        System.out.println(i);

        int max = list.stream()
                .max(Integer::compare)
                .orElseThrow(); // or use .orElse(0)
        System.out.println("Max Number: " + max);


        ///  ///////// flatMap() >>>>>>>>>>>>>>>>>>>
        //🔸 Basic Concept:
        //map() → transforms each element 1-to-1
        //
        //flatMap() → transforms and flattens the result 1-to-many
        //
        //🔹 Syntax:
        //java
        //Copy
        //Edit
        //stream.flatMap(element -> Stream.of(...))
        //🔹 Example 1: Flattening a list of lists
        //java
        //Copy
        //Edit
        List<List<String>> nestedList = Arrays.asList(
            Arrays.asList("A", "B"),
            Arrays.asList("C", "D")
        );

        List<String> flatList = nestedList.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());

        System.out.println(flatList); // Output: [A, B, C, D]
        //🔹 Example 2: Splitting strings into words
        //java
        //Copy
        //Edit
        List<String> lines = Arrays.asList("hello world", "java stream");

        List<String> words = lines.stream()
            .flatMap(line -> Arrays.stream(line.split(" ")))
            .collect(Collectors.toList());

        System.out.println(words); // Output: [hello, world, java, stream]
        //🔹 When to Use flatMap
        //Use flatMap() when your mapping function returns a stream, collection, or array — and you want to flatten it into a single stream.

    }

    void creatingStream(){
        Integer [] array = {2,4,6,7,8};
        //1st way to create a stream
        Stream<Integer> stream1 = Arrays.stream(array);
        //2nd way to create a stream
        Stream<Integer> stream2 = Stream.of(1 ,4,6); // you can pass any object like integer String or array, etc.
        //3rd way to create a stream
        List<Integer> integerList = new ArrayList<>();
        integerList.add(13);
        Stream<Integer> stream = integerList.stream();

    }




}
