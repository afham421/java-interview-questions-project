package JavaQuestions;

public class ParentVsChildMethodRunOnInstance {

    public static void main(String[] args) {
        Parent s = new Child();
//        Child s = new Child();
        s.foo(); // it will get from reference like if it is parent then it will get parent method
    }
}

class Parent {
    static void foo() {
        System.out.println("Parent");
    }
}


class Child extends Parent {
    static void foo() { // method overrride of parent should be same like if static then static
        System.out.println("Child");
    }
}

