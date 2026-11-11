package JavaQuestions.SerializationVsDeSerialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

/**
 * Serialization   = object → byte stream (save/send)
 * Deserialization = byte stream → object (read/rebuild)
 *
 * Uses:
 * - save object to file
 * - send object over network
 * - cache/persist object state
 *
 * Rules:
 * 1) Class must implement Serializable (marker interface)
 * 2) serialVersionUID keeps class versions compatible
 * 3) transient fields are NOT saved
 */
public class SerializationVsDeSerialization {

    public static void main(String[] args) {
        String file = "testing.ser";

        // ---------- Serialization (object → file) ----------
        Testing original = new Testing("Afham", 25, "secret123");
        System.out.println("Before serialize: " + original);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(original); // write full object graph (that is serializable)
            System.out.println("Serialization completed → " + file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ---------- Deserialization (file → object) ----------
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Testing restored = (Testing) in.readObject();
            System.out.println("Deserialization completed");
            System.out.println("After deserialize: " + restored);
            // name/age restored; password is null because it was transient
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Marker interface reminder:
 * Serializable has NO methods. It only marks: "this class can be serialized".
 * Other markers: Cloneable, Remote.
 */
class Testing implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    // Keep this stable. If missing, JVM generates one; class changes can break old .ser files.

    public String name;
    public int age;
    private transient String password; // NOT written to stream

    public Testing(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Testing{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }
}
