public class ExceptionDemo {
    public static void main(String[] args){
        int a,b,c;
        a = Integer.parseInt(args[0]);
        b = Integer.parseInt(args[1]);
        try{
            c = a/b;
            System.out.println(c);
        }
        catch (ArithmeticException e){
            System.out.println("Cant div by 0");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("enter at least two numbers..");
        }
        finally {
            System.out.println("fjsoeifh");
        }
    }
}
