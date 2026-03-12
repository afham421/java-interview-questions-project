package JavaQuestions;

public class SystemOutPrintln {
    public static void main(String[] args) {
        System.out.println("Hello World");

    }
}

//🔹 Definition
//System.out.println() is a Java statement used to print output to the console, followed by a new line.
//
//🔹 Breakdown of Components
//Part	Meaning
//System	A final class from java.lang package.
//out	A static field of type PrintStream inside System class.
//println()	A method of PrintStream used to print text and move the cursor to a new line.
//
//🔹 Full Explanation (Interview Perspective)
//"System.out.println() is used to display output to the console. Internally, System is a final class,
// and out is a static PrintStream object that represents the standard output stream.
// The println() method of PrintStream is invoked to print the value and then move the cursor to the next line."
//
//🔹 Key Technical Points (Interview Worthy)
//System class
//
//Part of java.lang (auto-imported)
//
//Cannot be instantiated (it's a final class)
//
//out field
//
//public static final PrintStream out
//
//Connected to the console output (standard output stream)
//
//println() method
//
//Overloaded for different data types (int, String, boolean, etc.)
//
//Automatically adds a newline after printing
//
//🔹 Bonus: print() vs println()
//| Method      | Difference                |
//| ----------- | ------------------------- |
//| `print()`   | Prints without a newline  |
//| `println()` | Prints and adds a newline |