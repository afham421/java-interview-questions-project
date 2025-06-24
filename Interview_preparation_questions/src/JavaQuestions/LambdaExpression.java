package JavaQuestions;

//Lamda is an anonymous function with no name, no return type and no modifiers
//In Java, a lambda expression is a concise way to express instances of single-method interfaces (functional interfaces).
public class LambdaExpression {
    public static void main(String[] args) {
// First way to implement interface in class
//        MyTestImpl myTest = new MyTestImpl();
        MyTest myTest = new MyTestImpl(); // you can create an object with parent class reference
        myTest.test(); // calling method of functional interface

//2nd way we use anonymous class means we create an object of interface actually we use anonymous class
        // means new myTest() { now this curly braces are the anonymous class object ,anonymous class has not name
        //means we implement interface method with anonymous class so we use new MyTest() { now this curly braces are the anonymous class}
        MyTest my = new MyTest() {
            @Override
            public void test() {
                System.out.println("Calling anonymous class");
            }
        };
        my.test(); // calling method of functional interface

// 3rd way is by lambda expression
        MyTest myLambda = () -> System.out.println("Calling Lambda Expression"); // by using this we dont need to implement interface into class nad then not need to create object
        // and then getting method with the object reference
        myLambda.test(); // calling method of functional interface

        //anonymous class   Vs  lambda expression
//        anonymous class can override one or more than one method, but lambda can be used in functional interface

    }

}

@FunctionalInterface
interface MyTest { // in functional interface we have only one abstract method
    void test();
//    void test1();
}

class MyTestImpl implements MyTest {
    @Override
    public void test() {
        System.out.println("Calling Functional Interface.................................");
    }
}