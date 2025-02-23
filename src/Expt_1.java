import java.util.*;
public class Expt_1 {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		//Sum of 2 numbers
		System.out.println("Enter Two Numbers: ");
		int n1 = input.nextInt();
		int n2 = input.nextInt();
		int sum = n1 + n2;
		System.out.println("Sum: " + sum);

		//2. Swapping 2 numbers using 3rd var
		int temp = n1;
		n1 = n2;
		n2 = temp;
		System.out.println("Swapped Numbers are by using 3rd variable: " + n1 +", " + n2);	

		//3. Swaps without 3rd variable
		n1 = n1 + n2;
		n2 = n1 - n2;
		n1 = n1 - n2;
		System.out.println("Re-Swapped Numbers without using 3rd variable: " + n1 +", " + n2);

		//4. Student Details
		System.out.println("\nEnter student details:");
        	System.out.print("Name: ");
        	String name = input.next();
        	System.out.print("Age: ");
        	int age = input.nextInt();
        	System.out.print("Phone Number: ");
        	String phone = input.next();
        	System.out.print("Marks in 3 subjects (separated by spaces): ");
        	int mark1 = input.nextInt();
        	int mark2 = input.nextInt();
        	int mark3 = input.nextInt();

        	double average = (mark1 + mark2 + mark3) / 3.0;

        	System.out.println("\nStudent Information:");
        	System.out.println("Name: " + name);
        	System.out.println("Age: " + age);
        	System.out.println("Phone: " + phone);
        	System.out.println("Average Marks: " + average);

		// 5. Even or odd
        	System.out.println("\nEnter an integer to check even/odd:");
        	int number = input.nextInt();
        	if (number % 2 == 0) {
            		System.out.println(number + " is even.");
        	} 
		else {
            		System.out.println(number + " is odd.");
        	}

        	// 6. Multiplication table
        	System.out.println("\nEnter an integer for multiplication table:");
        	int n = input.nextInt();
        	for (int i = 1; i <= 10; i++) {
            		System.out.println(n + " x " + i + " = " + (n * i));
        	}

        	// 7. Positive, negative, or zero
        	System.out.println("\nEnter an integer to check positive/negative/zero:");
        	int num = input.nextInt();
        	if (num > 0) {
            		System.out.println(num + " is positive.");
        	} 
		else if (num < 0) {
            		System.out.println(num + " is negative.");
        	} 
		else {
            		System.out.println(num + " is zero.");
        	}

		// 8. ASCII value of a character
        	System.out.println("\nEnter a character to get its ASCII value:");
        	char ch = input.next().charAt(0);  // Read a character
        	int ascii = ch; // Implicit type conversion char to int
        	System.out.println("ASCII value of " + ch + " is: " + ascii);

		// 9. Size of data types
        	System.out.println("\nSize of data types:");
        	System.out.println("int: " + Integer.BYTES + " bytes");
        	System.out.println("float: " + Float.BYTES + " bytes");
        	System.out.println("double: " + Double.BYTES + " bytes");
        	System.out.println("char: " + Character.BYTES + " bytes");
        	System.out.println("boolean: " + Byte.BYTES + " bytes"); // Technically, boolean's size is JVM-dependent, but often treated as 1 byte

		// 10. Type conversion
        	int intValue = 10;
        	double doubleValue = intValue; // Implicit conversion (widening)
        	System.out.println("\nImplicit type conversion (int to double): " + doubleValue);

        	double anotherDouble = 10.5;
        	int anotherInt = (int) anotherDouble; // Explicit conversion (narrowing) - data loss possible
        	System.out.println("Explicit type conversion (double to int): " + anotherInt);

        	input.close(); // Close the scanner
	}
}
