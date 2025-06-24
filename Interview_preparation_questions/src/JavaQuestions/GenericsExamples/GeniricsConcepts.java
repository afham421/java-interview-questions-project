package JavaQuestions.GenericsExamples;

import java.util.ArrayList;
import java.util.List;

public class GeniricsConcepts {
    public static void main(String[] args) {
        List list = new ArrayList(); // here we can add any type of value so it is not type safe
        list.add(1);
        list.add("Hello");// for example, when another person gets this list as an integer, but it has boolean and string, also it will give error on runtime
        // but now showing on compile time so it is a bad approach
        list.add(true);
        System.out.println(list);
        System.out.println("=======================");

        List<Integer> list1 = new ArrayList<Integer>();//here we can add only integers if we try to add another type, it will give compile time error
        list1.add(41);
//        list1.add("sss");// here it will give compile time error // but in another case it will give error on runtime so it provides type safety
        list1.add(451);
        System.out.println(list1);

        // we can use Object class to make general return type, but it can throw runtime exception
        Test test = new Test(45); // here we can pass any type of object
        System.out.println(test.getValue()); //45
        test.container = "changed to String value";
        System.out.println(test.getValue()); // so here we cannot get type safety, and it can throw exception on runtime next person wants int it will give string…
        Test test1 = new Test("water"); // here we can pass any type of object
        System.out.println(test1.getValue());//water

        // so we will use generics to make type safe because if we pass string it will give and take the same
        // first we create class box and pass <T> this as a type see below

        Container<String> container = new Container<String>("This is a string");
        Container<Integer> containerInt = new Container<Integer>(11); // here you see we pass int and above String
//        containerInt.value ="dfef";// here it gives compile time error on changing data type so it gives type safety
        containerInt.value = 23;
        System.out.println(container.value.getClass().getName());
        System.out.println(container.value);
        container.performTask();
        System.out.println(containerInt.value.getClass().getName());
        System.out.println(containerInt.value);
        containerInt.performTask();
    }
}

class Container<T> {
    T value;

    Container(T value) {
        this.value = value;
    }

    void performTask() {
        if (value instanceof String) {// mean its return type is String
            System.out.println("length of " + value + " is " + ((String) this.value).length());
        } else if (value instanceof Integer) {
            System.out.println("the integer value " + value);
        }
    }

    T getValue() {
        return value;
    }
}

class Test {
    Object container; // Object is the parent class of all java classes so it can take String int etc.

    Test(Object container) {
        this.container = container;
    }

    Object getValue() {
        return this.container;
    }
}