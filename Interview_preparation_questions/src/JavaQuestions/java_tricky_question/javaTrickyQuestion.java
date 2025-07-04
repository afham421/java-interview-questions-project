package JavaQuestions.java_tricky_question;

public class javaTrickyQuestion {
    public static void main(String[] args) {

        // Question 1: What will be the output of the following code?
        int i = 5;
        if (i++ == 5) { // it will print true because first it compares with 5 then increment
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        // Question 2: What will be the output of the following code?

        int j = 0;
        for (System.out.println("Start"); j < 3; j++) {
            System.out.println(j);
        }
//        A. Compilation Error
//        B. Start 0 1 2
//        C. Start 1 2 3
//        D. Start 0 1 2 3
//        Answer: B. Start 0 1 2
//        Explanation: The initialization block of the for loop can contain any statement. Here, System.out.println("Start") is executed once, and the loop prints the values of i from 0 to 2.

        // Question 3: What will be the output of the following code?
        int x = 5;
        System.out.println(x++);
        System.out.println(++x);
//        A. 5 7
//        B. 5 6
//        C. 6 6
//        D. Compilation Error
//        Answer: A. 5 7
//        Explanation: x++ prints 5 and then increments x to 6. ++x increments x to 7 before printing, so the second println outputs 7.

        // Question 4: What will be the output of the following code?




            //“bit” would have been the result printed if the letters were used in double-quotes (or the string literals). But the question has the character literals (single quotes) being used which is why concatenation wouldn't occur. The corresponding ASCII values of each character would be added and the result of that sum would be printed.
        //The ASCII values of ‘b’, ‘i’, ‘t’ are:
        //
        //‘b’ = 98
        //‘i’ = 105
        //‘t’ = 116
        //98 + 105 + 116 = 319
                System.out.println('b' + 'i' + 't');









    }
}
