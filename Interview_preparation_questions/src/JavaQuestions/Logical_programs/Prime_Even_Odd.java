package JavaQuestions.Logical_programs;

public class Prime_Even_Odd {

    public static void main(String[] args) {
        int number = 29;

        if (number % 2 == 0) {
            System.out.println(number + " is Even.");
        } else {
            System.out.println(number + " is Odd.");
        }

        if (isPrime(number)) {
            System.out.println(number + " is a Prime number.");
        } else {
            System.out.println(number + " is not a Prime number.");
        }
    }


    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false; // 0 and 1 are not prime numbers
        }
        for (int i = 2; i <= Math.sqrt(number); i++) { // check up to the square root of the number
            if (number % i == 0) {
                return false; // number is divisible by some i, not a prime number
            }
        }
        return true;
    }


}
