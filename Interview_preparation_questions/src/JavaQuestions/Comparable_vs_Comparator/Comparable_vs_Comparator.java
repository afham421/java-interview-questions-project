package JavaQuestions.Comparable_vs_Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comparable_vs_Comparator {

//    When to Use Which?
//    Use Comparable: When you want to define a natural order that is intrinsic to the object itself. For example, sorting by ID in a class where ID is a unique identifier.
//
//    Use Comparator: When you need to define multiple or complex sorting criteria, or when you want to sort objects in a way that isn't their natural order.
//    It's also useful when you cannot modify the class whose objects you want to sort.

//    Features                       comparable                                      comparator
//    Interface Location	         Implemented by the class to be compared	     Implemented by a separate class
//    Method	                     compareTo(T o)	                                 compare(T o1, T o2)
//    Sort Order	                 Defines natural (default) order	             Defines custom order
//    Number of Sorting Sequences	 Single sorting sequence	                     Multiple sorting sequences
//    Modification of Class        	Requires modification of the class             	Does not require modification of the class
//    Usage	                        Used when there's a single natural order	    Used when different sorting criteria are needed


    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3, "Alice"));
        employees.add(new Employee(1, "Bob"));
        employees.add(new Employee(2, "Charlie"));
//for comparable
        Collections.sort(employees);  // Sorts based on natural order (id)
        System.out.println(employees); // comparable main ap aik hi terha ki sorting ker sakty ho at a time ya id sy ya name sy q k ap usi class main implement kerty hain

//for comparator
        Collections.sort(employees, new EmployeeNameComparator());  // Sorts by name
        System.out.println(employees); // but in this case we implement in another new class
        Collections.sort(employees, new EmployeeIdComparator());  // Sorts by id
        System.out.println(employees);

    }

}


class EmployeeIdComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getId() - o2.getId();
    }
}

class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) { // ab is main yeh hy k ab ap name k sath sort compare ker rhy ho agr id k sath kerna tu aik new calss bnao or id k sath ker lo
        //is sy benifit yeh hota k ap two list per different sorting ker sakty ho q k
        // Collections.sort(employees, new JavaQuestions.Comparable_vs_Comparator.EmployeeNameComparator());  //yahan apko aik class ka object pass kerna us main agr id k related ho gi tu id ni tu name
        //but comparable main ap sirf list hi pass kerty ho   Collections.sort(employees);
        return e1.getName().compareTo(e2.getName()); // Custom order by name
    }
}





class Employee implements Comparable<Employee> {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // comparable main ap aik hi terha ki sorting ker sakty ho at a time ya id sy ya name sy q k interface ka method aik hi bar override ho ga
    @Override
    public int compareTo(Employee other) {
        return this.id - other.id; // Natural order by id
//        return Integer.compare(this.id, other.id); // Natural order by id

    }

//     @Override
//     public int compareTo(JavaQuestions.Comparable_vs_Comparator.Employee other) {
//         return this.name.compareTo(other.name); // Natural order by name
//     }

    // Getters and toString()...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JavaQuestions.Comparable_vs_Comparator.Employee{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
