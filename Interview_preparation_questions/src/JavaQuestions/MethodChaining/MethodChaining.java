package JavaQuestions.MethodChaining;

/**
 * Method Chaining
 *
 * Idea:
 * A method returns `this` (the same object), so you can call the next method
 * on the returned object in one expression.
 *
 * Normal:
 *   person.setName("A");
 *   person.setAge(20);
 *   person.setCity("ISB");
 *
 * Chained:
 *   person.setName("A").setAge(20).setCity("ISB");
 *
 * Requirement for chaining setters:
 *   return type = same class (or a type that still exposes next methods)
 *   usually: return this;
 */
public class MethodChaining {

    public static void main(String[] args) {

        // ---------- 1) Without chaining ----------
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(12);
        person.setCity("isb");
        System.out.println(person); // Person{name='John Doe', age=12, city='isb'}

        // ---------- 2) With chaining (same object updated step by step) ----------
        // Step A: setName returns person
        // Step B: setAge is called on that same person, returns person
        // Step C: setCity called on same person
        person.setName("Jane Doe").setAge(25).setCity("Los Angeles");
        System.out.println(person);

        // Multi-line chaining (same thing, just formatted)
        person
                .setName("kaka")
                .setCity("pindi")
                .setAge(23);
        System.out.println(person);

        // ---------- 3) Private constructor + static factory + chaining ----------
        // new Student() is illegal (private ctor)
        // Student.of() creates instance, then chain setters
        Student s = Student
                .of()
                .setName("test")
                .setSubject("Math")
                .setRollNo(11);
        System.out.println(s);

        // ---------- 4) Return type decides next methods ----------
        // toPerson() returns Person, so AFTER that you can call only Person methods
        // (setAge/setCity), not Student methods (setRollNo/setSubject).
        Person p = Student
                .of()
                .setName("Ali")          // returns Student
                .setSubject("Physics")   // returns Student
                .toPerson()              // returns Person  ← chain type switches here
                .setAge(20)              // Person method OK
                .setCity("ISB");         // Person method OK
        // .setRollNo(11);               // ❌ compile error — Person has no setRollNo
        System.out.println(p);
    }
}

/**
 * Private ctor → cannot `new Student()` from outside.
 * Static factory `of()` returns Student so chaining can start.
 */
class Student {
    private String name;
    private String subject;
    private int rollNo;

    private Student() {
    }

    public static Student of() {
        return new Student();
    }

    public Student setName(String name) {
        this.name = name;
        return this; // enable chaining
    }

    public Student setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Student setRollNo(int rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    /**
     * Example of switching chain type:
     * After this method, compiler sees Person, so next calls are Person APIs.
     */
    public Person toPerson() {
        return new Person()
                .setName(this.name)
                .setAge(0)
                .setCity("unknown");
    }

    public String getName() { return name; }
    public String getSubject() { return subject; }
    public int getRollNo() { return rollNo; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', subject='" + subject + "', rollNo=" + rollNo + "}";
    }
}

class Person {
    private String name;
    private int age;
    private String city;

    public Person setName(String name) {
        this.name = name;
        return this; // key for method chaining
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public Person setCity(String city) {
        this.city = city;
        return this;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCity() { return city; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", city='" + city + "'}";
    }
}
