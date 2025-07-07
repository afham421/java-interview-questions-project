package JavaQuestions;

class VariablePrintTest {

    static int s;
    static int d;
    int e;

    VariablePrintTest() { // here it will initialize when the object is created so in this case it will not enter in if block because s is not initialized
        this.s = 4;
    }

    public static void main(String[] args) {
        VariablePrintTest test = new VariablePrintTest();
        int f;
        if (s == 4) {
            int s = 6;
            System.out.println(s); // here it will print internal local variables s if s=4 and enter in if block
        }


        System.out.println("Static variable default value::::::" + d); ////static and instance variable are initialized by null or 0
        System.out.println("instance variable default value::::::" + test.e);
//        System.out.println("local variable default value::::::" + f); //// it will give error because local variable is compulsory to initialize
        f = 5;
        System.out.println("local variable default value::::::" + f); // now it will not give error

    }
}