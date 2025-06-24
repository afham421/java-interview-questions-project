package JavaQuestions.MethodChaining;

public class MethodChaining {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John Doe");
        person.setAge(12);
        person.setCity("isb");
        System.out.println(person);  // Output: JavaQuestions.MethodChaining.Person{name='John Doe', age=30, city='New York'}

        person.setName("Jane Doe").setAge(25).setCity("Los Angeles");
        System.out.println(person);

        person
                .setName("kaka")
                .setCity("pindi")
                .setAge(23);
        System.out.println(person);


//        JavaQuestions.MethodChaining.Student student = new JavaQuestions.MethodChaining.Student();// it will give error because constructor is private

       Student s = Student
                .of()
                .setName("test")
                .setSubject("Math")
                .setRollNo(11);
        System.out.println(s);

//        JavaQuestions.MethodChaining.Person p = JavaQuestions.MethodChaining.Student // if you want to access the both person and student class method in one method chain by using inheritance means you extend the other class and access all
//                .of()
//                .setName("test")
//                .setSubject("Math") // now here i change the return type to JavaQuestions.MethodChaining.Person so it gives error and showing the person class methods
//                .setAge(10);
//        System.out.println(p);
    }

}

//if we have a class whose constructor is private, and we cannot make an object of that class
// so we can get all methods by creating static method with same class return type
class Student{
    private String name;
    private String subject;
    private int rollNo;

    private Student() {
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getSubject() {
        return subject;
    }

//    public JavaQuestions.MethodChaining.Person setSubject(String subject) {
//        this.subject = subject;
//        return new JavaQuestions.MethodChaining.Person();
//    }
    public Student setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getRollNo() {
        return rollNo;
    }

    public Student setRollNo(int rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    @Override
    public String toString() {
        return "JavaQuestions.MethodChaining.Student{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", rollNo=" + rollNo +
                '}';
    }

    public static Student of(){  // so we can get all methods by creating static method with same class return type
        return new Student();
    }

}

class Person {

    private String name;
    private int age;
    private String city;

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
    public Person setName(String name) { // for setting values in one line or in method chaining form
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {// we can get method in method chaining on the base of return type here we get JavaQuestions.MethodChaining.Person class method
        // but if we have other class(like student) return type, then we get student class method in method chaining on the base of return type
        // we can set values in method chaining form when we have the same return type os setter method
        this.age = age;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Person setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String toString() {
        return "JavaQuestions.MethodChaining.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

//    JavaQuestions.MethodChaining.Person person = new JavaQuestions.MethodChaining.Person("John", 30, "New York");
//    System.out.println(person.toString()); // Outputs: JavaQuestions.MethodChaining.Person{name='John', age=30, city='New York'}
//    System.out.println(super.toString()); // Outputs something like: JavaQuestions.MethodChaining.Person@6d06d69c (if called within the JavaQuestions.MethodChaining.Person class)

}