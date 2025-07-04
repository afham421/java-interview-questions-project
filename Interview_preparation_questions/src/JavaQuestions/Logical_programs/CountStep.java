package JavaQuestions.Logical_programs;

public class CountStep {
    // A simple recursive program to find
    // n'th fibonacci number
    static int fib(int n)
    {
        if (n <= 1)
            return n;

        return fib(n - 1) + fib(n - 2);
    }

    // Returns number of ways to reach s'th stair
    static int countWays(int s)
    {
        return fib(s + 1);
    }

    /* Driver program to test the above function */
    public static void main(String args[])
    {
        int s = 5 , n= 4;
        System.out.println("Number of ways = " + countWays(s)+ "   "+fib(n));
    }

}

