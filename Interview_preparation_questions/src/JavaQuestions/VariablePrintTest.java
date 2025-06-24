package JavaQuestions;

class VariablePrintTest {

    static int s;

    VariablePrintTest() { // here it will initialize when the object is created so in this case it will not enter in if block because s is not initialized
        this.s = 4;
    }

    public static void main(String[] args) {
        if (s == 4) {
            int s = 6;
            System.out.println(s); // here it will print internal local variables s if s=4 and enter in if block
        }
    }
}