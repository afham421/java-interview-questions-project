package JavaQuestions.Logical_programs;

import java.util.Scanner;

public class Whitespace_Uppercase_LowerCase {
    public static void main(String[] args) {
        System.out.println("Enter string to find white space,uppercase,lowercase");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);

        int spaces = 0;
        int upper = 0;
        int lower = 0;
        int other = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
//            // built-in logic
//            if (Character.isWhitespace(c)) {
//                spaces++;
//            } else if (Character.isUpperCase(c)) {
//                upper++;
//            } else if (Character.isLowerCase(c)) {
//                lower++;
//            } else {
//                other++;
//            }
//       // custom logic
            if (c == 32){
                spaces++;
            } else if (c >= 65 && c <= 90) { //         if (c>='a' && c <='z'){ // we can use this also
                upper++;
            } else if (c >= 97 && c <= 122) { //     if (c>='A' && c <='Z'){
                lower++;
            }else {
                other++;
            }
        }
        System.out.println("Number of spaces: " + spaces);
        System.out.println("Number of uppercase letters: " + upper);
        System.out.println("Number of lowercase letters: " + lower);
        System.out.println("Number of other characters: " + other);

    }
}
