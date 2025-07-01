package JavaQuestions;

class Example {
    static int s = 9;

    // Static block
    static {
        s = 10; // you can use only static variable in static method
        System.out.println("Static block executed");
    }

    // Constructor
    public Example() {
        System.out.println("parent Constructor executed");
    }

    static class children extends Example {
        //static
        static {
            System.out.println("Static block executed");
        }

        // Constructor
        public children() {
            System.out.println("child Constructor executed");
        }
    }

}

class Test {
    public static void main(String[] args) {

//        Static int i = 10;   //In Java, static variables must be declared at the class level, not inside a method.
////You can’t declare a static variable inside any method, constructor, or block. Variables inside methods are local variables, and the static keyword is only valid for class-level variables.

        // Creating the first instance
        Example obj = new Example();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        Example obj1 = new Example.children();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");

        // Creating the second instance
        Example obj2 = new Example.children();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");


    }

}
