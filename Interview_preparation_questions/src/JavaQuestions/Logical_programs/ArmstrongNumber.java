package JavaQuestions.Logical_programs;

import java.util.Scanner;

public class ArmstrongNumber {

    public static void main(String[] args)  {
        int c=0,a,temp;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any number to check: ");
        int n=scanner.nextInt();//It is the number to check Armstrong
        int number = n; // this is not for first way it is for 2nd way
        temp=n;
        while(n > 0)
        {
            a=n%10;
            n=n/10;
            c=c+(a*a*a);
        }
        System.out.println("By 1st way");
        if(temp==c)
            System.out.println("armstrong number");
        else
            System.out.println("Not armstrong number");



        System.out.println("By 2nd way");
        if (isArmstrong(number)) {
            System.out.println(number + " is an Armstrong number.");
        } else {
            System.out.println(number + " is not an Armstrong number.");
        }
    }


    public static boolean isArmstrong(int number) {
        int originalNumber = number;
        int sum = 0;
        int digits = String.valueOf(number).length();

        while (number > 0) {
            int digit = number % 10;
            sum += Math.pow(digit, digits);
            number /= 10;
        }

        return sum == originalNumber;
    }
}