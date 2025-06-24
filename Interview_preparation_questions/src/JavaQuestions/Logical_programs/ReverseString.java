package JavaQuestions.Logical_programs;

public class ReverseString {

    public static void main(String[] args) {
        String str = "Hello World!";
        System.out.println("Original String: " + str);
        String reversedStr = "";

        for (int i=str.length()-1; i>=0; i--){
//            System.out.print(str.charAt(i));
            reversedStr += str.charAt(i); // append character at current index to reversedStr string
        }
        System.out.println("Reversed String: " + reversedStr);

        StringBuilder sb = new StringBuilder(str); // we can use StringBuilder for reverse string
        String reversed = sb.reverse().toString();
        System.out.println("Reversed String via StringBuilder: " + reversed);

        String reversedByRecursion = reverse(str);
        System.out.println("Reversed String via Recursion: " + reversedByRecursion);


    }

    public static String reverse(String str) {
        if (str.isEmpty()) { //base case
            return str;
        }
        return reverse(str.substring(1)) + str.charAt(0); //skip first character and add it to the last
    }
}
