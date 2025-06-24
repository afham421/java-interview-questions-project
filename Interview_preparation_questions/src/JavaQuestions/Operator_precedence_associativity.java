package JavaQuestions;

public class Operator_precedence_associativity {
    public static void main(String[] args) {
        int a = 5, b = 10, c = 15;
        int result = (a + b) * c / 2; // Parentheses are evaluated first
        System.out.println("Result: " + result); // Output: 105
//precedence is first multiply or divide first and then add or subtract(means +,- has less precedence than *,/ )
        result = a + b * c / 2; // Multiplication and division are evaluated from left to right  // this process is called Associativity
        System.out.println("Result: " + result); // Output: 105

        result = a + (b * c) / 2; // Parentheses are evaluated first
        System.out.println("Result: " + result); // Output: 105

        result = (a + b) * (c / 2); // Parentheses are evaluated first
        System.out.println("Result: " + result); // Output: 105

        result = a + b * c / 2; // Multiplication and division
    }
}
//Java Operator Precedence and Associativity Table
//Precedence Level                      	Operators                               	Associativity	                                        Description
//1	                        [], (), .	                                             Left to Right	                                 Array access, method call, member access
//2	                        ++, --, + (unary), - (unary), ~, !	                                Right to Left	              Unary post-increment, post-decrement, negation, logical NOT
//3                     	*, /, %                             	            Left to Right	                                    Multiplication, division, modulus
//4	                        +, -	                                              Left to Right	                                        Addition, subtraction
//5                     	<<, >>, >>>	                                Left to Right	                                        Bitwise left shift, right shift, unsigned right shift
//6                     	<, >, <=, >=, instanceof	                                Left to Right	                                  Relational operators, instanceof
//7	                        ==, !=	                                Left to Right	                                                   Equality operators
//8                     	&	                                Left to Right	                                                     Bitwise AND
//9	                        ^	                                Left to Right	                                                     Bitwise XOR
//10                        	`	`	                                                                                            Left to Right
//11                        	&&	                                Left to Right	                                                  Logical AND
//12                        	`		`
//13                        	? :	                                Right to Left	                                                  Ternary conditional operator
//14                        	=, +=, -=, *=, /=, %=, &=, ^=, `                	=, <<=, >>=, >>>=`	                                Right to Left
//15                        	,	                                Left to Right	                                                 Comma operator (used in expressions)
//Key Notes:
//Associativity determines the direction in which operators of the same precedence are evaluated.
//Left to Right: When operators are evaluated from left to right (e.g., a + b + c is evaluated as (a + b) + c).
//Right to Left: When operators are evaluated from right to left (e.g., a = b = c is evaluated as a = (b = c)).
//Higher precedence means the operator is evaluated first in the expression.
//Let me know if you need further clarification!

//        do you mean 15 is operate first

//No, the numbers in the chart represent the precedence level where 1 has the highest precedence, meaning the operators at level 1 are evaluated first,
// and 15 has the lowest precedence, meaning operators at level 15 are evaluated last.