package JavaQuestions;

//When can you use super keyword?
//The super keyword is used to access hidden fields and overridden methods or attributes of the parent class.
//Following are the cases when this keyword can be used:
//Accessing data members of parent class when the member names of the class and its child subclasses are same.
//To call the default and parameterized constructor of the parent class inside the child class.
//Accessing the parent class methods when the child classes have overridden them.
//The following example demonstrates all 3 cases when a super keyword is used.
class Parent1{
    protected int num = 1;

    Parent1(){
        System.out.println("Parent class default constructor.");
    }

    Parent1(String x){
        System.out.println("Parent class parameterised constructor.");
    }

    public void foo(){
        System.out.println("Parent class foo!");
    }
}
class Child1 extends Parent1{
    private int num = 2;

    Child1(){
        //super constructor call should always be in the first line
        // super();              // Either call default super() to call default parent constructor OR
        super("Call Parent");    // call parameterised super to call parameterised parent constructor.
        System.out.println("Child class default Constructor");
    }

    void printNum(){
        System.out.println(num);
        System.out.println(super.num); //prints the value of num of parent class
    }

    @Override
    public void foo(){
        System.out.println("Child class foo!");
        super.foo();    //Calls foo method of Parent class inside the Overriden foo method of Child class.
    }
}

public class SuperKeywordDemoClass {
    public static void main(String args[]) {
        Child1 demoObject=new Child1();
        demoObject.foo();
        demoObject.printNum();
     /*
      This would print -
      Parent class parameterized constructor.
      Child class default Constructor
      Child class foo!
      Parent class foo!
      2
      1
     */
    }

}
