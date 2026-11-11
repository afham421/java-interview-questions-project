package JavaQuestions.ReferanceComparison_vs_ContentComparison;

import java.util.Objects;

/**
 * Reference comparison vs Content comparison
 *
 * ==      → compares references (same object in memory?)
 * equals  → should compare content (same data?)
 *
 * Object.equals() default = same as == (reference)
 * String overrides equals() → compares characters
 * Custom classes: override equals() (and hashCode()) yourself
 */
public class ReferenceComparison_vs_ContentComparison {

    public static void main(String[] args) {

        // ---------- Custom object ----------
        Students student1 = new Students(12, "Afham");
        Students student2 = new Students(12, "Afham");

        System.out.println("student1 == student2 : " + (student1 == student2));
        // false → different objects in memory (two `new`)

        System.out.println("student1.equals(student2) : " + student1.equals(student2));
        // true → our overridden equals compares id + name

        student1.studentId = 11; // change content
        System.out.println("After id change equals : " + student1.equals(student2));
        // false → ids differ now

        // ---------- String ----------
        String str1 = new String("Hello");
        String str2 = new String("Hello");

        System.out.println("str1 == str2 : " + (str1 == str2));
        // false → two different String objects

        System.out.println("str1.equals(str2) : " + str1.equals(str2));
        // true → String class already overrides equals (content)

        // Interview tip:
        // String pool case:
        String a = "Hello";
        String b = "Hello";
        System.out.println("pool a == b : " + (a == b)); // often true (same pooled ref)
        System.out.println("pool a.equals(b) : " + a.equals(b)); // true
    }
}

class Students {
    public int studentId;
    private String name;

    Students(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        // 1) same reference?
        if (this == o) return true;
        // 2) null or different class?
        if (o == null || getClass() != o.getClass()) return false;
        // 3) compare content fields
        Students other = (Students) o;
        return studentId == other.studentId && Objects.equals(name, other.name);
    }

    // Always override hashCode when you override equals (HashMap/HashSet contract)
    @Override
    public int hashCode() {
        return Objects.hash(studentId, name);
    }

    @Override
    public String toString() {
        return "Students{id=" + studentId + ", name='" + name + "'}";
    }
}
