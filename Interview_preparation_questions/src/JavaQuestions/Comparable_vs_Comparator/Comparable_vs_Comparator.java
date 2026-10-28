package JavaQuestions.Comparable_vs_Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comparable_vs_Comparator {

//    When to Use Which?
//    Use Comparable: When you want to define a natural order that is intrinsic to the object itself.
//    For example, sorting by ID in a class where ID is a unique identifier.
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

//    KEY IDEA (easy memory):
//    Comparable  -> "I can compare MYSELF with another object"   (inside the class)   -> Collections.sort(list)
//    Comparator  -> "I can compare TWO objects from OUTSIDE"     (separate class)     -> Collections.sort(list, comparator)

//    Return value of compareTo / compare (same meaning for both):
//    negative  => this/o1 comes BEFORE o2
//    zero      => both are equal for sorting
//    positive  => this/o1 comes AFTER o2


    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3, "Alice"));
        employees.add(new Employee(1, "Bob"));
        employees.add(new Employee(2, "Charlie"));

        System.out.println("1) Original list:");
        System.out.println(employees);
        System.out.println();

//for comparable
        // Employee already implements Comparable -> sort uses compareTo() (natural order = id)
        Collections.sort(employees);  // Sorts based on natural order (id)
        System.out.println("2) After Comparable sort (by id) -> Collections.sort(employees)");
        System.out.println(employees);
        // comparable main ap aik hi terha ki sorting ker sakty ho at a time ya id sy ya name sy
        // q k ap usi class main implement kerty hain, or compareTo() aik hi bar override hota hai
        System.out.println();

//for comparator
        Collections.sort(employees, new EmployeeNameComparator());  // Sorts by name
        System.out.println("3) After Comparator sort (by name) -> Collections.sort(employees, new EmployeeNameComparator())");
        System.out.println(employees);
        // but in this case we implement in another new class
        System.out.println();

        Collections.sort(employees, new EmployeeIdComparator());  // Sorts by id
        System.out.println("4) After Comparator sort (by id) -> Collections.sort(employees, new EmployeeIdComparator())");
        System.out.println(employees);
        System.out.println();

        // SAME thing with Java 8 lambda / method reference (interview favorite)
        // No need to create separate Comparator classes for simple cases
        employees.sort(Comparator.comparing(Employee::getName));
        System.out.println("5) Java 8 Comparator.comparing(Employee::getName)  -> same as EmployeeNameComparator");
        System.out.println(employees);

        employees.sort(Comparator.comparingInt(Employee::getId).reversed());
        System.out.println("6) Comparator.comparingInt(Employee::getId).reversed()  -> id descending");
        System.out.println(employees);

        // multi-field sort: first by name, if name same then by id
        employees.add(new Employee(4, "Alice")); // duplicate name to show secondary sort
        employees.sort(Comparator.comparing(Employee::getName).thenComparingInt(Employee::getId));
        System.out.println("7) Multi-field: name ASC, then id ASC  (Comparator power = multiple strategies)");
        System.out.println(employees);
    }

}


class EmployeeIdComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        // o1.id - o2.id  => ascending by id
        // if result negative, o1 comes first
        return o1.getId() - o2.getId();
        // safer for large numbers: return Integer.compare(o1.getId(), o2.getId());
    }
}

class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        // ab is main yeh hy k ab ap name k sath sort compare ker rhy ho
        // agr id k sath kerna tu aik new class bnao or id k sath ker lo
        // is sy benefit yeh hota k ap same list per different sorting ker sakty ho
        // Collections.sort(employees, new EmployeeNameComparator());  // yahan comparator object pass hota hai
        // but comparable main ap sirf list hi pass kerty ho: Collections.sort(employees);
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

    // comparable main ap aik hi terha ki sorting ker sakty ho at a time
    // ya id sy ya name sy q k interface ka method aik hi bar override ho ga
    @Override
    public int compareTo(Employee other) {
        return this.id - other.id; // Natural order by id
//        return Integer.compare(this.id, other.id); // safer Natural order by id
    }

//     @Override
//     public int compareTo(Employee other) {
//         return this.name.compareTo(other.name); // Natural order by name (uncomment to switch)
//     }

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
        return "Employee{id=" + id + ", name='" + name + "'}";
    }
}

// One-Line Summary
// Comparable = natural order inside the class (one default way)  -> sort(list)
// Comparator = custom order outside the class (many ways)       -> sort(list, comparator)
