package JavaQuestions.DSA;

import java.math.BigInteger;

/**
 * Check which Java primitive integer types a number can fit into.
 * (HackerRank-style: Java Datatypes)
 *
 * Ranges:
 * byte  → -128 .. 127
 * short → -32,768 .. 32,767
 * int   → -2^31 .. 2^31-1
 * long  → -2^63 .. 2^63-1
 *
 * If it fits in byte, it also fits in short/int/long (nested ranges).
 */
public class CheckDataTypeFit {

    public static void main(String[] args) {
        String[] inputs = {
                "-120",
                "150",
                "150000",
                "1500000000",
                "213333333333333333333333333333333333",
                "-100000000000000"
        };

        for (String input : inputs) {
            printFit(input);
            System.out.println();
        }
    }

    /**
     * Your style: parse as long, compare with MIN/MAX constants
     * Fails (Exception) if number is outside long range → "can't be fitted anywhere"
     */
    public static void printFitUsingLong(String input) {
        try {
            long x = Long.parseLong(input);
            System.out.println(x + " can be fitted in:");
            if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) System.out.println("* byte");
            if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) System.out.println("* short");
            if (x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE) System.out.println("* int");
            if (x >= Long.MIN_VALUE && x <= Long.MAX_VALUE) System.out.println("* long");
        } catch (NumberFormatException e) {
            System.out.println(input + " can't be fitted anywhere.");
        }
    }

    /**
     * Smarter way: BigInteger comparisons (no try/catch needed for huge numbers)
     * Works for values bigger than long without relying on parse exception.
     * Time: O(d) for digit parsing, comparisons are fine for interview use
     */
    public static void printFit(String input) {
        BigInteger x;
        try {
            x = new BigInteger(input.trim());
        } catch (NumberFormatException e) {
            System.out.println(input + " can't be fitted anywhere.");
            return;
        }

        BigInteger byteMin = BigInteger.valueOf(Byte.MIN_VALUE);
        BigInteger byteMax = BigInteger.valueOf(Byte.MAX_VALUE);
        BigInteger shortMin = BigInteger.valueOf(Short.MIN_VALUE);
        BigInteger shortMax = BigInteger.valueOf(Short.MAX_VALUE);
        BigInteger intMin = BigInteger.valueOf(Integer.MIN_VALUE);
        BigInteger intMax = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger longMin = BigInteger.valueOf(Long.MIN_VALUE);
        BigInteger longMax = BigInteger.valueOf(Long.MAX_VALUE);

        boolean fitsLong = x.compareTo(longMin) >= 0 && x.compareTo(longMax) <= 0;
        if (!fitsLong) {
            System.out.println(input + " can't be fitted anywhere.");
            return;
        }

        System.out.println(input + " can be fitted in:");
        if (x.compareTo(byteMin) >= 0 && x.compareTo(byteMax) <= 0) System.out.println("* byte");
        if (x.compareTo(shortMin) >= 0 && x.compareTo(shortMax) <= 0) System.out.println("* short");
        if (x.compareTo(intMin) >= 0 && x.compareTo(intMax) <= 0) System.out.println("* int");
        System.out.println("* long"); // already known to fit in long
    }
}
