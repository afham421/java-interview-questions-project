package JavaQuestions;

public class FinallyBlock {


//    When the finally Block Runs:
//    Normal Execution: If no exceptions are thrown in the try block, the finally block will execute after the try block completes.
//    Exception Thrown and Caught: If an exception is thrown in the try block and is caught by a catch block, the finally block will still execute after the catch block.
//    Exception Thrown but Not Caught: If an exception is thrown in the try block but is not caught (or is re-thrown), the finally block will execute before the exception propagates.
//    When the finally Block May Not Run:
//    JVM Termination: If the JVM exits during the try or catch block execution, such as by calling System.exit(), the finally block will not execute.
//    Infinite Loops or Deadlocks: If the try or catch block enters an infinite loop or deadlock situation, the finally block will never be reached.
//    Power Failure or Hardware Issues: In cases of a power failure, hardware issue, or similar catastrophic event, the finally block obviously won't run.
//    Thread Interruption or Forceful Termination: If the thread executing the try block is interrupted or terminated (e.g., using Thread.stop()), the finally block may not execute.
    public static void main(String[] args) {
        try {
            System.out.println("In try block");
            // Uncommenting the next line will prevent finally from running
//             System.exit(0); // it will not allow to run finally block

        } catch (Exception e) {
            System.out.println("In catch block");
        } finally {
            System.out.println("In finally block");
        }




    }

}
