public class ToBeTranslated {
    void arithmeticOperation(int a, double d, double b){
        double c = a + b + d;
        System.out.println(c);
        for (int i = 0; i < 3; i++){
            System.out.println(i);
        }
        System.out.print(d);
    }

    double returnValue(int a, int b, String value) {
        System.out.println(value);
        int c = a + b;
        return c;
    }

    void newMethod(int x, int y){
        if(x==y){
            System.out.println("statement is true");
        }


        if (x!=y){
            System.out.println("inside is statement");
        } else {
            System.out.print("inside else statement");
        }
    }
}
