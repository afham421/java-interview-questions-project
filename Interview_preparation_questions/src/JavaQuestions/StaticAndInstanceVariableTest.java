package JavaQuestions;

public class StaticAndInstanceVariableTest {


    // Static variable (shared across all instances)
    static Integer staticNumber = 100;

    static int x = 10; //// Static variable is common between all objects if anyone changes the value update in all places
    int y = 12;
    // Instance variable (unique to each object)
    Integer instanceNumber;

    // Constructor
    public StaticAndInstanceVariableTest(Integer value) {
        this.instanceNumber = value;
    }

    public static void main(String[] args) {

        // Accessing static variable via class name
        System.out.println("Static Number (via class): " + StaticAndInstanceVariableTest.staticNumber);

        // Creating objects
        StaticAndInstanceVariableTest obj1 = new StaticAndInstanceVariableTest(200);
        StaticAndInstanceVariableTest obj2 = new StaticAndInstanceVariableTest(300);

        obj1.x += 10;
        obj2.x += 20;

        // Accessing instance and static variables via object
        System.out.println("Object 1 - Instance Number: " + obj1.instanceNumber);
        System.out.println("Object 2 - Instance Number: " + obj2.instanceNumber);
        System.out.println("Static Number (via object): " + obj1.staticNumber); // not recommended, but legal

        // Changing static value using class
        StaticAndInstanceVariableTest.staticNumber = 999;

        System.out.println("Static Number (after change): " + StaticAndInstanceVariableTest.staticNumber);
        System.out.println("Static Number (via obj2): " + obj2.staticNumber);


        System.out.println("obj1.x>>>>>>" +  obj1.x );   // Static variable is common between all objects if anyone changes the value update in all places
    }


}
