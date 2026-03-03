package JavaQuestions;

public class ExceptionExample {

    public static void main(String[] args) {


//// without handling
//        int a = 10;
//        int b = 0;
//        int result = a / b;


// with handling

        try {
            int a = 10;
            int b = 0;
            int result = a / b;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }
        System.out.println("Program continues...");









    }

}
