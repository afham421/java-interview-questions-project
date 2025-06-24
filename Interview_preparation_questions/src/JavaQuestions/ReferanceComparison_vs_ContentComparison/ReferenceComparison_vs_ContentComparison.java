package JavaQuestions.ReferanceComparison_vs_ContentComparison;

import java.util.Objects;

public class ReferenceComparison_vs_ContentComparison  {
    public static void main(String[] args) {
        Students student1 = new Students();
        Students student2 = new Students();
        System.out.println(student1==student2); // here it is a reference comparison
        System.out.println(student1.equals(student2)); // here it called equal method from Object class that also do "==" internally. so it also returns false
        //jab tak hm student class main equals() method override nhin krin gy yeh object class sy call kry ga
        System.out.println(student1.equals(student2)); // now i override equals() method so it returns true
        //after override, we can write our logic in which we can compare id or name or both
        student1.studentId = 11; // here we override studentId so it returns false
        System.out.println(student1.equals(student2));

        String str1 = new String("Hello");
        String str2 = new String("Hello");

        if (str1 == str2) { // here reference is different in
            System.out.println("References are the same");
        } else {
            System.out.println("References are different");
        }
        if (str1.equals(str2)) { //here it will give true because String class provide the override equal() method so that it gives true,
            // but in case of a different custom object, we would override the equal method in the class
            System.out.println("Contents are the same");
        } else {
            System.out.println("Contents are different");
        }

    }
}

class Students{
    public int studentId = 12;
    private String name = "Afham";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        System.out.println("Comparing objects with our override method" );
        return studentId == students.studentId && Objects.equals(name, students.name);
    }

}