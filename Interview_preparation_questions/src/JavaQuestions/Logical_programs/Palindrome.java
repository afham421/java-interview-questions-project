package JavaQuestions.Logical_programs;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("myym"));  // Call the method to check if a number is palindrome or not.
        System.out.println(isPalindromeByTwoParts("1221"));  // Call the method to check if a string is palindrome or not.
    }


    public static Boolean isPalindrome(String s) {
        String original, reverse = "";
        int length;
        original = s ;
        length = original.length();
        for (int i = length - 1; i >= 0; i--) {
            reverse = reverse + original.charAt(i);
        }
        System.out.println("reverse is:" + reverse);

        if (original.equals(reverse)) {// palindrome means its reverse is also same
            System.out.println("The number is palindrome");
            return true;
        }
        else{
            System.out.println("The number is not a palindrome");
        return false;
        }
    }

    public static boolean isPalindromeByTwoParts(String s) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanedString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        System.out.println(cleanedString);
        int left = 0;
        int right = cleanedString.length() - 1;

        while (left < right) {
            if (cleanedString.charAt(left) != cleanedString.charAt(right)) {
                System.out.println("The number is not a palindrome");
                return false; // Characters don't match, not a palindrome
            }
            left++;
            right--;
        }
        System.out.println("The number is palindrome");
        return true; // All characters match, it is a palindrome
    }
}