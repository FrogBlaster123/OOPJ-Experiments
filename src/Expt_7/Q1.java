package Expt_7;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Q1 {
    public static void  main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = 0;
        boolean validInput = false;

        try{
            System.out.print("Enter an Integer: ");
            num = sc.nextInt();
            validInput = true;
            System.out.println("Your Entered: " + num);
        }
        catch (InputMismatchException e){
            System.err.println("Error: Invalid input");
        }
        finally {
            System.out.println("Input Process Complete");
        }

        if (validInput) {
            System.out.println("Valid integer input was received.");
        }
        else{
            System.out.println("Integer input was not received.");
        }
    }
}
