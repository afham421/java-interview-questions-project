package JavaQuestions.SerializationVsDeSerialization;

import java.io.*;

public class SerializationVsDeSerialization {
//    Serialization and deserialization are mechanisms in Java that allow you to convert an object into a byte stream (serialization) and then back into an object (deserialization).
//    This process is useful for saving objects to a file, sending them over a network, or persisting them in databases.
//    When to Use Serialization/Deserialization
//    Persistence: Saving an object's state to a file or database.
//    Networking: Sending objects over a network (e.g., RMI, messaging systems).
//    Caching: Storing objects temporarily in memory or disk.
    public static void main(String[] args) {

        Testing testing = new Testing("Afham", 25);
        System.out.println("serialization started");
        try (FileOutputStream fileOut = new FileOutputStream("testing.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(testing);
            System.out.println("Serialization completed");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("De-serialization started");

        try (FileInputStream fileIn = new FileInputStream("testing.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Testing testing1 = (Testing) in.readObject();
            System.out.println("De-serialization completed");
            System.out.println("Name: " + testing1.name);
            System.out.println("Age: " + testing1.age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

////////////////////////////Marker interface////////////////
//A marker interface is a special type of interface in Java that does not contain any methods or fields. Instead, it is used to "mark" or "tag" a class with specific properties
// or behavior, allowing the Java runtime or other code to recognize that the class has certain capabilities or should be treated in a certain way.
//Marker Interface: An interface with no methods, used to mark classes with special properties or behavior.
//Common Examples: Serializable, Cloneable, Remote.
//Purpose: To signal to the Java runtime or other code that a class has specific capabilities or should be treated in a certain way.
//Modern Usage: Marker interfaces are still used, but annotations offer a more flexible alternative in some cases.

//Implementing Serializable: To serialize an object, the class must implement the Serializable interface. This is a marker interface,
// meaning it doesn't have any methods to implement, but it tells the Java Virtual Machine (JVM) that the object can be serialized.
//Serializing an Object: Use the ObjectOutputStream to write the object to an OutputStream, such as a file.
class Testing implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; //The serialVersionUID is used to ensure that the same class (or a compatible version) is being used during deserialization.
    // If the serialVersionUID does not match, an InvalidClassException will be thrown.
    public String name; //transient Keyword: Fields marked as transient will not be serialized.This is useful for sensitive data or fields that do not need to be persisted.
    public int age;
    private transient String password; // This field will not be serialized

    public Testing(String name, int age) {
        this.name = name;
        this.age = age;
    }
}