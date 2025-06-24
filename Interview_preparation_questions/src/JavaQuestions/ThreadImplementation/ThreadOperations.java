package JavaQuestions.ThreadImplementation;

public class ThreadOperations {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("program start");
        int sum = 12 + 13;
        System.out.println("sum = " + sum);
        Thread thread = Thread.currentThread(); // for getting current thread
        System.out.println("Thread name is : " + thread.getName());
        thread.setName("new name"); // for setting thread name
        System.out.println("Thread name is : " + thread.getName());
        thread.sleep(3000); // it will take 3 sec sleep for doing tasks
        System.out.println("id of thread is : " + thread.getId());

        System.out.println("program end");
    }
}
