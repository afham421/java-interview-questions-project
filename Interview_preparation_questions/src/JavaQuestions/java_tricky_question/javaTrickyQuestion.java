package JavaQuestions.java_tricky_question;

public class javaTrickyQuestion {

    public static void staticMethod() {
        System.out.println("Static method called.");
    }

    // Q: try return 1 + finally return 2 → result?
    int test() {
        try {
            return 1;
        } finally {
            return 2; // overrides try's return (BAD practice — can also swallow exceptions)
        }
    }

    public static void main(String[] args) {

        // ═══════ Q1: post-increment in condition ═══════
        // i++ == 5 → compare with 5 FIRST, then increment
        int i = 5;
        if (i++ == 5) {
            System.out.println("True");   // ✅ prints True (then i becomes 6)
        } else {
            System.out.println("False");
        }


        // ═══════ Q2: for-loop init can be any statement ═══════
        // Output: Start\n012  (option often written as "Start 0 1 2")
        // A Compilation Error
        // B Start 0 1 2
        // C Start 1 2 3
        // D Start 0 1 2 3
        // Answer: B
        int j = 0;
        for (System.out.println("Start"); j < 3; j++) {
            System.out.print(j);  // 0 then 1 then 2
        }
        // Init runs once → "Start". Condition j<3. Body prints j. Then j++.


        // ═══════ Q3: x++ vs ++x ═══════
        // A 5 7
        // B 5 6
        // C 6 6
        // D Compilation Error
        // Answer: A
        int x = 5;
        System.out.println();
        System.out.println(x++); // prints 5, then x = 6
        System.out.println(++x); // x = 7, then prints 7


        // ═══════ Q4: char + char → int addition, NOT String concat ═══════
        // "b"+"i"+"t" → "bit"
        // 'b'+'i'+'t' → 98+105+116 = 319  (char promotes to int)
        System.out.println('b' + 'i' + 't'); // 319


        // ═══════ Q5: Integer cache (-128 to 127) ═══════
        //
        // Reason (samajhne ke liye):
        // 1) Integer ii = 127;  → autoboxing → actually Integer.valueOf(127)
        // 2) valueOf() pehle CACHE check karta hai:
        //      cache range = -128 to 127  (JVM default, JLS guarantee)
        // 3) Agar number cache mein hai → SAME object return (reuse)
        // 4) Agar bahar hai (e.g. 128) → NEW Integer object har baar
        //
        // ==  → reference compare (same object in memory?)
        // equals() → value compare (number same?)
        //
        Integer ii = 127;
        Integer jj = 127;
        System.out.println(ii == jj); // true  ✅
        // 127 cache mein hai → ii aur jj SAME object point karte hain
        // isliye reference == bhi true

        Integer k = 128;
        Integer l = 128;
        System.out.println(k == l);   // false ❌
        // 128 cache ke bahar → har valueOf(128) NEW object banata hai
        // k aur l alag-alag objects → == false (references different)
        // values same hain, lekin == values check nahi karta

        System.out.println(k.equals(l)); // true ✅
        // Value compare: always use equals() for Integer/Long/etc.
        //
        // Dry run tip:
        //   -128..127  → == often true (cached)
        //   128, 1000… → == false (new objects), equals() still true
        //
        // Bonus: new Integer(127) == new Integer(127) → false ALWAYS
        //   (constructor cache bypass karta hai — deprecated since Java 9)


        // ═══════ Q6: static method via object reference ═══════
        javaTrickyQuestion obj = new javaTrickyQuestion();
        obj.staticMethod(); // works, but prefer: javaTrickyQuestion.staticMethod();

        // Tricky follow-up: null reference se bhi static call chal jata hai (no NPE)
        javaTrickyQuestion nullRef = null;
        nullRef.staticMethod(); // ✅ still prints — bind at compile time to class


        // ═══════ Q7: return in finally overrides try ═══════
        System.out.println(obj.test()); // 2
        // try returns 1, but finally ALWAYS runs and return 2 wins.
        // Interview tip: never return/throw from finally in real code.

    }
}
