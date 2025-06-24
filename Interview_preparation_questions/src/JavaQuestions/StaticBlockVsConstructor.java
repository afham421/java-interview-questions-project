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
        System.out.println("Constructor executed");
    }
static class children extends Example {
//static
    static {
        System.out.println("Static block executed");
    }
    // Constructor
    public children() {
        System.out.println("Constructor executed");
    }
}

}
class Test{
    public static void main(String[] args) {
        // Creating the first instance
        Example obj1 = new Example.children();

        // Creating the second instance
        Example obj2 = new Example.children();
    }
}
